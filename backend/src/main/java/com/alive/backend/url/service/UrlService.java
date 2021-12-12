package com.alive.backend.url.service;

import com.alive.backend.url.dtos.UrlAddRequest;
import com.alive.backend.url.dtos.UrlDeleteRequest;
import com.alive.backend.url.dtos.UrlGetResponse;
import com.alive.backend.url.dtos.UrlPatchRequest;
import com.alive.backend.url.repository.UrlEntity;
import com.alive.backend.url.repository.UrlHistoryEntity;
import com.alive.backend.url.repository.UrlHistoryRepository;
import com.alive.backend.url.repository.UrlRepository;
import com.alive.backend.user.repository.UserEntity;
import com.alive.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("urlService")
public class UrlService {
    private final UrlRepository urlRepository;
    private final UserRepository userRepository;

    public UrlService(final UrlRepository urlRepository, final UserRepository userRepository) {
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
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
                        .urlStatusCode(url.getStatusCode()).build())
                .collect(Collectors.toList());
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
        urlRepository.save(urlEntity);
    }
    @Transactional
    public void deleteUrl(UrlDeleteRequest urlDeleteRequest) {
        urlRepository.deleteById(urlDeleteRequest.getId());
    }

}
