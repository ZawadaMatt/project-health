package com.github.java.demo.controllers;

import com.github.java.demo.domain.Ingredient;
import com.github.java.demo.repositories.DietRepository;
import com.github.java.demo.repositories.DieticanRepository;
import com.github.java.demo.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
public class IngredientRegistrationController {

    private IngredientRepository ingredientRepository;
    private DieticanRepository dieticanRepository;

    @Autowired
    public IngredientRegistrationController(IngredientRepository ingredientRepository, DieticanRepository dieticanRepository) {
        this.ingredientRepository = ingredientRepository;
        this.dieticanRepository = dieticanRepository;
    }

    @GetMapping("/ingredient-register")
    public String addIngredient(Model model, @RequestParam(defaultValue = "0", required = false) String search) {
        model.addAttribute("dietician", dieticanRepository
                .findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        if (search.equalsIgnoreCase("0")) {
            model.addAttribute("ingredients", ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        } else {
            model.addAttribute("ingredients", ingredientRepository.findIngredientByName(search));
        }
        return "ingredient-register";
    }

    @Transactional
    @PostMapping("/ingredient-register")
    public String ingredientRegistration(String name, String calories, String protein, String fats, String carbs, String salt, String weight, String category) {
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
        ingredientRepository.flush();
        return "redirect:/";
    }

}
