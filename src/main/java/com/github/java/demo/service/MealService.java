package com.github.java.demo.service;


import com.github.java.demo.domain.Ingredient;
import com.github.java.demo.repositories.DieticanRepository;
import com.github.java.demo.repositories.IngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MealService {

    private Map<Ingredient, Integer> session = new HashMap<>();
    private IngredientRepository ingredientRepository;
    private DieticanRepository dieticanRepository;
    private Logger logger = LoggerFactory.getLogger(MealService.class.getName());

    @Autowired
    public MealService(IngredientRepository ingredientRepository, DieticanRepository dieticanRepository) {
        this.ingredientRepository = ingredientRepository;
        this.dieticanRepository = dieticanRepository;
    }

    public void addIngredientToCard(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findIngredientById(ingredientId);

        if (session.containsKey(ingredient)) {
            session.put(ingredient, session.get(ingredient) + 1);
        } else {
            session.put(ingredient, 1);
        }
    }

    public void deleteIngredientFromCard(Ingredient ingredient) {
        if (session.isEmpty()) {
            logger.info("Brak posiłku");
        } else if (session.containsKey(ingredient)) {
            session.replace(ingredient, session.get(ingredient) - 1);
            if (session.get(ingredient) == 0) {
                session.remove(ingredient);
            }
        }
    }

    public String showIngredientsInHtml() {
        return session.entrySet().stream()
                .map(i -> i.getKey().getName() + " " + i.getValue())
                .collect(Collectors.joining(","));
    }

    public Map<Ingredient, Integer> getSession() {
        return session;
    }

}
