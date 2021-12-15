package com.alive.backend.scheduler.dtos;

import com.alive.backend.scheduler.repository.ReservationEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ReserveSendMailResponse {
    @ApiModelProperty(name="email 리스트", example="cherrykang97@naver, ")
    List<ReservationEntity> emails;
}
