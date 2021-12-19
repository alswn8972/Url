package com.alive.backend.user.controller;

import com.alive.backend.url.dtos.UrlPatchRequest;
import com.alive.backend.user.dtos.UserLoginRequest;
import com.alive.backend.user.dtos.UserPatchRequest;
import com.alive.backend.user.repository.UserRepository;
import com.alive.backend.user.service.UserService;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alive.backend.common.utils.BaseResponseBody;
import com.alive.backend.user.dtos.UserRegisterRequest;

import java.util.NoSuchElementException;

@Api(value = "사용자 API", tags = {"User"})
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserController {
	private final UserService userService;
	public UserController(final UserService userService) {
		this.userService = userService;
	}
	@PostMapping("/join")
	public ResponseEntity<? extends BaseResponseBody> register(@RequestBody UserRegisterRequest userRegisterRequest){
		System.out.println(userRegisterRequest.getUserPw());
		userService.createUser(userRegisterRequest);
		return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<? extends BaseResponseBody> getUserById(@PathVariable(value = "id") String id) {
		if(userService.getUserId(id) == null)
			return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
		else
			return ResponseEntity.status(409).body(BaseResponseBody.of(409, "이미 존재하는 사용자 ID입니다."));
	}

	@PatchMapping("/user/patch")
	public ResponseEntity<? extends BaseResponseBody> patchUrl(@RequestBody UserPatchRequest userPatchRequest){
		userService.patchUser(userPatchRequest);
		return ResponseEntity.status(201).body(BaseResponseBody.of(201, "수정이 완료되었습니다."));
	}

}

