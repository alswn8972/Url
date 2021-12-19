package com.alive.backend.url.repository;

import com.alive.backend.common.utils.BaseEntity;
import com.alive.backend.user.repository.UserEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class UrlEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="userId")
    private UserEntity userEntity;

    String urlName;
    String urlContent;
    String urlAddress;
    int statusCode;
    LocalDateTime checkTime;
    boolean pending;
}
