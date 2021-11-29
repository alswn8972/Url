package com.alive.backend.user;

import lombok.Builder;
import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String email;

    @Builder
    public UserDto(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

}