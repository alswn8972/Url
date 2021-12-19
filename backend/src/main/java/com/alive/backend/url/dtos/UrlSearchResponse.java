package com.alive.backend.url.dtos;

import com.alive.backend.common.utils.BaseResponseBody;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UrlSearchResponse extends BaseResponseBody {
    List<UrlGetResponse> urlSearchResponseList;


    public UrlSearchResponse(Integer statusCode, String message, List<UrlGetResponse> urlSearchResponseList) {
        super(statusCode, message);
        this.urlSearchResponseList = urlSearchResponseList;
    }

    public static UrlSearchResponse of(Integer statusCode, String message, List<UrlGetResponse> urlSearchResponseList) {
        UrlSearchResponse res = new UrlSearchResponse(statusCode, message,urlSearchResponseList);
        return res;
    }
}
