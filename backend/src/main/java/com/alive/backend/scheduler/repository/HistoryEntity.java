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
public class HistoryEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="url_id")
    private UrlEntity urlEntity;

    int stateCode;
    int readTimeout;
    int timeout;
    int contentLength;
    boolean isAbnormal;

}
