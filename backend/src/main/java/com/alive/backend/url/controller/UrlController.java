package com.alive.backend.url.controller;

import com.alive.backend.common.utils.BaseResponseBody;
import com.alive.backend.common.utils.UrlConnector;
import com.alive.backend.url.dtos.*;
import com.alive.backend.url.service.UrlService;
import com.alive.backend.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/url")
public class UrlController {
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

    @PostMapping("/check")
    public ResponseEntity<?> getUrlState(@RequestBody UrlCheckStateRequest urlCheckStateRequest) throws IOException {

        UrlConnector urlConnector = new UrlConnector();
        URLConnection urlConnection =null;
        String address = urlCheckStateRequest.getAddress();
        try {
            if(urlCheckStateRequest.getProtocol() == 1){
                address = "http://"+address;
            }else{
                address = "https://"+address;
            }
            urlConnection = new URL(address).openConnection();
        }catch (MalformedURLException e){
            //urlConnection = new URL(address).openConnection();
        }
        List<UrlStateResponse> urlResult = new ArrayList<>();
        urlResult = urlConnector.getFinalURL(urlConnection.getURL(), urlResult);
        if(urlResult.size()==0){
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "존재하지 않는 Url 입니다."));
        }

        return ResponseEntity.status(201).body(urlResult);
    }

}
