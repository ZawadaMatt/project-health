package com.github.java.demo.controllers;

import com.github.java.demo.domain.Diet;
import com.github.java.demo.domain.Meal;
import com.github.java.demo.domain.Patient;
import com.github.java.demo.repositories.DietRepository;
import com.github.java.demo.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.Set;

public class DietRegistrationController {
    private final DietRepository dietRepository;
    private PatientsRepository patientsRepository;

    @Autowired
    public DietRegistrationController (DietRepository dietRepository, PatientsRepository patientsRepository) {
        this.dietRepository = dietRepository;
        this.patientsRepository = patientsRepository;

    }

    @GetMapping
    public String get(){

        return "redirect:/";

    }


    @PostMapping("/diet-registration")
    public String dietRegistration(String name, LocalDate startdate, Set<Meal> mealsList, String patient ){
        Diet goodluck= new Diet();
        goodluck.setName(name);
        goodluck.setStartdate(startdate);
        goodluck.setMealsList(mealsList);
        String[] split = patient.split(",");
        patientsRepository.findPatientByEmail(split[2]);

        dietRepository.save(goodluck);

        return"redirect:/";
    }




}
