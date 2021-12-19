package com.alive.backend.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findByUrlId(Long urlId);
    Optional<ReservationEntity> findById(Long id);
}
