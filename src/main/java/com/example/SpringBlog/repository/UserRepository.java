package com.example.SpringBlog.repository;

import com.example.SpringBlog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
//username 중복 검증
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
}
