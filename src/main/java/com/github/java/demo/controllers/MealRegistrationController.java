package com.github.java.demo.controllers;

import com.github.java.demo.domain.Meal;
import com.github.java.demo.repositories.DieticanRepository;
import com.github.java.demo.repositories.IngredientRepository;
import com.github.java.demo.repositories.MealRepository;
import com.github.java.demo.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class MealRegistrationController {
    private final MealRepository mealRepository;
    private final IngredientRepository ingredientRepository;
    private DieticanRepository dieticanRepository;
    private MealService mealService;

    @Autowired
    public MealRegistrationController(MealService mealService, MealRepository mealRepository, IngredientRepository ingredientRepository, DieticanRepository dieticanRepository) {
        this.mealRepository = mealRepository;
        this.ingredientRepository = ingredientRepository;
        this.dieticanRepository = dieticanRepository;
        this.mealService = mealService;
    }

    @GetMapping("/meal-register")
    public String preprareRegistrationPage(Model model) {
        model.addAttribute("ingredient", ingredientRepository.findAll());
        model.addAttribute("dietican", dieticanRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("mealCard", mealService.showIngredientsInHtml());
        return "meal-register";
    }

    @PostMapping("/meal-register")
    public String processRegistrationPage(String name, String ingrediens, String recipt) {
        Meal meal = new Meal();
        meal.setName(name);
        String[] split = ingrediens.split(",");
        meal.setIngredients(Arrays.stream(split).map(s -> ingredientRepository.findIngredientByName(s)).collect(Collectors.toSet()));
        meal.setRecipt(recipt);
        mealRepository.save(meal);

        return "/meal-register";
    }
    @PostMapping("/meal-add")
    public String mealAdder(String id){
        mealService.addIngredientToCard(Long.valueOf(id));
        return "redirect:/";
    }
    @PostMapping("/meal-minus")
    public String mealMinuser(String id){
        mealService.deleteIngredientFromCard(ingredientRepository.findIngredientById(Long.valueOf(id)));
        return "redirect:/";
    }


}
