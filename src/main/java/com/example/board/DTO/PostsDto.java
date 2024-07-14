package com.example.board.DTO;

import com.example.board.entity.PostsEntity;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostsDto {
    private Long postId;
    private String title;
    private String content;
    private String writer;
    private UsersDto usersDto;

    public static PostsDto toPostDto(PostsEntity postsEntity){
        PostsDto dto = new PostsDto();
        dto.setPostId(postsEntity.getPostId());
        dto.setTitle(postsEntity.getTitle());
        dto.setContent(postsEntity.getContent());
        dto.setWriter(postsEntity.getWriter());
        dto.setUsersDto(UsersDto.toUsersDto(postsEntity.getUser()));
        return dto;
    }
}


