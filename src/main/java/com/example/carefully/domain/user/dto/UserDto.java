package com.example.carefully.domain.user.dto;

import com.example.carefully.domain.user.entity.User;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
public class UserDto {

    @Getter
    @RequiredArgsConstructor
    public enum RoleRequest {
        CLASSIC, SILVER, GOLD, PLATINUM;
    }


    @Getter
    @RequiredArgsConstructor
    public enum RoleResponse {
        CLASSIC, SILVER, GOLD, PLATINUM, ADMIN;
    }

    @Getter
    @RequiredArgsConstructor
    public enum EducationRequest {
        UNDERGRADUATE, BACHELOR, MASTER, DOCTOR;
    }

    @Getter
    @RequiredArgsConstructor
    public enum MembershipRequest {
        ACCEPT, WAITING, REJECT;
    }

    @Getter
    @RequiredArgsConstructor
    public enum GenderRequest {
        MAN, WOMAN, NA;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserRegisterRequest {

        @NotNull(message = "이메일을 입력해 주세요.")
        @Email(message = "이메일이 형식이 유효하지 않습니다.")
        @Schema(description = "이메일", example = "example@example.com", required = true)
        private String username;

        @NotNull(message = "비밀번호를 입력해 주세요.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,24}$",
                message = "비밀번호는 8자리 이상 24자리 이하여야 하며 숫자와 특수문자를 한 자리 이상 반드시 포함해야 합니다.")
        private String password;

        @NotNull(message = "이름을 입력해 주세요.")
        @Pattern(regexp = "^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]{2,10}$",
                message = "이름은 2자리 이상 10자리 이하여야 하며 특수문자는 입력할 수 없습니다.")
        @Schema(description = "이름", example = "홍길동", required = true)
        private String name;

        @NotNull(message = "주민번호를 입력해 주세요.")
        @Pattern(regexp = "^([0-9]{6})-([0-9]{7})$", message = "주민번호 형식이 유효하지 않습니다.")
        @Schema(description = "주민번호", example = "123456-1234567", required = true)
        private String identificationNumber;

        @NotNull(message = "전화번호를 입력해 주세요.")
        @Pattern(regexp = "^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})$", message = "전화번호 형식이 유효하지 않습니다.")
        @Schema(description = "전화번호", example = "010-1234-5678", required = true)
        private String phoneNumber;

        @NotNull(message = "성별을 선택해주세요.")
        @ApiModelProperty(example = "MAN/WOMAN/NA")
        private GenderRequest genderRequest;

        @NotNull(message = "국적을 입력해주세요.")
        @ApiModelProperty(example = "대한민국")
        private String nationality;

        @NotNull(message = "대학교 이름을 입력해주세요.")
        @ApiModelProperty(example = "상명대학교")
        private String universityName;

        @NotNull(message = "전공을 입력해주세요.")
        @ApiModelProperty(example = "컴퓨터과학부")
        private String major;

        @NotNull(message = "지도교수님 성함을 입력해주세요.")
        @ApiModelProperty(example = "김교수")
        private String advisorName;

        @NotNull(message = "학위를 선택해주세요.")
        @ApiModelProperty(example = "UNDERGRADUATE/BACHELOR/MASTER/DOCTOR")
        private EducationRequest educationRequest;

        @NotNull(message = "주소를 입력해 주세요.")
        @Schema(description = "주소", example = "서울시 어딘가...", required = true)
        private String address;

        @ApiModelProperty(example = "회원가입 신청합니다!")
        private String content;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserUpdateRequest {

        @NotNull
        @ApiModelProperty(example = "홍길동")
        private String name;

        @NotNull
        @ApiModelProperty(example = "상명대학교")
        private String universityName;

        @NotNull
        @ApiModelProperty(example = "UNDERGRADUATE/BACHELOR/MASTER/DOCTOR")
        private EducationRequest educationRequest;

        @NotNull
        @ApiModelProperty(example = "MAN/WOMAN/NA")
        private GenderRequest genderRequest;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AdminRegisterRequest {

        @NotNull(message = "이메일을 입력해 주세요.")
        @Email(message = "이메일이 형식이 유효하지 않습니다.")
        @Schema(description = "이메일", example = "example@example.com", required = true)
        private String username;

        @NotNull(message = "비밀번호를 입력해 주세요.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,24}$",
                message = "비밀번호는 8자리 이상 24자리 이하여야 하며 숫자와 특수문자를 한 자리 이상 반드시 포함해야 합니다.")
        private String password;

        @NotNull(message = "이름을 입력해 주세요.")
        @Pattern(regexp = "^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]{2,10}$",
                message = "이름은 2자리 이상 10자리 이하여야 하며 특수문자는 입력할 수 없습니다.")
        private String name;

        @NotNull(message = "주민번호를 입력해 주세요.")
        @Pattern(regexp = "^([0-9]{6})-([0-9]{7})$", message = "주민번호 형식이 유효하지 않습니다.")
        @Schema(description = "전화번호", example = "010-1234-5678", required = true)
        @ApiModelProperty(example = "123456-1234567")
        private String identificationNumber;

        @NotNull(message = "전화번호를 입력해 주세요.")
        @Pattern(regexp = "^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})$", message = "전화번호 형식이 유효하지 않습니다.")
        @Schema(description = "전화번호", example = "010-1234-5678", required = true)
        private String phoneNumber;

        @ApiModelProperty(example = "회원가입 신청합니다!")
        private String content;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AdminUpdateRequest {

        @NotNull
        @ApiModelProperty(example = "홍길동")
        private String name;

        @NotNull
        @ApiModelProperty(example = "MAN/WOMAN/NA")
        private GenderRequest genderRequest;

    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserResponse {

        @Schema(description = "유저 이메일", example = "test@test.com", required = true)
        private String username;

        @Schema(description = "유저 이름", example = "홍길동", required = true)
        private String name;

        @Schema(description = "유저 전화번호", example = "010-1234-5678", required = true)
        private String phoneNumber;

        @Schema(description = "유저 성별", example = "MAN/WOMAN/NA", required = true)
        private GenderRequest gender;

        @Schema(description = "유저 국적", example = "대한민국", required = true)
        private String nationality;

        @Schema(description = "유저 대학교", example = "상명대학교", required = true)
        private String universityName;

        @Schema(description = "유저 전공", example = "컴퓨터과학부", required = true)
        private String major;

        @Schema(description = "유저 지도교수 성함", example = "김교수", required = true)
        private String advisorName;

        @Schema(description = "유저 학위", example = "UNDERGRADUATE/BACHELOR/MASTER/DOCTOR", required = true)
        private EducationRequest education;

        @Schema(description = "유저 주소", example = "서울시 어딘가...", required = true)
        private String address;

        @NotNull
        @ApiModelProperty(example = "CLASSIC/SILVER/GOLD/PLATINUM")
        private RoleRequest role;

        public static UserResponse create(User user) {
            return UserResponse.builder()
                    .username(user.getUsername())
                    .name(user.getName())
                    .phoneNumber(user.getPhoneNumber())
                    .gender(GenderRequest.valueOf(user.getGender().name()))
                    .nationality(user.getNationality())
                    .universityName(user.getUniversityName())
                    .major(user.getMajor())
                    .advisorName(user.getAdvisorName())
                    .education(EducationRequest.valueOf(user.getEducation().name()))
                    .address(user.getAddress().getDetails())
                    .role(RoleRequest.valueOf(user.getRole().name()))
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AdminResponse {
        @Schema(description = "어드민 이메일", example = "test@test.com", required = true)
        private String username;

        @Schema(description = "어드민 이름", example = "홍길동", required = true)
        private String name;

        @Schema(description = "어드민 주민번호", example = "123456-1234567", required = true)
        private String identificationNumber;

        @Schema(description = "어드민 전화번호", example = "010-1234-5678", required = true)
        private String phoneNumber;

        String role;

        public static AdminResponse create(User user) {
            return AdminResponse.builder()
                    .username(user.getUsername())
                    .name(user.getName())
                    .identificationNumber(user.getIdentificationNumber())
                    .phoneNumber(user.getPhoneNumber())
                    .role(user.getRole().name())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRequest {

        @NotNull(message = "이메일을 입력해 주세요.")
        @Email(message = "이메일이 형식이 유효하지 않습니다.")
        @Schema(description = "이메일", example = "example@example.com", required = true)
        private String username;

        @NotNull(message = "비밀번호를 입력해 주세요.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,24}$",
                message = "비밀번호는 8자리 이상 24자리 이하여야 하며 숫자와 특수문자를 한 자리 이상 반드시 포함해야 합니다.")
        private String password;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignoutRequest {
        @NotNull(message = "비밀번호를 입력해 주세요.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,24}$",
                message = "비밀번호는 8자리 이상 24자리 이하여야 하며 숫자와 특수문자를 한 자리 이상 반드시 포함해야 합니다.")
        private String password;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserRoleRequest {
        @NotNull
        @ApiModelProperty(example = "CLASSIC/SILVER/GOLD/PLATINUM")
        private RoleRequest roleRequest;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserAllResponse {
        @NotNull
        @Size(min = 3, max = 50)
        @ApiModelProperty(example = "test@test.com")
        private String username;

        @NotNull
        @ApiModelProperty(example = "홍길동")
        private String name;

        @NotNull
        @ApiModelProperty(example = "CLASSIC/SILVER/GOLD/PLATINUM")
        private RoleResponse role;

        @NotNull
        @ApiModelProperty(example = "010-1234-5678")
        private String phoneNumber;

        public static UserAllResponse create(User user) {
            return UserAllResponse.builder()
                    .username(user.getUsername())
                    .name(user.getName())
                    .phoneNumber(user.getPhoneNumber())
                    .role(RoleResponse.valueOf(user.getRole().name()))
                    .build();
        }
    }
}
