package com.alive.backend.url.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UrlSearchRequest {
    @ApiModelProperty(name="userId", example = "1")
    private final Long id;
    @ApiModelProperty(name="option", example="1")
    private final int option;
    @ApiModelProperty(name="keyword", example="비씨")
    private final String keyword;
}
