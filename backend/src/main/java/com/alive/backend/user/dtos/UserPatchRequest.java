package com.alive.backend.user.dtos;

import com.alive.backend.common.utils.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel("UserPatchRequest")
public class UserPatchRequest {
    @ApiModelProperty(name="고유번호", example="1")
    private final Long id;
    @ApiModelProperty(name="이메일", example="cherrykang97@naver.com")
    private final String userEmail;
}

