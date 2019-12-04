package com.github.java.demo.repositories;

import com.github.java.demo.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository <Ingredient, Long> {
    Ingredient findIngredientByName (String name);
    Ingredient findIngredientByCategory(String category);








}
