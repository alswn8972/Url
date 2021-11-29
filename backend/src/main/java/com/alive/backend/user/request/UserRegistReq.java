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
	@ApiModelProperty(name="���� ID", example="ssafy_web")
    private final String userId;
	@ApiModelProperty(name="���� Password", example="your_password")
    private final String password;
	@ApiModelProperty(name="���� email", example="your_email")
    private final String email;

}
