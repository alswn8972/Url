package com.alive.backend.common.mail.repository;

import javax.persistence.Entity;


import com.alive.backend.common.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class MailEntity extends BaseEntity {
    private String code;
    private String userEmail;

}

