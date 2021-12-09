package com.alive.backend.scheduler.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MailDto {
    @ApiModelProperty(name="email", example="alswn8972")
    private final String address;
    @ApiModelProperty(name="title", example="[알림]SITE monitor에서 알려드립니다.")
    private final String title;
    @ApiModelProperty(name="content", example="현재 어쩌구 저쩌구")
    private final String content;
}
