package com.example.board.DTO;

import com.example.board.entity.CommentEntity;
import lombok.*;

import javax.xml.stream.events.Comment;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CommentDto {
    private Long commentId;
    private String writer;
    private String content;
    private PostsDto postsDto;
    private UsersDto usersDto;

    public static CommentDto toCommentDto(CommentEntity commentEntity) {
        CommentDto dto = new CommentDto();
        dto.setCommentId(commentEntity.getCommentId());
        dto.setWriter(commentEntity.getWriter());
        dto.setContent(commentEntity.getContent());
        dto.setPostsDto(PostsDto.toPostDto(commentEntity.getPostId()));
        dto.setUsersDto(UsersDto.toUsersDto(commentEntity.getUserId()));
        return dto;
    }

}
