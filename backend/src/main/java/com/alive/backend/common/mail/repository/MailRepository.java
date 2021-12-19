package com.alive.backend.common.mail.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MailRepository extends JpaRepository<MailEntity, Long> {
    MailEntity findByUserEmail(String userEmail);
    void deleteByUserEmail(String userEmail);
    Optional<MailEntity> findByUserEmailAndCode(String userEmail, String code);
}
