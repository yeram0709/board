package com.example.board.repository;

import com.example.board.entity.QUsersEntity;
import com.example.board.entity.UsersEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UsersRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public UsersEntity findByUserName(String username) {
        QUsersEntity qUser = QUsersEntity.usersEntity;
        return queryFactory.selectFrom(qUser)
                .where(qUser.username.eq(username))
                .fetchOne();
    }
}
