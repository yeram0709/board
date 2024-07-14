package com.example.board.repository;

import com.example.board.entity.PostsEntity;
import com.example.board.entity.QPostsEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostsRepositoryImpl implements PostsRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public PostsEntity findByTitle(String title) {
        QPostsEntity qPosts = QPostsEntity.postsEntity;
        return queryFactory.selectFrom(qPosts)
                .where(qPosts.title.eq(title))
                .fetchOne();
    }



}
