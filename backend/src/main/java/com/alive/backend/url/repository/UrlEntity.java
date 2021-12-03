package com.alive.backend.url.repository;

import com.alive.backend.common.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class UrlEntity extends BaseEntity {
    String urlName;
    String urlContent;
    String urlAddress;
}
