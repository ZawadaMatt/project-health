package com.github.java.demo.controllers;

import com.github.java.demo.domain.Dietician;
import com.github.java.demo.domain.Patient;
import com.github.java.demo.repositories.DieticanRepository;
import com.github.java.demo.repositories.PatientsRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class UserRegistrationController {
    private final PatientsRepository patientsRepository;
    private final DieticanRepository dieticanRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegistrationController(PatientsRepository patientsRepository, DieticanRepository dieticanRepository, PasswordEncoder passwordEncoder) {
        this.patientsRepository = patientsRepository;
        this.dieticanRepository = dieticanRepository;
        this.passwordEncoder = passwordEncoder;
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
    public String addUsertoDataBase(String email, String name, String lastName, String password, String birthDate, String gender, String phoneNumber) {
        Patient newPatient = new Patient();
        newPatient.setEmail(email);
        newPatient.setName(name);
        newPatient.setLastName(lastName);
        newPatient.setPassword(passwordEncoder.encode(password));
        newPatient.setBirthDate(LocalDate.parse(birthDate));
        newPatient.setGender(gender);
        newPatient.setPhoneNumber(phoneNumber);
        newPatient.setCreateAt(LocalDate.now());
        newPatient.setActive(true);
        patientsRepository.save(newPatient);
        return "index";
    }

    @PostMapping("/add-dietician")
    public String addDieticianToDataBase(String email, String name, String lastName, String password, String licenceNumber) {
        Dietician newDietician = new Dietician();
        newDietician.setEmail(email);
        newDietician.setName(name);
        newDietician.setLastName(lastName);
        newDietician.setPassword(passwordEncoder.encode(password));
        newDietician.setLicenceNumber(licenceNumber);
        dieticanRepository.save(newDietician);
        return "index";
    }

}
