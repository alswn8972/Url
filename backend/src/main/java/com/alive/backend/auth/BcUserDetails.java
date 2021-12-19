package com.alive.backend.auth;

import com.alive.backend.user.dtos.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BcUserDetails implements UserDetails {

    UserDto user;

    boolean accountNonExpired;

    boolean accountNonLocked;

    boolean credentialNonExpired;

    boolean enabled = false;

    List<GrantedAuthority> roles = new ArrayList<>();

    public BcUserDetails(UserDto user) {
        super();
        this.user = user;
    }

    public UserDto getUser() {
        return this.user;
    }

    @Override
    public String getPassword() {
        return this.user.getUserPw();
    }

    @Override
    public String getUsername() {
        return this.user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    public void setAuthorities(List<GrantedAuthority> roles) {
        this.roles = roles;
    }
}
