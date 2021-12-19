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
import java.util.Optional;
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
        UserEntity urlList = userRepository.findIdByUserId(userId);

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
    @Transactional
    public Optional<UrlEntity> findUrl(Long urlId) {
        Optional<UrlEntity> urlEntity = urlRepository.findById(urlId);
        return urlEntity;
    }
    @Transactional
    public void addUrl(UrlAddRequest urlAddRequest){
        UserEntity userEntity = userRepository.findIdByUserId(urlAddRequest.getUserId());

        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setUrlName(urlAddRequest.getUrlName());
        urlEntity.setUrlContent(urlAddRequest.getUrlContent());
        urlEntity.setUrlAddress(urlAddRequest.getUrlAddress());
        urlEntity.setStatusCode(200);
        urlEntity.setPending(true);
        urlEntity.setUserEntity(userEntity);
        urlRepository.save(urlEntity);
    }
    @Transactional
    public void patchUrl(UrlPatchRequest urlPatchRequest) {
        Optional<UrlEntity> urlEntity = urlRepository.findById(Long.valueOf(urlPatchRequest.getId()));
        urlEntity.get().setUrlName(urlPatchRequest.getUrlName());
        urlEntity.get().setUrlContent(urlPatchRequest.getUrlContent());
        urlEntity.get().setUrlAddress(urlPatchRequest.getUrlAddress());
        urlEntity.get().setPending(false);
        urlEntity.get().setStatusCode(200);
        urlRepository.save(urlEntity.get());
    }

    @Transactional
    public void changePendingStateToTrue(Long id) {
        Optional<UrlEntity> urlEntity = urlRepository.findById(id);
        urlEntity.get().setPending(true);
        urlRepository.save(urlEntity.get());
    }

    @Transactional
    public void changePendingStateToFalse(Long id) {
        Optional<UrlEntity> urlEntity = urlRepository.findById(id);
        urlEntity.get().setPending(false);
        urlRepository.save(urlEntity.get());
    }

    @Transactional
    public void changeStatusCode(Long id, int code) {
        Optional<UrlEntity> urlEntity = urlRepository.findById(id);
        urlEntity.get().setStatusCode(code);
        urlEntity.get().setCheckTime(LocalDateTime.now());
        urlRepository.save(urlEntity.get());
    }
    @Transactional
    public void deleteUrl(Long urlId) {
        urlRepository.deleteById(urlId);
    }

    @Transactional
    public List<UrlGetResponse> searchUrl(UrlSearchRequest urlSearchRequest) {
        Optional<UserEntity> urlList = userRepository.findById(urlSearchRequest.getId());

        List<UrlGetResponse> urls;
        if(urlSearchRequest.getOption()==1){
            urls = urlList.get().getUrls().stream().filter(url ->
                    url.getUrlName().contains(urlSearchRequest.getKeyword())).map(filterUrl -> UrlGetResponse.builder()
                    .urlId(filterUrl.getId())
                    .urlName(filterUrl.getUrlName())
                    .urlAddress(filterUrl.getUrlAddress())
                    .urlContent(filterUrl.getUrlContent())
                    .urlCheckTime(filterUrl.getCheckTime())
                    .urlIsPending(filterUrl.isPending())
                    .urlStatusCode(filterUrl.getStatusCode()).build()).collect(Collectors.toList());
        }

        else if(urlSearchRequest.getOption()==2){
            urls = urlList.get().getUrls().stream().filter(url ->
                    url.getUrlContent().contains(urlSearchRequest.getKeyword())).map(filterUrl -> UrlGetResponse.builder()
                    .urlId(filterUrl.getId())
                    .urlName(filterUrl.getUrlName())
                    .urlAddress(filterUrl.getUrlAddress())
                    .urlContent(filterUrl.getUrlContent())
                    .urlCheckTime(filterUrl.getCheckTime())
                    .urlIsPending(filterUrl.isPending())
                    .urlStatusCode(filterUrl.getStatusCode()).build()).collect(Collectors.toList());

        }else if(urlSearchRequest.getOption()==3){
            urls = urlList.get().getUrls().stream().filter(url ->
                    url.getUrlAddress().contains(urlSearchRequest.getKeyword())).map(filterUrl -> UrlGetResponse.builder()
                    .urlId(filterUrl.getId())
                    .urlName(filterUrl.getUrlName())
                    .urlAddress(filterUrl.getUrlAddress())
                    .urlContent(filterUrl.getUrlContent())
                    .urlCheckTime(filterUrl.getCheckTime())
                    .urlIsPending(filterUrl.isPending())
                    .urlStatusCode(filterUrl.getStatusCode()).build()).collect(Collectors.toList());
        }else{
            urls = urlList.get().getUrls().stream().filter(url ->
                    (Integer.toString(url.getStatusCode())).equals((urlSearchRequest.getKeyword()))).map(filterUrl -> UrlGetResponse.builder()
                    .urlId(filterUrl.getId())
                    .urlName(filterUrl.getUrlName())
                    .urlAddress(filterUrl.getUrlAddress())
                    .urlContent(filterUrl.getUrlContent())
                    .urlCheckTime(filterUrl.getCheckTime())
                    .urlIsPending(filterUrl.isPending())
                    .urlStatusCode(filterUrl.getStatusCode()).build()).collect(Collectors.toList());
        }

        return urls;
    }

    public List<UrlHistoryEntity> getSearchHistory(UrlSearchHistoryRequest urlSearchHistoryRequest) {
        LocalDateTime currentTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println(LocalTime.now().getHour());
        LocalDateTime startTime = null;
        //1. 30분전 2. 1시간 전 3. 12시간 전 4. 하루 전
        if(urlSearchHistoryRequest.getOption() == 1){
            startTime = LocalDateTime.of(LocalDate.now(), LocalTime.now().minusMinutes(30));
        }else if(urlSearchHistoryRequest.getOption() == 2){
            startTime = LocalDateTime.of(LocalDate.now(), LocalTime.now().minusHours(1));
        }else if(urlSearchHistoryRequest.getId() == 3){
            startTime = LocalDateTime.of(LocalDate.now(), LocalTime.now().minusHours(12));
        }else{
            startTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.now());
        }

        List<UrlHistoryEntity> urlHistoryEntities = urlHistoryRepository.findByUrlIdAndCreatedDateBetween(urlSearchHistoryRequest.getId(),startTime,currentTime);
        return urlHistoryEntities;
    }
}
