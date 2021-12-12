package com.alive.backend.user.service;

import com.alive.backend.user.dtos.UserDto;
import com.alive.backend.user.dtos.UserLoginRequest;
import com.alive.backend.user.dtos.UserRegisterRequest;
import com.alive.backend.user.repository.UserEntity;
import com.alive.backend.user.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //회원가입
    public UserEntity createUser(UserRegisterRequest userRegisterRequest){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userRegisterRequest.getUserId());
        userEntity.setUserName(userRegisterRequest.getUserName());
        userEntity.setUserEmail(userRegisterRequest.getUserEmail());
        userEntity.setUserPw(passwordEncoder.encode(userRegisterRequest.getUserPw()));
        return userRepository.save(userEntity);
    }
    //아이디 중복검사
    public UserDto getUserId(String userId){
        return userRepository.findUserByUserId(userId);
    }
    @Transactional(readOnly = true)
    public UserDto selectUser(String userId) {
        return userRepository.findUserByUserId(userId);
    }

    //로그인
    public UserDto loginUser(UserLoginRequest userLoginRequest){
        String userId = userLoginRequest.getUserId();
        return userRepository.findUserByUserId(userId);
    }

    public UserDto getId(String userId){
        return userRepository.findUserByUserId(userId);
    }
}
