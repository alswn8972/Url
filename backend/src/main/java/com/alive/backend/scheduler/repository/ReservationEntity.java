package com.alive.backend.scheduler.repository;

import com.alive.backend.common.utils.BaseEntity;
import com.fasterxml.jackson.databind.DatabindException;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
//@EntityListeners(AuditingEntityListener.class)
public class ReservationEntity extends BaseEntity {
    private Long urlId;
    private Long userId;
    String emailGroup;
}