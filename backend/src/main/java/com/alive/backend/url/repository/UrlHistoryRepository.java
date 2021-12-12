package com.alive.backend.url.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlHistoryRepository extends JpaRepository<UrlHistoryEntity, Long> {
    //UrlHistoryEntity findByIdOrderByDateDesc(Long id);
}
