package com.alive.backend.url.service;

import com.alive.backend.url.repository.UrlRepository;
import com.alive.backend.user.repository.UserRepository;

public class UrlService {
    private final UrlRepository urlRepository;
    public UrlService(final UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }
}
