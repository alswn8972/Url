package com.alive.backend.common.mail.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MailAuthRequest {
    @ApiModelProperty(name="유저 email", example="alswn8972@daum.net")
    String email;
}
