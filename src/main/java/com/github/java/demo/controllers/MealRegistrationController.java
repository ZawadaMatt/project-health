package com.github.java.demo.controllers;

import com.github.java.demo.domain.Meal;
import com.github.java.demo.repositories.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;

@Controller
@RequestMapping
public class MealRegistrationController {
    private final MealRepository mealRepository;

@Autowired
    public MealRegistrationController (MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @GetMapping

    public String preprareRegistrationPage () {
        return "WEB-INF/jsp/meal-registration-page.jsp";
    }

    @PostMapping
    public String processRegistrationPage (String name, HashSet ingrediens, String recipt) {
        Meal meal = new Meal();
        meal.setName(name);
        meal.setIngredients(ingrediens);
        meal.setRecipt(recipt);



        return "WEB-INF/jsp/meal-registration-page.jsp";
    }


}
