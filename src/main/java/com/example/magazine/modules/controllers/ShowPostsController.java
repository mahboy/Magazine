package com.example.magazine.modules.controllers;

import com.example.magazine.modules.entity.Posts;
import com.example.magazine.modules.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Controller
@RequestMapping("/index")
public class ShowPostsController {

    private PostService postService;

    @Autowired
    public ShowPostsController( PostService postService) {
        this.postService = postService;
    }


    //show home page
    @GetMapping("")
    public String index(Model model, @PageableDefault(size = 4) Pageable pageable){
        model.addAttribute("posts",postService.findAllPost2(pageable));
        model.addAttribute("newPost",new Posts());
        return "index/index";
    }

    //list of a subject
    @GetMapping("/list/blockchain")
    public String showCategories
            (Model model,@ModelAttribute Posts posts, @PageableDefault(size = 4)Pageable pageable){
        model.addAttribute("post",postService.findBySubject1(posts,pageable));
        return "categories/category";
    }
    @GetMapping("/list/MachineLearning")
    public String showCategories2
            (Model model, @ModelAttribute Posts posts, @PageableDefault(size = 4)Pageable pageable){
        model.addAttribute("post",postService.findBySubject2(posts,pageable));
        return "categories/category";
    }

    @GetMapping("/list/GraphicsCards")
    public String showCategories3
            (Model model, @ModelAttribute Posts posts, @PageableDefault(size = 4)Pageable pageable){
        model.addAttribute("post",postService.findBySubject3(posts,pageable));
        return "categories/category";
    }

    @GetMapping("/list/RAM")
    public String showCategories4
            (Model model, @ModelAttribute Posts posts, @PageableDefault(size = 4)Pageable pageable){
        model.addAttribute("post",postService.findBySubject4(posts,pageable));
        return "categories/category";
    }

    @GetMapping("/list/CPU")
    public String showCategories5
            (Model model, @ModelAttribute Posts posts, @PageableDefault(size = 2)Pageable pageable){
        model.addAttribute("post",postService.findBySubject5(posts,pageable));
        return "categories/category";
    }

    //make a post by postman
    @PostMapping("/post/rest")
    public @ResponseBody Posts restPost(@ModelAttribute Posts post)  {
        return this.postService.savePost(post);
    }

    //show a post rest
    @GetMapping("/post/rest")
    public @ResponseBody List<Posts> restGetPost()  {
        return this.postService.findAllPost();
    }
}
