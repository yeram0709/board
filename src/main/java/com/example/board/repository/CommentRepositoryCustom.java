package com.example.board.repository;

import com.example.board.entity.CommentEntity;

import java.util.List;

public interface CommentRepositoryCustom {
    CommentEntity findByCommentId(Long commentId);
    List<CommentEntity> findByPostsId(Long id);
}
