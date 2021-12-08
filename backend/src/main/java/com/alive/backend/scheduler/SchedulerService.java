package com.alive.backend.scheduler;

import com.alive.backend.url.repository.UrlRepository;
import com.alive.backend.url.service.UrlService;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {
    UrlRepository urlRepository;
    public SchedulerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }


}
