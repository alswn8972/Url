package com.alive.backend.url.repository;

import com.alive.backend.url.dtos.UrlAddRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
    UrlEntity findByIdLike(Long id);
}
