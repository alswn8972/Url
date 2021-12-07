package com.alive.backend.auth;

import com.alive.backend.user.dtos.UserDto;
import com.alive.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class BcUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public BcUserDetailService(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
         UserDto user = userService.selectUser(id);
        if(user != null) {
            BcUserDetails userDetail = new BcUserDetails(user);
            return userDetail;
        }
        return null;
    }
}
