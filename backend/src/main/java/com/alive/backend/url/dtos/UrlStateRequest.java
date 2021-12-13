package com.alive.backend.url.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Data
@ApiModel("UrlStateRequest")
public class UrlStateRequest {
    @ApiModelProperty(name="statusCode", example="200")
    private final int statusCode;
    @ApiModelProperty(name="urlAddress", example="http://naver.com")
    private final URL urlAddress;
}
