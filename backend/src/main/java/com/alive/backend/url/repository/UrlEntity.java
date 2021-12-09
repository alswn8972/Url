package com.alive.backend.url.repository;

import com.alive.backend.common.utils.BaseEntity;
import com.alive.backend.user.repository.UserEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class UrlEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    String urlName;
    String urlContent;
    String urlAddress;
    int isCheck;
}