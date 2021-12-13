package com.alive.backend.url.controller;

import com.alive.backend.common.utils.BaseResponseBody;
import com.alive.backend.common.utils.UrlConnector;
import com.alive.backend.url.dtos.UrlAddRequest;
import com.alive.backend.url.dtos.UrlDeleteRequest;
import com.alive.backend.url.dtos.UrlGetResponse;
import com.alive.backend.url.dtos.UrlPatchRequest;
import com.alive.backend.url.repository.UrlEntity;
import com.alive.backend.url.service.UrlService;
import com.alive.backend.user.dtos.UserDto;
import com.alive.backend.user.repository.UserEntity;
import com.alive.backend.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/url")
public class UrlController {
    static final int MOVED_PERMANENTLY = 301;
    static final int FOUND = 302;
    static final int  SEE_OTHER= 303;

    private final UrlService urlService;
    private final UserService userService;
    public UrlController(UrlService urlService, UserService userService) {
        this.urlService = urlService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<? extends BaseResponseBody> addUrl(@RequestBody UrlAddRequest urlAddRequest){
        urlService.addUrl(urlAddRequest);
        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "저장이 완료되었습니다."));
    }

    @PatchMapping("/patch")
    public ResponseEntity<? extends BaseResponseBody> patchUrl(@RequestBody UrlPatchRequest urlPatchRequest){
        urlService.patchUrl(urlPatchRequest);
        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "수정이 완료되었습니다."));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<? extends BaseResponseBody> deleteUrl(@RequestBody UrlDeleteRequest urlDeleteRequest){
        try {
            urlService.deleteUrl(urlDeleteRequest);
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "삭제가 완료되었습니다."));
        }catch (NullPointerException e){
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "다시 시도해주시길 바랍니다."));
        }
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<?> getMyUrl(@PathVariable String userId){
        try {
            List<UrlGetResponse> urlList =  urlService.getMyUrl(userId);
            return ResponseEntity.status(200).body(urlList);
        }catch (NullPointerException e){
            return null;
        }
    }

    @GetMapping("/check/{address:.+}")
    public ResponseEntity<? extends BaseResponseBody> getUrlState(@PathVariable(value = "address") String address) throws IOException {

        UrlConnector urlConnector = new UrlConnector();
        URLConnection urlConnection =null;
        try {
            urlConnection = new URL(address).openConnection();
        }catch (MalformedURLException e){
            address = "https://"+address;
            System.out.println(address);
            urlConnection = new URL(address).openConnection();
        }
         URL redirectUrl = urlConnector.getFinalURL(urlConnection.getURL());

        System.out.println(redirectUrl.toString());

        return null;
    }

}
