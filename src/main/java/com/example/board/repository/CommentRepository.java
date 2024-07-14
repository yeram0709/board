package com.example.board.repository;

import com.example.board.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.stream.events.Comment;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> , CommentRepositoryCustom{
}
