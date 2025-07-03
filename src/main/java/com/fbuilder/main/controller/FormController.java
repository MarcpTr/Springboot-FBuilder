package com.fbuilder.main.controller;

import com.fbuilder.main.model.Form;
import com.fbuilder.main.model.User;
import com.fbuilder.main.model.dto.Anwsers;
import com.fbuilder.main.model.dto.FormData;
import com.fbuilder.main.service.FormService;
import com.fbuilder.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class FormController {
    @Autowired
    UserService userService;
    @Autowired
    FormService formService;

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
    public String store(Model model, @ModelAttribute FormData formData)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        formService.createForm(formData, authentication.getName());

        model.addAttribute("pageTitle", "Pagina principal");
        model.addAttribute("content", "pages/index");
        return "layouts/base";
    }
    @GetMapping("/forms")
    public String getUserForms(Model model) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        User user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        List<Form> forms= formService.findByUserId(user.getId()).orElse(null);
        model.addAttribute("pageTitle", "Tus formularios");
        model.addAttribute("content", "pages/forms");
        model.addAttribute("forms", forms);
        return "layouts/base";
    }
    @GetMapping("/form/{id}")
    public String getFormById(@PathVariable("id") int id, Model model) {
        Form form = formService.findById(id).orElseThrow();
        System.out.println(form.getQuestions().get(0).getQuestion_text());
        model.addAttribute("pageTitle", "Pagina principal");
        model.addAttribute("content", "pages/form");
        model.addAttribute("form", form);
        return "layouts/base";
    }
}