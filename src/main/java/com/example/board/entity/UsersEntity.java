package com.example.board.entity;

import com.example.board.DTO.UsersDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Users")

public class UsersEntity {

    @Id //pk를 지정하는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long userId;

    @Column
    private String username;

    @Column
    private String password;

    public static UsersEntity toUsersEntity(UsersDto usersDto){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername(usersDto.getUsername());
        usersEntity.setPassword(usersDto.getPassword());
        return usersEntity;
    }

}
