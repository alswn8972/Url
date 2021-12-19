package com.alive.backend.mailGroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MailGroupRepository extends JpaRepository<MailGroupEntity, Long> {
    List<MailGroupEntity> findByUrlId(Long urlId);
    Optional<MailGroupEntity> findById(Long id);
}
