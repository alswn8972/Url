package com.alive.backend.url.dtos;

import com.alive.backend.common.utils.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;


@Data
@ApiModel("UrlGetResponse")
public class UrlGetResponse{
    private final Long urlId;
    private final String urlName;
    private final String urlContent;
    private final String urlAddress;

    @Builder
    public UrlGetResponse(Long urlId, String urlName, String urlContent, String urlAddress) {
        this.urlId = urlId;
        this.urlName = urlName;
        this.urlContent = urlContent;
        this.urlAddress = urlAddress;
    }

}
