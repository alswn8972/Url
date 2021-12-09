package com.alive.backend.scheduler.service;

import com.alive.backend.scheduler.repository.HistoryRepository;
import com.alive.backend.url.dtos.UrlGetResponse;
import com.alive.backend.url.repository.HistoryEntity;
import com.alive.backend.url.repository.UrlEntity;
import com.alive.backend.url.repository.UrlRepository;
import com.alive.backend.url.service.UrlService;
import com.alive.backend.user.repository.UserEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchedulerService {
    private JavaMailSender javaMailSender;
    private static final String FROM_ADDRESS = "noticemonitoring@gmail.com";
    UrlRepository urlRepository;
    HistoryRepository historyRepository;
    public SchedulerService(UrlRepository urlRepository, HistoryRepository historyRepository) {
        this.urlRepository = urlRepository;
        this.historyRepository = historyRepository;
    }



    public List<UrlEntity> getAllUrl() {
        return urlRepository.findAll();
    }

    public void sendMail(Long id) {
        SimpleMailMessage message = new SimpleMailMessage();
        UrlEntity urlEntity = urlRepository.findByIdLike(id);
        message.setTo(urlEntity.getUserEntity().getUserEmail());
        message.setSubject("[알림] SITE Monitor에서 알려드립니다.");
        message.setText("어쩌구 저쩌구 입니당");
        javaMailSender.send(message);
        //메세지 보내면 다시 상태를 3으로 확인 (사용자가 알림을 보고나서 조치를 취하지 않은 후)
        urlEntity.setIsCheck(3);
        urlRepository.save(urlEntity);
    }

    public UrlEntity findUrl(Long id){
        return urlRepository.findByIdLike(id);
    }

    public void changeUrlState(Long id) {
        UrlEntity urlEntity = urlRepository.findByIdLike(id);
        urlEntity.setIsCheck(2);
        urlRepository.save(urlEntity);
    }

    public void saveUrlHistory(HistoryEntity historyEntity) {
        historyRepository.save(historyEntity);
    }
}
