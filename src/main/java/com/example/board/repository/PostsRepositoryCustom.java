package com.example.board.repository;

import com.example.board.entity.PostsEntity;

public interface PostsRepositoryCustom {
    PostsEntity findByTitle(String title);
}
