package com.alive.backend.url.repository;

import com.alive.backend.url.dtos.UrlAddRequest;
import com.alive.backend.user.repository.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
    UrlEntity findByIdLike(Long id);
    }
