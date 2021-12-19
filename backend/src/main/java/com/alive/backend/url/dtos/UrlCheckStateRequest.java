package com.alive.backend.url.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UrlCheckStateRequest {
    @ApiModelProperty(name="1", example="1")
    private final int protocol;
    @ApiModelProperty(name="urlAddress", example="1")
    private final String address;
}
