package org.example.views;

import lombok.AllArgsConstructor;
import org.example.controllers.MealController;
import org.example.models.Meal;
import org.example.models.User;

import java.util.Scanner;

@AllArgsConstructor
public class UpdateMealState implements MenuState{
    private MealController mealController;
    @Override
    public User doCommand(User user) {
        if (user == null || !user.isAdmin()) {
            System.out.println("Вы не вошли в аккаунт либо у вас недостаточно прав для этой операции.");
            return user;
        }

        boolean hasUpdated = false;
        Scanner scanner = new Scanner(System.in);

        String name;
        double price;
        int count;
        int cookingTimeMinutes;

        while (!hasUpdated) {
            try {
                System.out.println("Введите данные о блюде, которое хотите изменить");

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
                mealController.updateMeal(meal);
                System.out.println("Данные о блюде успешно обновлены");
                hasUpdated = true;
            } catch (Exception ex) {
                throw ex;
            }
        }

        return user;
    }

    @Override
    public String getCommandInfo() {
        return "Обновить данные о блюде.";
    }
}
