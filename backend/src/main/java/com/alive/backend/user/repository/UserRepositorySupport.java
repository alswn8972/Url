package com.alive.backend.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport{
    private final JPAQueryFactory jpaQueryFactory;

    public UserRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(UserEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    QUserEntity qUserEntity = QUserEntity.userEntity;

    public Boolean findByUserId(String userId){
        UserEntity userEntity = jpaQueryFactory
                .select(qUserEntity)
                .from(qUserEntity)
                .where(qUserEntity.userId.eq(userId))
                .fetchOne();

        if(userEntity == null) return true;
        else return false;
    }
}
