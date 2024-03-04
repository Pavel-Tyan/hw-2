package org.example.views;

import lombok.AllArgsConstructor;
import org.example.controllers.MealController;
import org.example.models.Meal;
import org.example.models.User;

import java.util.Scanner;

@AllArgsConstructor
public class AddNewMealState implements MenuState{
    private MealController mealController;
    @Override
    public User doCommand(User user) {
        if (user == null || !user.isAdmin()) {
            System.out.println("Вы не вошли в аккаунт либо у вас недостаточно прав для этой операции.");
            return user;
        }

        boolean hasAdded = false;
        Scanner scanner = new Scanner(System.in);

        String name;
        double price;
        int count;
        int cookingTimeMinutes;

        while (!hasAdded) {
            try {
                System.out.println("Введите данные о новом блюде");

                System.out.print("Название блюда: ");
                name = scanner.next();

                System.out.println();

                System.out.print("Цена за 1 порцию: ");
                price = scanner.nextDouble();

                System.out.println();

                System.out.print("Количество порций: ");
                count = scanner.nextInt();

                System.out.println();

                System.out.print("Время готовки в минутах: ");
                cookingTimeMinutes = scanner.nextInt();

                Meal meal = new Meal(price, name, count, cookingTimeMinutes);
                mealController.addNewMeal(meal);
                hasAdded = true;
            } catch (Exception ex) {
                throw ex;
            }
        }

        return user;
    }

    @Override
    public String getCommandInfo() {
        return "Добавить в меню новое блюдо";
    }
}
