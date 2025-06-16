package com.fbuilder.main.controller;

import com.fbuilder.main.model.dto.Anwsers;
import com.fbuilder.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

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
    @GetMapping("/form/create")
    public String create(Model model){
        model.addAttribute("pageTitle", "Create form");
        model.addAttribute("content", "pages/create-form");
        return "layouts/base";
    }
    @PostMapping("/form/create")
    public String store(Model model, @RequestBody List<Anwsers> anwsers)
    {
        for (Anwsers anwser : anwsers) {
            switch (anwser.getType()) {
                case "text":
                case "email":
                    System.out.println("Respuesta: " + anwser.getValue());
                    break;

            }
        }
        model.addAttribute("pageTitle", "Pagina principal");
        model.addAttribute("content", "pages/index");
        return "layouts/base";
    }
}