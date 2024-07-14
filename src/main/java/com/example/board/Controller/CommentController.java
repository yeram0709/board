package com.example.board.Controller;

import com.example.board.DTO.CommentDto;
import com.example.board.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CommentController {

    private final CommentService commentService;

    //댓글작성
    @PostMapping("/comment/{id}")
    public String save(@RequestBody CommentDto commentDto, @PathVariable Long id) {
        commentService.save(commentDto, id);
        return "success";
    }

    //전체댓글조회
    @GetMapping("/comments")
    public List<CommentDto> findAll() {
        List<CommentDto> commentDtos = commentService.findAll();
        return commentDtos;
    }

    //게시글의 댓글 조회
    @GetMapping("/comments/{postId}")
    public List<CommentDto> findAllByPostId(@PathVariable Long postId) {
        return commentService.findAllByPostId(postId);
    }

    //내용수정
    @PutMapping("/commentsedit/{id}")
    public CommentDto update(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        return commentService.update(id, commentDto);
    }

    //삭제
    @DeleteMapping("/commentdelete/{id}")
    public List<CommentDto> delete(@PathVariable Long id) {
        commentService.delete(id);
        return commentService.findAll();
    }
}
