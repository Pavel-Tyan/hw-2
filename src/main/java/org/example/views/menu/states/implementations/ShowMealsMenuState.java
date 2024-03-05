package org.example.views.menu.states.implementations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.controllers.MealController;
import org.example.models.Meal;
import org.example.models.User;
import org.example.views.menu.states.MenuState;

@AllArgsConstructor
@Getter
public class ShowMealsMenuState implements MenuState {
    private MealController mealController;
    @Override
    public User doCommand(User user) {
        for (Meal meal : mealController.findAllMeals()) {
            System.out.println(String.format("Название: %s, цена: %f, количество: %d, " +
                    "время готовки в минутах: %d",
                    meal.getName(),
                    meal.getPrice(),
                    meal.getCount(),
                    meal.getCookingTimeMinutes()));
        }

        return user;
    }

    @Override
    public String getCommandInfo() {
        return "Показать меню блюд";
    }
}
