package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.exceptions.ResourceNotFoundException;
import org.example.models.Meal;
import org.example.services.MealService;

import java.util.List;

@AllArgsConstructor
public class MealController {
    private MealService mealMenuService;

    public boolean isMealExists(String name) {
        try {
            mealMenuService.findMealByName(name);
        } catch (ResourceNotFoundException e) {
            return false;
        }

        return true;
    }

    public List<Meal> findAllMeals() {
        return mealMenuService.findAllMeals();
    }

}
