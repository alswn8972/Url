package com.alive.backend.scheduler.repository;

import com.alive.backend.scheduler.repository.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
}
