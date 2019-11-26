package com.github.java.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomePageController {

    private PasswordEncoder passwordEncoder;

    @Autowired
    HomePageController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String get(Model model) {
        return "index";
    }

    @GetMapping("/user-login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/auth")
    public String auth(String email, String password) {
        return "";
    }
}
