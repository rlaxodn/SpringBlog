package com.example.SpringBlog.controller;

import com.example.SpringBlog.dto.PostDTO;
import com.example.SpringBlog.entity.PostEntity;
import com.example.SpringBlog.entity.UserEntity;
import com.example.SpringBlog.repository.PostRepository;
import com.example.SpringBlog.repository.UserRepository;
import com.example.SpringBlog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/post")
    public String post(Model model){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);

        return "post";
    }

    @PostMapping("/postProc")
    public String postProc(PostDTO postDTO){

        postService.PostProcess(postDTO);

        return "redirect:/";

    }
    @GetMapping("/postList")
    public String postList(Model model){

        List<PostEntity> posts = postRepository.findAll();
        model.addAttribute("posts", posts);

        return "postList";
    }

    @GetMapping("/post/{id}")
    public String postDetail(@PathVariable("id") int id, Model model){

        //postEntity에서 ID로 게시글 정보를 찾음
        Optional<PostEntity> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            PostEntity post = postOptional.get();
            model.addAttribute("post", post);
            return "postDetail";
        } else {
            // 게시글이 없을 경우 처리
            return "redirect:/postList";
        }
    }

    //My Posts
    @GetMapping("/myPosts")
    public String myPost(Model model){

        //session user username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //DB에서 user의 게시글을 불러옴
        UserEntity userEntity = userRepository.findByUsername(username);
        List<PostEntity> posts = postRepository.findByUser(userEntity);

        model.addAttribute("posts", posts);
        return "myPosts";
    }

    //My Post Detail
    @GetMapping("/my/post/{id}")
    public String myPostDetail(@PathVariable("id") int id, Model model){

        //postEntity에서 ID로 게시글 정보를 찾음
        Optional<PostEntity> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            PostEntity post = postOptional.get();
            model.addAttribute("post", post);
            return "myPostDetail";
        } else {
            // 게시글이 없을 경우 처리
            return "redirect:/myPosts";
        }
    }
}
