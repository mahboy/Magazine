package com.example.magazine.modules.controllers;

import com.example.magazine.modules.entity.Users;
import com.example.magazine.modules.services.UserService;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/password")
public class ResetPassword {

    private final UserService userService;

    public ResetPassword(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/reset")
    public String infoLogin(Model model){
        model.addAttribute("users",new Users());
        return "login/reset";
    }
//    @GetMapping("/reset/{email}")
//    public String pass(@PathVariable("email") String email){
//        userService.findUsername(email);
//        return "redirect:/index";
//    }

    //change password
//    @PostMapping("/reset")
//    public String changePassword(Model model, @PathVariable ("id") Long id,
//                                 @ModelAttribute(name = "users") Users users){
////        model.addAttribute("user",userService.findByid(id));
//        userService.saveUser(users);
//        return "redirect:/index";
//    }
}
