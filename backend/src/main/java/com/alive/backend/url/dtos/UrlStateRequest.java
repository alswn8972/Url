package com.alive.backend.url.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel("UrlStateRequest")
public class UrlStateRequest {
    @ApiModelProperty(name="urlAddress", example="http://naver.com")
    private final String urlAddress;
}
