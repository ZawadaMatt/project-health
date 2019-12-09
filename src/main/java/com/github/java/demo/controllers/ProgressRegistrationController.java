package com.github.java.demo.controllers;

import com.github.java.demo.domain.Patient;
import com.github.java.demo.domain.Progress;
import com.github.java.demo.repositories.PatientsRepository;
import com.github.java.demo.repositories.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Controller
public class ProgressRegistrationController {
    private final ProgressRepository progressRepository;
    private PatientsRepository patientsRepository;

    @Autowired
    public ProgressRegistrationController(ProgressRepository progressRepository, PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
        this.progressRepository = progressRepository;
    }

    @GetMapping("/progress-register")
    public String prepareRegistrationPage(Model model) {
        model.addAttribute("patient", patientsRepository
                .findPatientByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("progressSet", progressRepository.findProgressesByPatient((Patient) model.getAttribute("patient")));
        return "patient-panel";
    }

    @Transactional
    @PostMapping("/progress-register")
    public String processRegistrationPage(String weight, String height, String targetWeight, String commentary) {
        Progress progress = new Progress();

        progress.setDate(LocalDate.now());
        progress.setWeight(Double.parseDouble(weight));
        progress.setHeight(Double.parseDouble(height));
        progress.setTargerWeight(Double.parseDouble(targetWeight));
        progress.setCommentary(commentary);
        progress.setPatient(patientsRepository.findPatientByEmail(SecurityContextHolder.getContext().getAuthentication().getName())); // rzutuje nam dany postęp do konkretneo zalogowanego uzytkownika (zalogowanego)
        progressRepository.save(progress);
        return "patient-panel";
    }
}
