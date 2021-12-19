package com.alive.backend.url.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UrlDeleteRequest {
    @ApiModelProperty(name="id", example="1")
    private final Long id;
    @ApiModelProperty(name="userId", example="1")
    private final Long userId;
}
