package com.fbuilder.main.controller;

import com.fbuilder.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
 @Autowired
    UserService userService;
    @GetMapping("/profile")
    public String profile(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("pageTitle", "Perfil de " + username);
        model.addAttribute("content", "pages/profile");
        return "layouts/base";
    }
}
