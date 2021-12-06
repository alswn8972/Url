package com.alive.backend.user.service;

import com.alive.backend.user.dtos.UserLoginRequest;
import com.alive.backend.user.dtos.UserRegisterRequest;
import com.alive.backend.user.repository.UserEntity;
import com.alive.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //회원가입
    public UserEntity createUser(UserRegisterRequest userRegisterRequest){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userRegisterRequest.getUserId());
        userEntity.setUserEmail(userRegisterRequest.getUserEmail());
        userEntity.setUserPw(userRegisterRequest.getUserPw());
        return userRepository.save(userEntity);
    }
    //아이디 중복검사
    public UserEntity getUserId(String userId){
        return userRepository.findByUserIdLike(userId);
    }

    //로그인
    public UserEntity loginUser(UserLoginRequest userLoginRequest){
        String userId = userLoginRequest.getUserId();
        String userPw = userLoginRequest.getUserPw();
        return userRepository.findByUserIdLikeAndUserPwLike(userId, userPw);
    }
}
