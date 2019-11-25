package com.github.java.demo.controllers;

import com.github.java.demo.domain.Ingredient;
import com.github.java.demo.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientRegistrationController {


    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientRegistrationController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/ingredient-register")
    public String preprareRegistrationPage () {
        return "WEB-INF/jsp/ingredient-registration-page.jsp";
    }

    @PostMapping("/ingredient-register")
    public String processRegistrationPage (String name, Double calories, Double protein, Double fats, Double carbs, Double salt, Double weight, String category) {
        Ingredient ingredient = new Ingredient();

        ingredient.setName(name);
        ingredient.setCategory(category);
        ingredient.setCalories(calories);
        ingredient.setProtein(protein);
        ingredient.setFats(fats);
        ingredient.setCarbs(carbs);
        ingredient.setSalt(salt);
        ingredient.setWeight(weight);

        return "WEB-INF/jsp/ingredient-registration-page.jsp";
    }





}
