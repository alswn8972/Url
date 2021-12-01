package com.alive.backend.user.repository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alive.backend.common.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserEntity extends BaseEntity {
    private String userId;
    private String userEmail;
    private String userPw;
}
