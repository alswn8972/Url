package com.alive.backend.scheduler.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReserveAddRequest {
    @ApiModelProperty(name="userId", example="1")
    private final Long userId;
    @ApiModelProperty(name="urlId", example="1")
    private final Long urlId;
    @ApiModelProperty(name="email Group", example="alswn8972@daum.net,cherrykang97@naver.com,")
    private final String emailGroup;
}

