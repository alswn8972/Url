package com.alive.backend.scheduler.repository;

import com.alive.backend.url.repository.HistoryEntity;
import com.alive.backend.url.repository.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
}
