package com.alive.backend.mailGroup.controller;

import com.alive.backend.common.utils.BaseResponseBody;
import com.alive.backend.mailGroup.dtos.MailPatchRequest;
import com.alive.backend.mailGroup.dtos.MailAddRequest;
import com.alive.backend.mailGroup.dtos.MailListResponse;
import com.alive.backend.mailGroup.repository.MailGroupEntity;
import com.alive.backend.mailGroup.service.MailGroupService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "예약 API", tags = {"MailGroup"})
@CrossOrigin("*")
@RestController
@RequestMapping("/api/mail")
public class MailGroupController {
    private final MailGroupService mailGroupService;
    public MailGroupController(final MailGroupService mailGroupService) {
        this.mailGroupService = mailGroupService;
    }

    @PostMapping("/add")
    public ResponseEntity<? extends BaseResponseBody> addUrl(@RequestBody MailAddRequest mailAddRequest){
        mailGroupService.addReserve(mailAddRequest);
        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "저장이 완료되었습니다."));
    }

    @GetMapping("/{urlId}")
    public ResponseEntity<? extends BaseResponseBody> getUrlMails(@PathVariable Long urlId){
        List<MailGroupEntity> res = mailGroupService.getEmails(urlId);
        if(res == null){
            return ResponseEntity.status(404).body(MailListResponse.of(404, "등록된 이메일이 존재하지 않습니다.", null));
        }
        return ResponseEntity.status(201).body(MailListResponse.of(201, "이메일이 조회되었습니다.", res));
    }

    @PatchMapping("/patch")
    public ResponseEntity<? extends BaseResponseBody> patchUrl(@RequestBody MailPatchRequest mailPatchRequest) {
        if (mailGroupService.patchMail(mailPatchRequest))
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "수정이 완료되었습니다."));
        else
            return ResponseEntity.status(201).body(BaseResponseBody.of(404, "수정이 실패했습니다. 다시 한번 시도해주세요."));
    }
}
