package com.example.board.Service;

import com.example.board.entity.UsersEntity;
import com.example.board.DTO.UsersDto;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository userRepository;

    public void save(UsersDto usersDto) {
        UsersEntity usersEntity = UsersEntity.toUsersEntity(usersDto);
        userRepository.save(usersEntity);
    }

    public UsersDto login(UsersDto usersDto) {
        Optional<UsersEntity> byName = Optional.ofNullable(userRepository.findByUserName(usersDto.getUsername()));

        if(byName.isPresent()) {
            UsersEntity usersEntity = byName.get();
            if(usersEntity.getUsername().equals(usersDto.getUsername())) {
                UsersDto dto = UsersDto.toUsersDto(usersEntity);
                return dto;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }


    }

}
