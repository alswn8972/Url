package com.alive.backend.url.service;

import com.alive.backend.url.dtos.UrlAddRequest;
import com.alive.backend.url.dtos.UrlDeleteRequest;
import com.alive.backend.url.dtos.UrlPatchRequest;
import com.alive.backend.url.repository.UrlEntity;
import com.alive.backend.url.repository.UrlRepository;
import com.alive.backend.user.dtos.UserDto;
import com.alive.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(final UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public void addUrl(UrlAddRequest urlAddRequest){
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setUrlName(urlAddRequest.getUrlName());
        urlEntity.setUrlContent(urlAddRequest.getUrlContent());
        urlEntity.setUrlAddress(urlAddRequest.getUrlAddress());
        urlRepository.save(urlEntity);
    }

    public void patchUrl(UrlPatchRequest urlPatchRequest) {
        System.out.println(urlPatchRequest.getId().getClass().getName());
        Long id = urlPatchRequest.getId();
        urlRepository.findByIdLike(Long.valueOf(urlPatchRequest.getId()));
        UrlEntity urlEntity = urlRepository.findByIdLike(Long.valueOf(urlPatchRequest.getId()));
//
        urlEntity.setUrlName(urlPatchRequest.getUrlName());
        urlEntity.setUrlContent(urlPatchRequest.getUrlContent());
        urlEntity.setUrlAddress(urlPatchRequest.getUrlAddress());
        urlRepository.save(urlEntity);
    }

    public void deleteUrl(UrlDeleteRequest urlDeleteRequest) {
        urlRepository.deleteById(urlDeleteRequest.getId());
    }
}
