package com.github.java.demo.controllers;

import com.github.java.demo.domain.Ingredient;
import com.github.java.demo.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientRegistrationController {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientRegistrationController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }



    @PostMapping("/ingredient-register")
    public String ingredientRegistration(String name, String calories, String protein, String fats, String carbs, String salt, String weight, String category){
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setCalories(Double.parseDouble(calories));
        ingredient.setProtein(Double.parseDouble(protein));
        ingredient.setFats(Double.parseDouble(fats));
        ingredient.setCarbs(Double.parseDouble(carbs));
        ingredient.setFats(Double.parseDouble(fats));
        ingredient.setSalt(Double.parseDouble(salt));
        ingredient.setWeight(Double.parseDouble(weight));
        ingredient.setCategory(category);
        ingredientRepository.save(ingredient);
       

        return "redirect:/";
    }
}
