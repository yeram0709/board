package com.example.board.Service;

import com.example.board.DTO.CommentDto;
import com.example.board.entity.CommentEntity;
import com.example.board.entity.PostsEntity;
import com.example.board.entity.UsersEntity;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.PostsRepository;
import com.example.board.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;
    private final HttpSession session;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostsRepository postsRepository, HttpSession session) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postsRepository = postsRepository;
        this.session = session;
    }

    public void save(CommentDto commentDto, Long postsId) {
        String userName = (String) session.getAttribute("user");

        if(userName == null) {
            throw new IllegalStateException("로그인이 필요한 서비스입니다");
        }

        Optional<UsersEntity> optionuser = Optional.ofNullable(userRepository.findByUserName(userName));
        if(!optionuser.isPresent()) {
            throw new IllegalStateException("사용자가 없는 오류");
        }

       PostsEntity postsEntity = postsRepository.findById(postsId).get();

       // PostsEntity posts = postsEntity.get();
        UsersEntity user = optionuser.get();

        CommentEntity comment = new CommentEntity();
        comment.setContent(commentDto.getContent());
        comment.setUserId(user);
        comment.setWriter(user.getUsername());
        comment.setPostId(postsEntity);

        commentRepository.save(comment);

    }

    public List<CommentDto> findAll() {
        List<CommentEntity> commentEntity = commentRepository.findAll();
        List<CommentDto> commentDtoList = new ArrayList<>();

        for(CommentEntity comment : commentEntity) {
            commentDtoList.add(CommentDto.toCommentDto(comment));
        }
        return commentDtoList;
    }

    public List<CommentDto> findAllByPostId(Long postId) {
        List<CommentEntity> commentEntities = commentRepository.findByPostsId(postId);
        List<CommentDto> commentDtoList = new ArrayList<>();

        for (CommentEntity comment : commentEntities) {
            commentDtoList.add(CommentDto.toCommentDto(comment));
        }
        return commentDtoList;
    }

    public CommentDto update(Long commentId, CommentDto commentDto) {
        Optional<CommentEntity> comment = commentRepository.findById(commentId);
        if(comment.isPresent()) {
            CommentEntity commentEntity = comment.get();
            commentEntity.setContent(commentDto.getContent());

            commentRepository.save(commentEntity);
            return CommentDto.toCommentDto(commentEntity);
        }else{
            return null;
        }
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
