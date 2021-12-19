package com.alive.backend.scheduler.controller;

import com.alive.backend.common.utils.BaseResponseBody;
import com.alive.backend.scheduler.dtos.MailPatchRequest;
import com.alive.backend.scheduler.dtos.ReserveAddRequest;
import com.alive.backend.scheduler.dtos.ReserveMailResponse;
import com.alive.backend.scheduler.repository.ReservationEntity;
import com.alive.backend.scheduler.service.ReservationService;
import com.alive.backend.url.dtos.UrlAddRequest;
import com.alive.backend.url.dtos.UrlDeleteRequest;
import com.alive.backend.url.dtos.UrlPatchRequest;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "예약 API", tags = {"Reservation"})
@CrossOrigin("*")
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/add")
    public ResponseEntity<? extends BaseResponseBody> addUrl(@RequestBody ReserveAddRequest reserveAddRequest){
        reservationService.addReserve(reserveAddRequest);
        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "저장이 완료되었습니다."));
    }

    @GetMapping("/{urlId}")
    public ResponseEntity<? extends BaseResponseBody> getUrlMails(@PathVariable Long urlId){
        List<ReservationEntity> res = reservationService.getEmails(urlId);
        if(res == null){
            return ResponseEntity.status(404).body(ReserveMailResponse.of(404, "등록된 이메일이 존재하지 않습니다.", null));
        }
        return ResponseEntity.status(201).body(ReserveMailResponse.of(201, "이메일이 조회되었습니다.", res));
    }

    @PatchMapping("/patch")
    public ResponseEntity<? extends BaseResponseBody> patchUrl(@RequestBody MailPatchRequest mailPatchRequest){
        if(reservationService.patchMail(mailPatchRequest))
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "수정이 완료되었습니다."));
        else
            return ResponseEntity.status(201).body(BaseResponseBody.of(404, "수정이 실패했습니다. 다시 한번 시도해주세요."));
    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<? extends BaseResponseBody> deleteUrl(@RequestBody UrlDeleteRequest urlDeleteRequest){
//
//    }
}