package com.alive.backend.url.service;

import com.alive.backend.user.repository.UserRepository;

public class UrlService {
    private final UserRepository userRepository;
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
