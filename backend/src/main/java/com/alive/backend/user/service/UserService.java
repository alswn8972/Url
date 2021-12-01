package com.alive.backend.user.service;

import com.alive.backend.user.dtos.UserRegisterRequest;
import com.alive.backend.user.repository.UserEntity;
import com.alive.backend.user.repository.UserRepository;
import com.alive.backend.user.repository.UserRepositorySupport;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRepositorySupport userRepositorySupport;

    public UserService(final UserRepository userRepository, UserRepositorySupport userRepositorySupport) {
        this.userRepository = userRepository;
        this.userRepositorySupport = userRepositorySupport;
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
    public Boolean getUserId(String userId){
        return userRepositorySupport.findByUserId(userId);
    }
}
