package com.example.SpringBlog.repository;

import com.example.SpringBlog.entity.PostEntity;
import com.example.SpringBlog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    boolean existsByUser(UserEntity userEntity);
    List<PostEntity> findByUser(UserEntity userEntity); //해당 USER가 작성한 모든 글

}
