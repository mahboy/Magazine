package com.example.magazine.modules.controllers;

import com.example.magazine.modules.entity.Users;
import com.example.magazine.modules.security.JwtAuth;
import com.example.magazine.modules.security.JwtUtils;
import com.example.magazine.modules.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/guest")
public class NoLimitController {

    private UserService userService;
    private final AuthenticationManager manager;
    private final JwtUtils jwtUtils;

    @Autowired
    public NoLimitController(UserService userService, AuthenticationManager manager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.manager = manager;
        this.jwtUtils = jwtUtils;
    }

    //about
    @GetMapping("/about")
    public String about() {
        return "Contact/page";
    }

    //contact
    @GetMapping("/contact")
    public String contact() {
        return "Contact/contact";
    }

    //user register get-method
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new Users());
        return "login/register";
    }

    //user register post-method
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute(name = "user") Users users, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "login/register";
        else {
            this.userService.saveUser(users);
            return "redirect:/index";
        }
    }

    //user login post-method
    @PostMapping("/login")
    public String login(){
        return "redirect:/index";
    }

    //user login get-method
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("users",new Users());
        return "login/login";
    }

    //503 error
    @GetMapping("/503")
    public String error503(){
        return "errors/503";
    }

    //403 error
    @GetMapping("/403")
    public String error403(){
        return "errors/403";
    }

    //point to search page
    @GetMapping("/search")
    public String searchPage(){
        return "categories/search";
    }

    //user rest
    @PostMapping("/user/rest")
    public @ResponseBody Users resPostUser(@RequestBody Users users) {
        return this.userService.saveUser(users);
    }

    //user rest
    @GetMapping("/user/rest")
    public @ResponseBody List<Users> restGetPost()  {
        return this.userService.showAllUsers();
    }

    @PostMapping("/jwt/rest")
    public @ResponseBody ResponseEntity<?> jwtLogin(
            @RequestBody JwtAuth jwtAuth, HttpServletResponse response){
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(jwtAuth.getUsername(),jwtAuth.getPassword()));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        response.addHeader("Authorization",jwtUtils.generateToken(jwtAuth.getUsername()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
