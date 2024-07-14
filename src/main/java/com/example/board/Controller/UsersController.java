package com.example.board.Controller;

import com.example.board.DTO.UsersDto;
import com.example.board.Service.UsersService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/adduser")
    public String save(@RequestBody UsersDto usersDto) {
        usersService.save(usersDto);
        return "success";
    }

    @PostMapping("/login")
    public String login(@RequestBody UsersDto usersDto, HttpSession session) {
       UsersDto result = usersService.login(usersDto);
       if(result != null) {
           session.setAttribute("user", result.getUsername());

           return "로그인 성공";
       }
       else{
           return "로그인 실패";
       }
    }
}
