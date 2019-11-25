package com.github.java.demo.controllers;

import com.github.java.demo.domain.Progress;
import com.github.java.demo.repositories.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
public class ProgressRegistrationController {
    private final ProgressRepository progressRepository;

    @Autowired
    public ProgressRegistrationController (ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @GetMapping("/progress-register")
    public String preprareRegistrationPage () {
        return "WEB-INF/jsp/progress-registration-page.jsp";
    }

    @PostMapping("/progress-register")
    public String processRegistrationPage (LocalDate date, Double weight, Double height, Double targetWeight, String commentary) {
        Progress progress = new Progress();

        progress.setDate(date);
        progress.setWeight(weight);
        progress.setHeight(height);
        progress.setTargerWeight(targetWeight);
        progress.setCommentary(commentary);

        return "WEB-INF/jsp/home-page.jsp";
    }
}
