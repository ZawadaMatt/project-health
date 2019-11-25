package com.github.java.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/home")
    public String get(Model model) {
        model.addAttribute("name", "Działaj");
        return "home";
    }
}
