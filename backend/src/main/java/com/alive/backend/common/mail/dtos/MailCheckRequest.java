package com.alive.backend.common.mail.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MailCheckRequest {
    @ApiModelProperty(name="유저 인증 코드", example="XEFDKGJ")
    String code;
    @ApiModelProperty(name="유저 email", example="alswn8972@daum.net")
    String email;
}
