package com.github.java.demo.repositories;

import com.github.java.demo.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    Meal findByName(String name);
    Meal findMealById(Long id);
}
