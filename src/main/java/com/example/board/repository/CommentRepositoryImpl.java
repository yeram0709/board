package com.example.board.repository;

import com.example.board.entity.CommentEntity;
import com.example.board.entity.QCommentEntity;
import com.example.board.entity.QPostsEntity;
import com.example.board.entity.QUsersEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public CommentEntity findByCommentId(Long id) {
        QCommentEntity qCommentEntity = QCommentEntity.commentEntity;
        return queryFactory.selectFrom(qCommentEntity)
                .where(qCommentEntity.commentId.eq(id))
                .fetchOne();
    }

    @Override
    public List<CommentEntity> findByPostsId(Long id) {
        QCommentEntity qCommentEntity = QCommentEntity.commentEntity;
        QPostsEntity qPostsEntity = QPostsEntity.postsEntity;
        QUsersEntity qUsersEntity = QUsersEntity.usersEntity;

        return queryFactory.selectFrom(qCommentEntity)
                .leftJoin(qCommentEntity.postId, qPostsEntity).fetchJoin()
                .leftJoin(qCommentEntity.userId, qUsersEntity).fetchJoin()
                .where(qCommentEntity.postId.postId.eq(id))
                .fetch();
    }
}
