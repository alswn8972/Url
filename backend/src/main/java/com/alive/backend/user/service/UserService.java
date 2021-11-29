package com.alive.backend.user.service;

import com.alive.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
