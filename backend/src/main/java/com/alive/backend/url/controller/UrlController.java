package com.alive.backend.url.controller;

import com.alive.backend.common.utils.BaseResponseBody;
import com.alive.backend.url.dtos.UrlAddRequest;
import com.alive.backend.url.dtos.UrlDeleteRequest;
import com.alive.backend.url.dtos.UrlPatchRequest;
import com.alive.backend.url.service.UrlService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertPathValidatorException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/url")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
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

    @GetMapping("/{address:.+}")
    public ResponseEntity<? extends BaseResponseBody> getUrlState(@PathVariable(value = "address") String address) {
        String totalUrl = "http://"+address;
        URL url = null;
        HttpURLConnection conn = null;

        System.out.println(totalUrl);
        String responseData = "";
        BufferedReader br = null;
        StringBuffer sb = null;

        String returnData = "";
        try {
            //파라미터로 들어온 url을 사용해 connection 실시
            url = new URL(totalUrl);
            conn = (HttpURLConnection) url.openConnection();

            //http 요청에 필요한 타입 정의 실시
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("GET");

            //http 요청 실시
            conn.connect();
            System.out.println("http 요청 방식 : "+"GET");
            System.out.println("http 요청 타입 : "+"application/json");
            System.out.println("http 요청 주소 : "+totalUrl);

            System.out.println("");

            //http 요청 후 응답 받은 데이터를 버퍼에 쌓는다
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            sb = new StringBuffer();
            while ((responseData = br.readLine()) != null) {
                sb.append(responseData); //StringBuffer에 응답받은 데이터 순차적으로 저장 실시
            }

            //메소드 호출 완료 시 반환하는 변수에 버퍼 데이터 삽입 실시
            returnData = sb.toString();

            //http 요청 응답 코드 확인 실시
            String responseCode = String.valueOf(conn.getResponseCode());
            System.out.println("http 응답 코드 : "+responseCode);
            System.out.println("http 응답 데이터 : "+returnData);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //http 요청 및 응답 완료 후 BufferedReader를 닫아줍니다
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
