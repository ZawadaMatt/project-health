package com.github.java.demo.controllers;

import com.github.java.demo.domain.Ingredient;
import com.github.java.demo.domain.Meal;
import com.github.java.demo.repositories.IngredientRepository;
import com.github.java.demo.repositories.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class MealRegistrationController {
    private final MealRepository mealRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public MealRegistrationController (MealRepository mealRepository, IngredientRepository ingredientRepository) {
        this.mealRepository = mealRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/meal-register")
    public String preprareRegistrationPage (){

        return "redirect:/ making-meal";
    }

    @PostMapping("/meal-register")
    public String processRegistrationPage (String name, String ingrediens, String recipt) {
        Meal meal = new Meal();
        meal.setName(name);
        String[] split = ingrediens.split(",");
        meal.setIngredients(Arrays.stream(split).map(s -> ingredientRepository.findIngredientByName(s)).collect(Collectors.toSet()));
        meal.setRecipt(recipt);
        mealRepository.save(meal);


        return"/meal-register" ;
    }


}
