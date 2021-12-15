package com.alive.backend.url.service;

import com.alive.backend.url.dtos.*;
import com.alive.backend.url.repository.UrlEntity;
import com.alive.backend.url.repository.UrlHistoryEntity;
import com.alive.backend.url.repository.UrlHistoryRepository;
import com.alive.backend.url.repository.UrlRepository;
import com.alive.backend.user.repository.UserEntity;
import com.alive.backend.user.repository.UserRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service("urlService")
public class UrlService {
    private final UrlRepository urlRepository;
    private final UserRepository userRepository;
    private final UrlHistoryRepository urlHistoryRepository;
    public UrlService(final UrlRepository urlRepository, final UserRepository userRepository, final UrlHistoryRepository urlHistoryRepository) {
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
        this.urlHistoryRepository = urlHistoryRepository;
    }


    @Transactional(readOnly = true)
    public List<UrlEntity> getUrls() {
        return urlRepository.findAll();
    }
    public List<UrlGetResponse> getMyUrl(String userId) {
        UserEntity urlList = userRepository.findByUserIdLike(userId);

        return urlList.getUrls().stream().map(url -> UrlGetResponse.builder()
                .urlId(url.getId())
                .urlName(url.getUrlName())
                .urlAddress(url.getUrlAddress())
                .urlContent(url.getUrlContent())
                        .urlIsPending(url.isPending())
                        .urlCheckTime(url.getCheckTime())
                        .urlStatusCode(url.getStatusCode()).build())
                .collect(Collectors.toList());
    }

    public List<UrlHistoryEntity> getUrlHistory(Long urlId) {
        LocalDateTime currentTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println(LocalTime.now().getHour());
        LocalDateTime startTime = null;
        if(LocalTime.now().getHour() == 0){
            startTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0,000000));
        }else{
            startTime = LocalDateTime.of(LocalDate.now(), LocalTime.now().minusHours(1));
        }

        List<UrlHistoryEntity> urlHistoryEntities = urlHistoryRepository.findByUrlIdAndCreatedDateBetween(urlId,startTime,currentTime);
        return urlHistoryEntities;
    }

    public UrlEntity findUrl(Long urlId) {
        return urlRepository.findByIdLike(urlId);
    }

    public void addUrl(UrlAddRequest urlAddRequest){
        UserEntity userEntity = userRepository.findIdByUserIdLike(urlAddRequest.getUserId());

        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setUrlName(urlAddRequest.getUrlName());
        urlEntity.setUrlContent(urlAddRequest.getUrlContent());
        urlEntity.setUrlAddress(urlAddRequest.getUrlAddress());
        urlEntity.setPending(false);
        urlEntity.setUserEntity(userEntity);
        urlRepository.save(urlEntity);
    }

    public void patchUrl(UrlPatchRequest urlPatchRequest) {
        UrlEntity urlEntity = urlRepository.findByIdLike(Long.valueOf(urlPatchRequest.getId()));
        urlEntity.setUrlName(urlPatchRequest.getUrlName());
        urlEntity.setUrlContent(urlPatchRequest.getUrlContent());
        urlEntity.setUrlAddress(urlPatchRequest.getUrlAddress());
        urlRepository.save(urlEntity);
    }

    @Transactional
    public void changePendingStateToTrue(Long id) {
        UrlEntity urlEntity = urlRepository.findByIdLike(id);
        urlEntity.setPending(true);
        urlRepository.save(urlEntity);
    }

    @Transactional
    public void changePendingStateToFalse(Long id) {
        UrlEntity urlEntity = urlRepository.findByIdLike(id);
        urlEntity.setPending(false);
        urlRepository.save(urlEntity);
    }

    @Transactional
    public void changeStatusCode(Long id, int code) {
        UrlEntity urlEntity = urlRepository.findByIdLike(id);
        urlEntity.setStatusCode(code);
        urlEntity.setCheckTime(LocalDateTime.now());
        urlRepository.save(urlEntity);
    }
    @Transactional
    public void deleteUrl(UrlDeleteRequest urlDeleteRequest) {
        urlRepository.deleteById(urlDeleteRequest.getId());
    }

}
