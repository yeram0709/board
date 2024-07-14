package com.example.board.Controller;


import com.example.board.DTO.PostsDto;
import com.example.board.Service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class PostsController {

    private final PostsService postsService;

    //게시물 업로드
    @PostMapping("/addboard")
    public String save(@RequestBody PostsDto postsDto){
        postsService.save(postsDto);
        return "success";
    }

    //전체 게시판 조회
    @GetMapping("/boards")
    public List<PostsDto> findAll(){
        List<PostsDto> postsDtos = postsService.findAll();
        return postsDtos;
    }

    //일정번호 조회
    @GetMapping("/boards/{id}")
    public PostsDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    //내용수정
    @PutMapping("/boardsedit/{id}")
    public PostsDto update(@PathVariable Long id, @RequestBody PostsDto postsDto){
        return postsService.update(id, postsDto);
    }

    //삭제
    @DeleteMapping("/boardsdelete/{id}")
    public List<PostsDto> delete(@PathVariable Long id){
        postsService.delete(id);
        return postsService.findAll();
    }

}
