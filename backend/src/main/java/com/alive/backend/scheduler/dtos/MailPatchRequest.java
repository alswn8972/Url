package com.alive.backend.scheduler.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MailPatchRequest {
    @ApiModelProperty(name="id", example="1")
    private final Long id;
    @ApiModelProperty(name="id", example="1")
    private final Long urlId ;
    @ApiModelProperty(name="name", example="비씨카드 도메인")
    private final String address;
}
