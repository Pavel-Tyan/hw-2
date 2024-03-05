package org.example.views.menu.states.implementations;

import lombok.AllArgsConstructor;
import org.example.controllers.MealController;
import org.example.models.Meal;
import org.example.models.User;
import org.example.views.menu.states.MenuState;

import java.util.InputMismatchException;
import java.util.Scanner;

@AllArgsConstructor
public class UpdateMealState implements MenuState {
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

                try {
                    price = scanner.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("Некорректный ввод данных.");
                    return user;
                }

                System.out.println();

                System.out.print("Количество порций: ");

                try {
                    count = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Некорректный ввод данных.");
                    return user;
                }

                System.out.println();

                System.out.print("Время готовки в минутах: ");

                try {
                    cookingTimeMinutes = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Некорректный ввод данных.");
                    return user;
                }

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
        return "Обновить данные о блюде";
    }
}
