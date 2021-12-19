package com.alive.backend.url.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UrlPatchRequest {
    @ApiModelProperty(name="id", example="1")
    private final Long id;
    @ApiModelProperty(name="name", example="비씨카드 도메인")
    private final String urlName;
    @ApiModelProperty(name="content", example="비씨카드 메인 홈페이지")
    private final String urlContent;
    @ApiModelProperty(name="url", example="http://bccard.com")
    private final String urlAddress;
}
