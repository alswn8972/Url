package com.alive.backend.mailGroup.repository;

import com.alive.backend.common.utils.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
//@EntityListeners(AuditingEntityListener.class)
public class MailGroupEntity extends BaseEntity {
    private Long urlId;
    private Long userId;
    private String emailGroup;
}