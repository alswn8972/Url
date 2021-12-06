package com.alive.backend.user.repository;

import com.alive.backend.user.dtos.UserDto;
import com.alive.backend.user.repository.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserDto findByUserIdLike(String userId);
    UserDto findByUserId(String userId);
}

