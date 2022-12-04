package com.example.carefully.domain.user.service.impl;

import com.example.carefully.domain.user.dto.TokenResponse;
import com.example.carefully.domain.user.dto.UserDto;
import com.example.carefully.domain.user.entity.User;
import com.example.carefully.domain.user.entity.Operation;
import com.example.carefully.domain.user.entity.General;
import com.example.carefully.domain.user.exception.DuplicatedUsernameException;
import com.example.carefully.domain.user.exception.NotValidationPasswordException;
import com.example.carefully.domain.user.exception.NotValidationRoleException;
import com.example.carefully.domain.user.repository.UserRepository;
import com.example.carefully.domain.user.service.UserService;
import com.example.carefully.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.carefully.global.utils.UserUtils.getCurrentUser;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    /*
    로그인
     */
    @Override
    @Transactional
    public TokenResponse login(UserDto.LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken unauthenticated = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(unauthenticated);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);

        return new TokenResponse(jwt);
    }

    /*
    회원가입 (리팩토링해라 김현빈)
     */
    @Override
    @Transactional
    public void signup(UserDto.RegisterRequest registerRequest) {
        if (isDuplicateUsername(registerRequest.getUsername())) {
            throw new DuplicatedUsernameException();
        }

        String requestRole = String.valueOf(registerRequest.getRole());

        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        if (requestRole.equals("GENERAL")) {
            General general = General.registerUser(registerRequest);
            userRepository.save(general);
        }
        else if (requestRole.equals("OPERATION")) {
            Operation operation = Operation.registerOperation(registerRequest);
            userRepository.save(operation);
        }
        else {
            throw new NotValidationRoleException();
        }
    }

    /*
    회원 정보 수정 (리팩토링해라 감현빈2)
     */
    @Override
    @Transactional
    public void update(UserDto.UpdateRequest updateRequest) {

        User user = getCurrentUser(userRepository);

        if (user.getRole().name().equals("GENERAL")) {
            General general = (General) user;
            General result = General.updateUser(general, updateRequest);
            general.update(result);
            userRepository.save(result);
        }
        else if (user.getRole().name().equals("OPERATION")) {
            Operation operation = (Operation) user;
            Operation result = Operation.updateOperation(operation, updateRequest);
            operation.update(result);
            userRepository.save(operation);
        }
        else {
            throw new NotValidationRoleException();
        }
    }

    /*
    회원 탈퇴
     */
    @Override
    @Transactional
    public void signout(UserDto.SignoutRequest signoutRequest) {

        User currentUser = getCurrentUser(userRepository);

        UsernamePasswordAuthenticationToken unauthenticated = passwordCheckLogic(currentUser, signoutRequest.getPassword());

        if (unauthenticated != null) {
            userRepository.delete(currentUser);
        } else {
            throw new NotValidationPasswordException();
        }
    }

    /*
    유저 아이디 중복검사
     */
    @Override
    public boolean isDuplicateUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    /*
    로그인한 사용자 정보 조회
     */
    @Override
    @Transactional(readOnly = true)
    public UserDto.UserResponse getMyUserWithAuthorities() {
        User user =  getCurrentUser(userRepository);
        return UserDto.UserResponse.create(user);
    }

    @Transactional(readOnly = true)
    public UsernamePasswordAuthenticationToken passwordCheckLogic(User user, String password) {
        UsernamePasswordAuthenticationToken unauthenticated = UsernamePasswordAuthenticationToken.unauthenticated(
                user.getUsername(),
                password
        );
        return unauthenticated;
    }
}