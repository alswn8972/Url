package com.alive.backend.mailGroup.service;

import com.alive.backend.mailGroup.dtos.MailPatchRequest;
import com.alive.backend.mailGroup.dtos.MailAddRequest;
import com.alive.backend.mailGroup.repository.MailGroupRepository;
import com.alive.backend.mailGroup.repository.MailGroupEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MailGroupService {
    private final MailGroupRepository mailGroupRepository;

    public MailGroupService(MailGroupRepository mailGroupRepository) {
        this.mailGroupRepository = mailGroupRepository;
    }

    public void addReserve(MailAddRequest mailAddRequest){
        MailGroupEntity mailGroupEntity = new MailGroupEntity();
        mailGroupEntity.setUrlId(mailAddRequest.getUrlId());
        mailGroupEntity.setEmailGroup(mailAddRequest.getEmailGroup());
        mailGroupEntity.setUserId(mailAddRequest.getUserId());
        mailGroupRepository.save(mailGroupEntity);
    }
    public List<MailGroupEntity> getEmails(Long urlId){
        List<MailGroupEntity> emails = mailGroupRepository.findByUrlId(urlId);
        return emails;
    }

    public boolean patchMail(MailPatchRequest mailPatchRequest) {

        Optional<MailGroupEntity> reservationEntity = mailGroupRepository.findById(mailPatchRequest.getId());
        if(reservationEntity.equals(Optional.empty())) {
            return false;
        }
        reservationEntity.get().setEmailGroup(mailPatchRequest.getAddress());
        mailGroupRepository.save(reservationEntity.get());
        return true;

    }
}
