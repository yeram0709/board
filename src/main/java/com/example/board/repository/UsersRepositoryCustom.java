package com.example.board.repository;


import com.example.board.entity.UsersEntity;

public interface UsersRepositoryCustom {

    UsersEntity findByUserName(String username);
    //  UsersEntity findByUserName(String userName);

}
