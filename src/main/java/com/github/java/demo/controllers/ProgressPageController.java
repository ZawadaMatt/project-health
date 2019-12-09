package com.github.java.demo.controllers;

import com.github.java.demo.domain.Dietician;
import com.github.java.demo.repositories.DieticanRepository;
import com.github.java.demo.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProgressPageController {

    private PatientsRepository patientsRepository;
    private DieticanRepository dieticanRepository;

    @Autowired
    public ProgressPageController(PatientsRepository patientsRepository, DieticanRepository dieticanRepository) {
        this.patientsRepository = patientsRepository;
        this.dieticanRepository = dieticanRepository;
    }

    @GetMapping("/progress/{id}")
    public String progressPage(@PathVariable("id") String id, Model model) {
        model.addAttribute("patientProgress", patientsRepository.findPatientById(Long.parseLong(id)));
        Dietician dietician = dieticanRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("dietican", dietician);
        return "panel";
    }


}
