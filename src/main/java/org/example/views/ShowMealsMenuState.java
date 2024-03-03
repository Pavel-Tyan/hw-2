package org.example.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.controllers.MealController;
import org.example.models.Meal;

import java.util.List;

@AllArgsConstructor
@Getter
public class ShowMealsMenuState implements MenuState{
    private MealController mealController;
    @Override
    public void doCommand(boolean hasLogged) {
        for (Meal meal : mealController.findAllMeals()) {
            System.out.println(String.format("Название: %s, цена: %f, количество: %d, " +
                    "время готовки в минутах: $d",
                    meal.getName(),
                    meal.getPrice(),
                    meal.getCount(),
                    meal.getCookingTimeMinutes()));
        }

        if (!hasLogged) {
            System.out.println();
            System.out.println("Чтобы сделать заказ, зарегистрируйтесь!");
            System.out.println();
        }
    }

    @Override
    public String getCommandInfo() {
        return "Показать меню блюд";
    }
}
