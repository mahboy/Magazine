package com.example.magazine.modules.controllers;

import com.example.magazine.modules.entity.Posts;
import com.example.magazine.modules.services.PostService;
import com.example.magazine.modules.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminRoleController {
    private final PostService postService;
    private UserService userService;


    @Autowired
    public AdminRoleController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    //create a post get method
    @GetMapping("/post-register")
    public String registerPost(Model model){
        model.addAttribute("DataPost",postService.findAllPost());
        model.addAttribute("post",new Posts());
        return "posts/registerPosts";
    }

    //create a post Post method
    @PostMapping("/post-register")
    public String submitPost
            (@Valid @ModelAttribute(name = "post") Posts posts, Principal principal, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            return "posts/registerPosts";
        }else {
            posts.setUser(userService.findByUsername(principal.getName()));
            postService.savePost(posts);
            return "redirect:/index";
        }
    }
}
