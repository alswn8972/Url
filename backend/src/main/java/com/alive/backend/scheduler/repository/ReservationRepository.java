package com.alive.backend.scheduler.repository;

import com.alive.backend.scheduler.repository.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    ReservationEntity findByUrlId(Long urlId);
}
