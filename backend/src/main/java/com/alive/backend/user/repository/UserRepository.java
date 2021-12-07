package com.alive.backend.user.repository;

import com.alive.backend.user.dtos.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserDto findUserByUserIdLike(String userId);
    UserDto findByUserId(String userId);
}

