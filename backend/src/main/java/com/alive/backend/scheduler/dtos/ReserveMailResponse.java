package com.alive.backend.scheduler.dtos;

import com.alive.backend.common.utils.BaseResponseBody;
import com.alive.backend.scheduler.repository.ReservationEntity;
import com.alive.backend.user.dtos.UserLoginResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReserveMailResponse extends BaseResponseBody {
    @ApiModelProperty(name="email 리스트", example="cherrykang97@naver, ")
    List<ReservationEntity> emails;

    public static ReserveMailResponse of(Integer statusCode, String message, List<ReservationEntity> emails) {
        ReserveMailResponse res = new ReserveMailResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setEmails(emails);
        return res;
    }
}
