package org.example.services;

import org.example.models.Meal;

import java.util.List;

public interface MealMenuService {
    Meal findMeal(String mealName);
    List<Meal> findAllMeals();
    Meal addNewMeal(Meal meal);
    void deleteMeal(Meal meal);
    Meal updateMeal(Meal meal);
}
