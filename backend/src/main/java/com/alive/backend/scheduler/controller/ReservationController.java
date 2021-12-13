package com.alive.backend.scheduler.controller;

import com.alive.backend.common.utils.BaseResponseBody;
import com.alive.backend.scheduler.dtos.ReserveAddRequest;
import com.alive.backend.scheduler.service.ReservationService;
import com.alive.backend.url.dtos.UrlAddRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
