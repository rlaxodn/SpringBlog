package com.example.SpringBlog.controller;

import com.example.SpringBlog.dto.PostDTO;
import com.example.SpringBlog.repository.UserRepository;
import com.example.SpringBlog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @Autowired
    PostService postService;
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
}
