package com.alive.backend.user.repository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alive.backend.common.utils.BaseEntity;
import com.alive.backend.url.repository.UrlEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class UserEntity extends BaseEntity {
    private String userId;
    private String userEmail;
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPw;
    private String userName;
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<UrlEntity> urls = new ArrayList<>();
}
