package org.example;

import java.util.List;

public interface MealMenuService {
    public Meal findMeal(String mealName);
    public List<Meal> findAllMeals();
    public Meal addNewMeal(Meal meal);
    public void deleteMeal(Meal meal);

    public Meal updateMeal(Meal meal);
}
