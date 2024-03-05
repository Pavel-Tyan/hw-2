package org.example.views.menu.states.implementations;

import lombok.AllArgsConstructor;
import org.example.controllers.MealController;
import org.example.models.User;
import org.example.views.menu.states.MenuState;

import java.util.Scanner;

@AllArgsConstructor
public class DeleteMealState implements MenuState {
    private MealController mealController;
    @Override
    public User doCommand(User user) {
        if (user == null || !user.isAdmin()) {
            System.out.println("Вы не вошли в аккаунт либо у вас недостаточно прав для этой операции.");
            return user;
        }

        Scanner scanner = new Scanner(System.in);
        String name;
        boolean hasDeleted = false;

        while (!hasDeleted) {
            System.out.println("Введите название блюда, которое хотите удалить: ");
            name = scanner.next();

            if (name == null) {
                System.out.println("Вы не ввели имя.");
            } else {
                mealController.deleteMealByName(name);
                hasDeleted = true;
            }
        }

        return user;
    }

    @Override
    public String getCommandInfo() {
        return "Удалить блюдо из меню.";
    }
}
