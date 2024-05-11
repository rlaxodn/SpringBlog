package com.example.SpringBlog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username") //게시글과 회원정보가 다대일 관계
    private UserEntity user; //FK

    private String title;
    private String content;
    private LocalDateTime dateTime;

}
