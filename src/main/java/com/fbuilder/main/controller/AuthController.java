package com.fbuilder.main.controller;


import com.fbuilder.main.model.User;
import com.fbuilder.main.model.dto.RegisterRequest;
import com.fbuilder.main.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.http.HttpRequest;

@Controller
public class AuthController {

    private final UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }
    @PostMapping("/register")
    public String register(RegisterRequest registerRequest, Model model, HttpServletRequest request) {
        try {
            userService.registerUser(registerRequest.getUsername(), registerRequest.getPassword());
            UsernamePasswordAuthenticationToken authRequest =
                    new UsernamePasswordAuthenticationToken(registerRequest.getUsername(), registerRequest.getPassword());

            Authentication authentication = authenticationManager.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

            System.out.println(authentication.getName());
            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
