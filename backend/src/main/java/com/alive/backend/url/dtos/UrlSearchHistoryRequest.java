package com.alive.backend.url.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UrlSearchHistoryRequest {
    @ApiModelProperty(name="userId", example = "1")
    private final Long id;
    @ApiModelProperty(name="option", example="1")
    private final int option;
}
