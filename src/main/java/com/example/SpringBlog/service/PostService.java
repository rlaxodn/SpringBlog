package com.example.SpringBlog.service;

import com.example.SpringBlog.dto.PostDTO;
import com.example.SpringBlog.entity.PostEntity;
import com.example.SpringBlog.entity.UserEntity;
import com.example.SpringBlog.repository.PostRepository;
import com.example.SpringBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    public void PostProcess(PostDTO postDTO){

        //username -> userEntity 가져오기
        UserEntity userEntity = userRepository.findByUsername(postDTO.getUsername());

        //db에 data삽입
        PostEntity data = new PostEntity();
        data.setTitle(postDTO.getTitle());
        data.setContent(postDTO.getContent());
        data.setDateTime(LocalDateTime.now());  //작성일(현재시간)
        data.setUser(userEntity);

        postRepository.save(data);
    }
}
