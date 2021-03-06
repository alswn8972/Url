package com.alive.backend.url.dtos;

import com.alive.backend.common.utils.BaseResponseBody;
import com.alive.backend.url.repository.UrlEntity;
import com.alive.backend.url.repository.UrlHistoryEntity;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import org.apache.jasper.tagplugins.jstl.core.Url;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@ApiModel("UrlGetResponse")
public class UrlGetResponse extends BaseResponseBody{
    private final Long urlId;
    private final String urlName;
    private final String urlContent;
    private final String urlAddress;
    private final boolean urlIsPending;
    private final LocalDateTime urlCheckTime;
    private final int urlStatusCode;
    @Builder
    public UrlGetResponse(Long urlId, String urlName,
                                 String urlContent,
                                 String urlAddress,
                          boolean urlIsPending,
                          LocalDateTime urlCheckTime,
                          int urlStatusCode) {
        this.urlId = urlId;
        this.urlName = urlName;
        this.urlContent = urlContent;
        this.urlAddress = urlAddress;
        this.urlIsPending = urlIsPending;
        this.urlCheckTime = urlCheckTime;
        this.urlStatusCode = urlStatusCode;
    }
}
