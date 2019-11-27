package com.github.java.demo.controllers;

import com.github.java.demo.domain.Patient;
import com.github.java.demo.repositories.DieticanRepository;
import com.github.java.demo.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomePageController {

    private PasswordEncoder passwordEncoder;
    private DieticanRepository dieticanRepository;
    private PatientsRepository patientsRepository;

    @Autowired
    HomePageController(PasswordEncoder passwordEncoder, DieticanRepository dieticanRepository, PatientsRepository patientsRepository) {
        this.passwordEncoder = passwordEncoder;
        this.dieticanRepository = dieticanRepository;
        this.patientsRepository = patientsRepository;
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
    public String auth(String choice, String email, String password) {

        if (choice.equalsIgnoreCase("patient")) {
            Patient authPatient = patientsRepository.findPatientByEmail(email);
            if (passwordEncoder.matches(authPatient.getPassword(), password)) {
                return "/";
            }
        }
        return "";
    }

    @GetMapping("/logged-user")
    @ResponseBody
    public String getLoggedUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
