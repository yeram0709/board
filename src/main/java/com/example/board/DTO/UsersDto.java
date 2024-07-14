package com.example.board.DTO;


import com.example.board.entity.UsersEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UsersDto {
    private Long id;
    private String username;
    private String password;

    public static UsersDto toUsersDto(UsersEntity user) {   //엔티티를 DTO로
        UsersDto dto = new UsersDto();
        dto.setId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
