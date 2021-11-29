package com.alive.backend.user.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class UserRegistReq {
	@ApiModelProperty(name="유저 ID", example="ssafy_web")
    private final String userId;
	@ApiModelProperty(name="유저 Password", example="your_password")
    private final String password;
	@ApiModelProperty(name="유저 email", example="your_email")
    private final String email;

}
