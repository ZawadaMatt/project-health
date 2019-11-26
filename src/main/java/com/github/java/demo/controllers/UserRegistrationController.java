package com.github.java.demo.controllers;

import com.github.java.demo.domain.Patient;
import com.github.java.demo.repositories.DieticanRepository;
import com.github.java.demo.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/register-dietician")
    public String prepareDieticanRegister() {
        return "register-dietician";
    }

    @GetMapping("register-patient")
    public String preparePatientRegister() {
        return "register-patient";
    }

    @PostMapping("/add-patient")
    public String addUsertoDataBase(String email, String password) {
        Patient newPatient = new Patient();
        newPatient.setEmail(email);
        newPatient.setPassword(password);
        return "index";
    }

    @PostMapping("/add-dietician")
    public String addDieticianToDataBase() {

        return "";
    }

}
