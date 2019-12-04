package com.github.java.demo.controllers;

import com.github.java.demo.domain.Diet;
import com.github.java.demo.domain.Meal;
import com.github.java.demo.domain.Patient;
import com.github.java.demo.repositories.DietRepository;
import com.github.java.demo.repositories.MealRepository;
import com.github.java.demo.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DietRegistrationController {
    private final DietRepository dietRepository;
    private PatientsRepository patientsRepository;
    private MealRepository mealRepository;

    @Autowired
    public DietRegistrationController(DietRepository dietRepository, PatientsRepository patientsRepository, MealRepository mealRepository) {
        this.dietRepository = dietRepository;
        this.patientsRepository = patientsRepository;
        this.mealRepository = mealRepository;

    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute("meals", mealRepository.findAll());
        return "redirect:/";

    }

    @PostMapping("/diet-registration")
    public String dietRegistration(String name, LocalDate startdate, String mealsList, String patient) {
        Diet goodluck = new Diet();
        goodluck.setName(name);
        goodluck.setStartdate(startdate);
        String[] splitMeals = mealsList.split(",");
        Set<Meal> readyMealList = Arrays.stream(splitMeals).map(s -> mealRepository.findByName(s)).collect(Collectors.toSet());
        goodluck.setMealsList(readyMealList);
        String[] split = patient.split(",");
        patientsRepository.findPatientByEmail(split[2]);
        dietRepository.save(goodluck);
        return "redirect:/";
    }


}
