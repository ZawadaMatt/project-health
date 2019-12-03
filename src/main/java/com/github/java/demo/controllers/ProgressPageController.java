package com.github.java.demo.controllers;

import com.github.java.demo.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProgressPageController {

    private PatientsRepository patientsRepository;

    @Autowired
    public ProgressPageController(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    @GetMapping("/progress/{id}")
    public String progressPage(@PathVariable("id") String id, Model model) {
        model.addAttribute("patientProgress", patientsRepository.findPatientById(Long.parseLong(id)));
        return "panel";
    }


}
