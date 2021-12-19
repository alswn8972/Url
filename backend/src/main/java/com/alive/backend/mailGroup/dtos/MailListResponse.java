package com.alive.backend.mailGroup.dtos;

import com.alive.backend.common.utils.BaseResponseBody;
import com.alive.backend.mailGroup.repository.MailGroupEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class MailListResponse extends BaseResponseBody {
    @ApiModelProperty(name="email 리스트", example="cherrykang97@naver, ")
    List<MailGroupEntity> emails;

    public static MailListResponse of(Integer statusCode, String message, List<MailGroupEntity> emails) {
        MailListResponse res = new MailListResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setEmails(emails);
        return res;
    }
}
