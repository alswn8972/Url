package com.alive.backend.scheduler.service;

import com.alive.backend.scheduler.dtos.ReserveAddRequest;
import com.alive.backend.scheduler.dtos.ReserveMailResponse;
import com.alive.backend.scheduler.repository.ReservationRepository;
import com.alive.backend.scheduler.repository.ReservationEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void addReserve(ReserveAddRequest reserveAddRequest){
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setUrlId(reserveAddRequest.getUrlId());
        reservationEntity.setEmailGroup(reserveAddRequest.getEmailGroup());
        reservationRepository.save(reservationEntity);
    }
    public ReservationEntity getEmails(Long urlId){
        ReservationEntity emails = reservationRepository.findByUrlId(urlId);
        return emails;
    }
}
