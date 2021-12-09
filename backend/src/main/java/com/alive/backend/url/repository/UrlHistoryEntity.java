package com.alive.backend.url.repository;

import com.alive.backend.common.utils.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class UrlHistoryEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="url_id")
    private UrlEntity urlEntity;

    int statusCode;
}