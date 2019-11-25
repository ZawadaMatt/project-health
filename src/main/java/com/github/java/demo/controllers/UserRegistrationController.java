package com.github.java.demo.controllers;

import com.github.java.demo.repositories.DieticanRepository;
import com.github.java.demo.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegistrationController {
    private final PatientsRepository patientsRepository;
    private final DieticanRepository dieticanRepository;

    @Autowired
    public UserRegistrationController(PatientsRepository patientsRepository, DieticanRepository dieticanRepository) {
        this.patientsRepository = patientsRepository;
        this.dieticanRepository = dieticanRepository;
    }

    @GetMapping("/register")
    public String prepareRegistrationPage() {
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(Model model) {

        return "index";
    }

}
