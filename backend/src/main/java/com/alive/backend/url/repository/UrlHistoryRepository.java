package com.alive.backend.url.repository;

import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UrlHistoryRepository extends JpaRepository<UrlHistoryEntity, Long> {
    //UrlHistoryEntity findByIdOrderByDateDesc(Long id);

    List<UrlHistoryEntity> findByUrlIdAndCreatedDateBetween(Long id, LocalDateTime startTime, LocalDateTime currentTime);
    void deleteByCreatedDateBetween(LocalDateTime startTime, LocalDateTime currentTime);
}
