package com.github.java.demo.controllers;

import com.github.java.demo.repositories.DieticanRepository;
import com.github.java.demo.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().stream()
                .anyMatch(a -> ((GrantedAuthority) a).getAuthority().equalsIgnoreCase("PATIENT"))) {
            return "patient-panel";

        } else if (authentication.getAuthorities().stream()
                .anyMatch(a -> ((GrantedAuthority) a).getAuthority().equalsIgnoreCase("DIETETIAN"))) {
            return "dietetican-panel";
        }
        return "index";
    }

    @GetMapping("/user-login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logged-user")
    @ResponseBody
    public String getLoggedUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
