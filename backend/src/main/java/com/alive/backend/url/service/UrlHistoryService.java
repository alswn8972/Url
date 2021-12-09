package com.alive.backend.url.service;

import com.alive.backend.url.repository.UrlEntity;
import com.alive.backend.url.repository.UrlHistoryEntity;
import com.alive.backend.url.repository.UrlHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
