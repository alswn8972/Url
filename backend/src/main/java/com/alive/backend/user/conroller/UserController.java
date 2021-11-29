package com.alive.backend.user.conroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alive.backend.common.utils.BaseResponseBody;
import com.alive.backend.user.dtos.UserRegisterRequest;


@RestController
@RequestMapping("/api")
public class UserController {

	@PostMapping("/register")
	public ResponseEntity<? extends BaseResponseBody> register(@RequestBody UserRegisterRequest userRegistRequest){
		return null;
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<? extends BaseResponseBody> getUserById(@PathVariable(value = "id") String id) {
		return null;
	}

}

