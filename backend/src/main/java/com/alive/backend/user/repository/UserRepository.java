package com.alive.backend.user.repository;

import com.alive.backend.url.repository.UrlEntity;
import com.alive.backend.user.dtos.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserDto findUserByUserId(String userId);
    Long findIdByUserId(String userId);
    UserEntity findIdByUserIdLike(String userId);
    UserEntity findByUserIdLike(String userId);
//    List<UrlEntity> findUrls
    List<UrlEntity> findUrlsByUserIdLike(String userId);

}

