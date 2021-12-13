package com.alive.backend.common.mail.controller;

import com.alive.backend.common.mail.dtos.MailAuthRequest;
import com.alive.backend.common.mail.dtos.MailCodeWrapper;
import com.alive.backend.common.mail.dtos.MailDto;
import com.alive.backend.common.mail.dtos.MailWrapper;
import com.alive.backend.common.mail.service.MailService;
import com.alive.backend.common.utils.BaseResponseBody;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "이메일 API", tags = {"Email"})
@RestController
@RequestMapping("/api/v3/email")
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/code") // 이메일 인증 코드 보내기
    @ApiOperation(value = "이메일", notes = "<strong> 인증 코드 </strong>를 통해 회원가입한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
    })
    public ResponseEntity<? extends BaseResponseBody> sendEmail(
            @ApiParam(value="이메일 인증 정보", required = true) @RequestBody MailAuthRequest mailAuthRequest){
        String code = mailService.getTempPassword();
        mailService.sendMail(
                mailService.makeCodeMail(
                        MailCodeWrapper.builder()
                                .receiver(mailAuthRequest.getEmail())
                                .userName(mailAuthRequest.getName())
                                .authCode(code)
                                .build()
                )
        );

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

}
