package com.alive.backend.common.mail.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthCodeRepository extends JpaRepository<AuthCodeEntity, Long> {
    AuthCodeEntity findByUserEmail(String userEmail);
    void deleteByUserEmail(String userEmail);
    Optional<AuthCodeEntity> findByUserEmailAndCode(String userEmail, String code);
}
