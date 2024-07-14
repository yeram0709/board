package com.example.board.Service;

import com.example.board.DTO.PostsDto;
import com.example.board.entity.PostsEntity;
import com.example.board.entity.UsersEntity;
import com.example.board.repository.PostsRepository;
import com.example.board.repository.UserRepository;
import com.example.board.repository.UsersRepositoryCustom;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class PostsService {

    private final UserRepository userRepository;
    private final PostsRepository postsRepository;
    private final HttpSession session;

    @Autowired
    public PostsService(UserRepository userRepository, PostsRepository postsRepository, HttpSession session) {
        this.userRepository = userRepository;
        this.postsRepository = postsRepository;
        this.session = session;
    }

    public void save(PostsDto postsDto) {
        String userId = (String) session.getAttribute("user");

        if (userId == null) {
            throw new IllegalStateException("로르인이 필요한 서비스입니다");
        }

        Optional<UsersEntity> optionuser = Optional.ofNullable(userRepository.findByUserName(userId));
        if(!optionuser.isPresent()) {
            throw new IllegalStateException("사용자가 없는 오류");
        }

        UsersEntity user = optionuser.get();


        PostsEntity post = new PostsEntity();
        post.setUser(user);
        post.setTitle(postsDto.getTitle());
        post.setContent(postsDto.getContent());
        post.setWriter(user.getUsername());


        postsRepository.save(post);
    }

    public List<PostsDto> findAll() {
        List<PostsEntity> postsEntities = postsRepository.findAll();
        List<PostsDto> postsDtos = new ArrayList<>();

        for(PostsEntity post : postsEntities) {
            postsDtos.add(PostsDto.toPostDto(post));
        }
        return postsDtos;
    }

    public PostsDto findById(Long id) {
        Optional<PostsEntity> postsEntity = postsRepository.findById(id);
        if(postsEntity.isPresent()) {
                return PostsDto.toPostDto(postsEntity.get());
        }else{
            return null;
        }
    }

    public PostsDto update(Long id, PostsDto UppostsDto) {
        Optional<PostsEntity> postsEntity = postsRepository.findById(id);
        if(postsEntity.isPresent()) {
            PostsEntity post = postsEntity.get();
            post.setTitle(UppostsDto.getTitle());
            post.setContent(UppostsDto.getContent());

            postsRepository.save(post);
            return PostsDto.toPostDto(post);
        }
        else{
            return null;
        }
    }

    public void delete(Long id) {
        postsRepository.deleteById(id);
    }

}
