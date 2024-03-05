package org.example.views.menu.states.implementations;

import lombok.AllArgsConstructor;
import org.example.controllers.MealController;
import org.example.models.Meal;
import org.example.models.User;
import org.example.views.menu.states.MenuState;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

@AllArgsConstructor
public class AddNewMealState implements MenuState {
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
        double price = 0;
        int count = 0;
        int cookingTimeMinutes = 0;

        while (!hasAdded) {
            System.out.println("Введите данные о новом блюде");


            System.out.print("Название блюда: ");

            scanner.next();
            name = scanner.next();

            System.out.println();

            System.out.print("Цена за 1 порцию: ");

            try {
                price = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод данных.");
                continue;
            }


            System.out.println();

            System.out.print("Количество порций: ");

            try {
                count = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод данных.");
            }

            System.out.println();

            System.out.print("Время готовки в минутах: ");

            try {
                cookingTimeMinutes = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод данных.");
            }

            Meal meal = new Meal(price, name, count, cookingTimeMinutes);
            mealController.addNewMeal(meal);
            hasAdded = true;
            System.out.println("Данные о блюде успешно добавлены.");
        }

        return user;
    }

    @Override
    public String getCommandInfo() {
        return "Добавить в меню новое блюдо";
    }
}
