package com.alive.backend.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping("/index")
    public String getIndex(){
        return "index";
    }
}
