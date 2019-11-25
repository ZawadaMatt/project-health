package com.github.java.demo.controllers;

import com.github.java.demo.domain.Patient;
import com.github.java.demo.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping
public class PatientRegistrationController {
    private final PatientsRepository patientsRepository;

    @Autowired
    public PatientRegistrationController (PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    @GetMapping
    public String prepareRegistrationPage () {
        return "WEB-INF/jsp/patient-registration-page.jsp";
    }

    @PostMapping
    public String processRegistrationPage(String email, String password, String gender, String name, String lastName, LocalDate birthDate, String affection, String phoneNumber) {
        Patient patient = new Patient();
        patient.setEmail(email);
        patient.setPassword(password);
        patient.setGender(gender);
        patient.setName(name);
        patient.setLastName(lastName);
        patient.setBirthDate(birthDate);
        patient.setAffections(affection);
        patient.setPhoneNumber(phoneNumber);

        return "WEB-INF/jsp/home-page.jsp";
    }

}
