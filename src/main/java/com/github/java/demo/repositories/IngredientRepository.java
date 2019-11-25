package com.github.java.demo.repositories;

import com.github.java.demo.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository <Ingredient, Long> {
}
