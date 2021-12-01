package com.alive.backend.user.dtos;

import lombok.Builder;
import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String userEmail;

    @Builder
    public UserDto(String userId, String userEmail) {
        this.userId = userId;
        this.userEmail = userEmail;
    }
}