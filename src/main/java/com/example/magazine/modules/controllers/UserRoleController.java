package com.example.magazine.modules.controllers;

import com.example.magazine.modules.entity.Users;
import com.example.magazine.modules.services.PostService;
import com.example.magazine.modules.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserRoleController {
    private PostService postService;
    private final UserService userService;

    @Autowired
    public UserRoleController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    //show a single post
    @GetMapping("/post/{id}")
    public String show(Model model, @PathVariable("id") Long id){
        model.addAttribute("post",postService.getOnePost(id));
        return "posts/single";
    }

    //forget password
    @GetMapping("/forget")
    public String forget(){
        return "login/forgot";
    }


    @PostMapping("/reset")
    public String infoLogin( Authentication authentication,
                            @RequestParam("password") String password){
        userService.findUsername(authentication.getName(), password);
        return "redirect:/index";
    }

    @GetMapping("/reset")
    public String infoLogin(Model model) {
        model.addAttribute("users", new Users());
        return "login/reset";
    }

//    @GetMapping("/reset/{username}")
//    public String pass(@PathVariable("username") String username){
//        userService.findUsername(username);
//        return "redirect:/index";
//    }
}
