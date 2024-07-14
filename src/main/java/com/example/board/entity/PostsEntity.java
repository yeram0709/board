package com.example.board.entity;


import com.example.board.DTO.PostsDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "posts")

public class PostsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    public static PostsEntity toPostsEntity(PostsDto postsDto) {
        PostsEntity postsEntity = new PostsEntity();
        postsEntity.setPostId(postsDto.getPostId());
        postsEntity.setWriter(postsDto.getWriter());
        postsEntity.setTitle(postsDto.getTitle());
        postsEntity.setContent(postsDto.getContent());
        postsEntity.setUser(UsersEntity.toUsersEntity(postsDto.getUsersDto()));

        return postsEntity;
    }

}
