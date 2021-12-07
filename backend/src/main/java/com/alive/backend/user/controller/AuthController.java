package com.alive.backend.user.controller;

import com.alive.backend.common.utils.JwtTokenUtil;
import com.alive.backend.user.dtos.UserDto;
import com.alive.backend.user.dtos.UserLoginRequest;
import com.alive.backend.user.dtos.UserLoginResponse;
import com.alive.backend.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@Api(value = "인증 API", tags = {"Auth."})
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    public ResponseEntity<UserLoginResponse> login(@RequestBody @ApiParam(value="로그인 정보", required = true) UserLoginRequest userLoginRequest) {
        String password = userLoginRequest.getUserPw();

        try {
            UserDto userDto = userService.getUserId(userLoginRequest.getUserId());
            System.out.println(userDto.getUserName());
            if(passwordEncoder.matches(password, userDto.getUserPw()))
                return ResponseEntity.ok(UserLoginResponse.of(200, "Success", JwtTokenUtil.getToken(userDto)));
            return ResponseEntity.status(401).body(UserLoginResponse.of(401, "잘못된 비밀번호입니다.", null));
        } catch (NoSuchElementException e){
            return ResponseEntity.status(404).body(UserLoginResponse.of(404, "존재하지 않는 계정입니다.", null));
        }
    }
}
