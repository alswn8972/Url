package com.alive.backend.user.repository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alive.backend.common.utils.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserEntity extends BaseEntity {
    private String userId;
    private String email;
    private String password;

    public UserEntity(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }
}
