package com.alive.backend.user.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
public class UserRegisterRequest {
	@ApiModelProperty(name="ID", example="ssafy_web")
    private final String userId;
	@ApiModelProperty(name="Password", example="your_password")
    private final String userPw;
	@ApiModelProperty(name="Email", example="your_email")
    private final String userEmail;
}
