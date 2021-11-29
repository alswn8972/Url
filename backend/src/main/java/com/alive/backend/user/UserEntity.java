package com.alive.backend.user;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alive.backend.common.utils.BaseEntity;
import javax.persistence.*;

@Entity
public class UserEntity extends BaseEntity {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String userId;
    private String email;
    private String password;

    public UserEntity(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }
}
