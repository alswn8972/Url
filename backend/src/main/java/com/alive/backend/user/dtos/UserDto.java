package com.alive.backend.user.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDto {
    @ApiModelProperty(name="id", example="1")
    private final Long id;
    @ApiModelProperty(name="Name", example="강민주")
    private final String userName;
    @ApiModelProperty(name="ID", example="alswn8972")
    private final String userId;
    @ApiModelProperty(name="Password", example="djaakwl1@")
    private final String userPw;
    @ApiModelProperty(name="Email", example="cherrykang97@naver.com")
    private final String userEmail;

}
