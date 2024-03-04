package org.example.services;

import org.example.models.Meal;

import java.util.List;

public interface MealService {
    Meal findMealByName(String name);
    List<Meal> findAllMeals();
    void addNewMeal(Meal meal);
    void deleteMealByName(String name);
    void updateMeal(Meal meal);
}
