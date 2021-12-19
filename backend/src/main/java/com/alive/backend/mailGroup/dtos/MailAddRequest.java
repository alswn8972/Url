package com.alive.backend.mailGroup.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MailAddRequest {
    @ApiModelProperty(name="userId", example="1")
    private final Long userId;
    @ApiModelProperty(name="urlId", example="1")
    private final Long urlId;
    @ApiModelProperty(name="email Group", example="alswn8972@daum.net,cherrykang97@naver.com,")
    private final String emailGroup;
}

