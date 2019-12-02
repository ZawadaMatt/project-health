package com.github.java.demo.controllers;

import com.github.java.demo.domain.Dietician;
import com.github.java.demo.domain.Patient;
import com.github.java.demo.repositories.DieticanRepository;
import com.github.java.demo.repositories.PatientsRepository;
import com.github.java.demo.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().stream()
                .anyMatch(a -> ((GrantedAuthority) a).getAuthority().equalsIgnoreCase("PATIENT"))) {
            Patient patient = patientsRepository
                    .findPatientByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("patient", patient);
            return "patient-panel";

        } else if (authentication.getAuthorities().stream()
                .anyMatch(a -> ((GrantedAuthority) a).getAuthority().equalsIgnoreCase("DIETETIAN"))) {
            Dietician dietician = dieticanRepository
                    .findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("dietician", dietician);
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

    @PostMapping("/patient-to-list")
    public String patientToList(String email) {
        Patient patient = patientsRepository.findPatientByEmail(email);
        Dietician dietician = dieticanRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        dietician.addPatientToSet(patient);
        return "redirect:/";
    }
}
