package com.example.carefully.domain.user.dto;

import com.example.carefully.domain.user.entity.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
public class UserDto {

    @Getter
    @RequiredArgsConstructor
    public enum RoleRequest {
        USER, OPERATION
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterRequest {

        @NotNull
        @Size(min = 3, max = 50)
        @ApiModelProperty(example = "test@test.com")
        private String username;

        @NotNull
        @ApiModelProperty(example = "홍길동")
        private String name;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotNull
        @Size(min = 3, max = 100)
        @ApiModelProperty(example = "test1234")
        private String password;

        @NotNull
        @Size(min = 3, max = 50)
        @ApiModelProperty(example = "01012345678")
        private String phoneNumber;

        @ApiModelProperty(example = "123456-1234567")
        private String foreignerNumber;

        @ApiModelProperty(example = "WOMAN/MAN/NA")
        private Gender gender;

        private Address address;

        @ApiModelProperty(example = "상명대학교")
        private String university;

        @ApiModelProperty(example = "컴퓨터과학과")
        private String major;

        @ApiModelProperty(example = "TRANSLATE/DWELLING/TRAFFIC")
        private BusinessType businessType;

        @ApiModelProperty(example = "케어풀리")
        private String businessName;

        @ApiModelProperty(example = "123-12-12345")
        private String businessRegisterNumber;

        @NotNull
        @ApiModelProperty(example = "USER/OPERATION/ADMIN")
        private RoleRequest role;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserResponse {

        @NotNull
        @Size(min = 3, max = 50)
        @ApiModelProperty(example = "test@test.com")
        private String username;

        @NotNull
        @ApiModelProperty(example = "홍길동")
        private String name;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotNull
        @Size(min = 3, max = 100)
        @ApiModelProperty(example = "test1234")
        private String password;

        @NotNull
        @Size(min = 3, max = 50)
        @ApiModelProperty(example = "01012345678")
        private String phoneNumber;

        @ApiModelProperty(example = "123456-1234567")
        private String foreignerNumber;

        @ApiModelProperty(example = "WOMAN/MAN/NA")
        private Gender gender;

        private Address address;

        @ApiModelProperty(example = "상명대학교")
        private String university;

        @ApiModelProperty(example = "컴퓨터과학과")
        private String major;

        @ApiModelProperty(example = "TRANSLATE/DWELLING/TRAFFIC")
        private BusinessType businessType;

        @ApiModelProperty(example = "케어풀리")
        private String businessName;

        @ApiModelProperty(example = "123-12-12345")
        private String businessRegisterNumber;

        @ApiModelProperty(example = "USER/OPERATION/ADMIN")
        private Role role;

        public static UserResponse create(User user) {
            return UserResponse.builder()
                    .username(user.getUsername())
                    .name(user.getName())
                    .phoneNumber(user.getPhoneNumber())
                    .address(((General)user).getAddress())
                    .gender(((General) user).getGender())
                    .businessName(((Operation) user).getBusinessName())
                    .businessRegisterNumber(((Operation) user).getBusinessRegisterNumber())
                    .major(((General) user).getMajor())
                    .university(((General) user).getUniversity())
                    .role(user.getRole())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateRequest {

        @NotNull
        @ApiModelProperty(example = "홍길동")
        private String name;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotNull
        @Size(min = 3, max = 100)
        @ApiModelProperty(example = "test1234")
        private String password;

        @NotNull
        @Size(min = 3, max = 50)
        @ApiModelProperty(example = "01012345678")
        private String phoneNumber;

        @ApiModelProperty(example = "123456-1234567")
        private String foreignerNumber;

        @ApiModelProperty(example = "WOMAN/MAN/NA")
        private Gender gender;

        private Address address;

        @ApiModelProperty(example = "상명대학교")
        private String university;

        @ApiModelProperty(example = "컴퓨터과학과")
        private String major;

        @ApiModelProperty(example = "TRANSLATE/DWELLING/TRAFFIC")
        private BusinessType businessType;

        @ApiModelProperty(example = "케어풀리")
        private String businessName;

        @ApiModelProperty(example = "123-12-12345")
        private String businessRegisterNumber;

        @NotNull
        @ApiModelProperty(example = "USER/OPERATION/ADMIN")
        private RoleRequest role;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRequest {

        @NotNull
        @Size(min = 3, max = 50)
        @ApiModelProperty(example = "test@test.com")
        private String username;

        @NotNull
        @Size(min = 3, max = 100)
        @ApiModelProperty(example = "test1234")
        private String password;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignoutRequest {

        @NotNull
        @Size(min = 3, max = 100)
        @ApiModelProperty(example = "test1234")
        private String password;
    }
}
