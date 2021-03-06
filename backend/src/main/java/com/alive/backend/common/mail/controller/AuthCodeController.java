package com.alive.backend.common.mail.controller;

import com.alive.backend.common.mail.dtos.*;
import com.alive.backend.common.mail.service.AuthCodeService;
import com.alive.backend.common.utils.BaseResponseBody;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "이메일 API", tags = {"Mail"})
@RestController
@RequestMapping("/api/mail")
public class AuthCodeController {
    private final AuthCodeService authCodeService;

    public AuthCodeController(AuthCodeService authCodeService) {
        this.authCodeService = authCodeService;
    }

    @GetMapping("/code/{email}") // 이메일 인증 코드 보내기
    @ApiOperation(value = "이메일", notes = "<strong> 인증 코드 </strong>를 통해 회원가입한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
    })
    public ResponseEntity<? extends BaseResponseBody> sendCode(
            @ApiParam(value="이메일 인증 정보", required = true) @PathVariable String email){
        String code = authCodeService.getTempPassword();
        MailDto mailDto = new MailDto(code, email);
        authCodeService.saveCode(mailDto);
        authCodeService.sendMail(
                authCodeService.makeCodeMail(
                        MailCodeWrapper.builder()
                                .receiver(email)
                                .authCode(code)
                                .build()
                )
        );
        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "인증코드 전송이 성공되었습니다."));
    }

    @PostMapping("/check") // 이메일 인증 코드 확인하기
    @ApiOperation(value = "이메일", notes = "<strong> 인증 코드 </strong>를 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
    })
    public ResponseEntity<? extends BaseResponseBody> checkCode(
            @ApiParam(value="이메일 인증 정보", required = true) @RequestBody MailCheckRequest mailCheckRequest){
        if(!authCodeService.checkCode(mailCheckRequest).equals(mailCheckRequest.getCode())){
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "인증코드가 일치하지 않습니다."));
        }

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "인증이 성공적으로 완료되었습니다."));
    }

}
