package com.alive.backend.url.service;

import com.alive.backend.url.repository.UrlHistoryEntity;
import com.alive.backend.url.repository.UrlHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class UrlHistoryService {
    private final UrlHistoryRepository urlHistoryRepository;

    public UrlHistoryService(final UrlHistoryRepository urlHistoryRepository) {
        this.urlHistoryRepository = urlHistoryRepository;
    }

    @Transactional
    public void save(final UrlHistoryEntity urlHistoryEntity) {
        urlHistoryRepository.save(urlHistoryEntity);
    }

    @Transactional
    public UrlHistoryEntity getStatus(Long id) {
        return null;
    }
    @Transactional
    public void deleteData() {
        LocalDateTime currentTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.now());
        urlHistoryRepository.deleteByCreatedDateBetween(startTime, currentTime);
    }
}
