package com.alive.backend.url.dtos;

import com.alive.backend.common.utils.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@ApiModel("UrlHistoryGetResponse")
public class UrlHistoryGetResponse extends BaseResponseBody{
    private final Long urlId;
    private final LocalDateTime time;
    private final int urlStatusCode;
    @Builder
    public UrlHistoryGetResponse(Long urlId,
                                 LocalDateTime time, int urlStatusCode) {
        this.urlId = urlId;
        this.time = time;
        this.urlStatusCode = urlStatusCode;
    }
}
