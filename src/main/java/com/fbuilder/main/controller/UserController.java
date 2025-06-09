package com.fbuilder.main.controller;

import com.fbuilder.main.model.User;
import com.fbuilder.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller

public class UserController {
 @Autowired
    UserService userService;
    @GetMapping("/user/{userId}")
    public String viewUser(@PathVariable Long userId, Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("username", user.getUsername());
        return "user";
    }
}
