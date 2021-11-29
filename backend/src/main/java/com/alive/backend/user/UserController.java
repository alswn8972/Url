package com.alive.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alive.backend.common.utils.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "USER API", tags = {"User"})
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/regist")
	@ApiOperation(value = "ȸ�� ����", notes = "<strong>���̵��  �н�����</strong>�� ���� ȸ������ �Ѵ�.") 
    @ApiResponses({
        @ApiResponse(code = 201, message = "����"),
        @ApiResponse(code = 409, message = "����")
    })
	public ResponseEntity<? extends BaseResponseBody> register(
			@ApiParam(value="ȸ������ ����", required = true) UserRegisterPostReq registerInfo){
		
		return null;
	}


}

