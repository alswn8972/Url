package com.alive.backend.user.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLoginRequest {
    @ApiModelProperty(name="ID", example="alswn8972")
    private final String userId;
    @ApiModelProperty(name="Password", example="djaakwl1@")
    private final String userPw;
}
