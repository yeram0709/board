package com.example.board.entity;

import com.example.board.DTO.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column
    private String writer;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostsEntity postId;

    public static CommentEntity toCommentEntity(CommentDto commentDto) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentId(commentDto.getCommentId());
        commentEntity.setWriter(commentDto.getWriter());
        commentEntity.setContent(commentDto.getContent());
        commentEntity.setPostId(PostsEntity.toPostsEntity(commentDto.getPostsDto()));
        commentEntity.setUserId(UsersEntity.toUsersEntity(commentDto.getUsersDto()));
        return commentEntity;
    }
}
