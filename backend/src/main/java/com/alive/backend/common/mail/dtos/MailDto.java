package com.alive.backend.common.mail.dtos;

import lombok.Builder;
import lombok.Data;

@Data
public class MailDto {
    private String code;
    private String userEmail;
    @Builder
    public MailDto(String code, String userEmail){
        this.code = code;
        this.userEmail = userEmail;
    }
}
