package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.exceptions.ResourceNotFoundException;
import org.example.models.Meal;
import org.example.models.Order;
import org.example.services.MealService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class MealController {
    private MealService mealService;
    public boolean isMealExists(String name) {
        try {
            mealService.findMealByName(name);
        } catch (ResourceNotFoundException e) {
            return false;
        }

        return true;
    }
    public void addNewMeal(Meal meal) {
        mealService.addNewMeal(meal);
    }

    public void updateMeal(Meal meal) {
        mealService.updateMeal(meal);
    }

    public void deleteMealByName(String name) {
        mealService.deleteMealByName(name);
    }

    public Meal findMealByName(String name) {
        return mealService.findMealByName(name);
    }
    public List<Meal> findAllMeals() {
        return mealService.findAllMeals();
    }

}
