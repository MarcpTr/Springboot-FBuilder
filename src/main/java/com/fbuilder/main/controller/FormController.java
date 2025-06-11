package com.fbuilder.main.controller;

import com.fbuilder.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String index(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("pageTitle", "Pagina principal");
        model.addAttribute("content", "pages/index");
        return "layouts/base";
    }
}