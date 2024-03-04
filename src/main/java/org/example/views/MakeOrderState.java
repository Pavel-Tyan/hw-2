package org.example.views;

import lombok.AllArgsConstructor;
import org.example.controllers.MealController;
import org.example.models.User;

import java.util.Scanner;

@AllArgsConstructor
public class MakeOrderState implements MenuState{
    MealController mealController;
    @Override
    public User doCommand(User user) {
        Scanner scanner = new Scanner(System.in);

        String mealName;
        int count;
        double orderPrice = 0;

        boolean hasCreatedOrder = false;

        while (!hasCreatedOrder) {
            System.out.print("Введите название блюда:");
            mealName = scanner.next();

            if (mealName == null || !mealController.isMealExists(mealName)) {
                System.out.println("Неверное название блюда.");
                continue;
            }

            System.out.print("Введите число порций: ");
            count = scanner.nextInt(); //Отлови ошибку ввода

            orderedMeal

        }


    }

    @Override
    public String getCommandInfo() {
        return "Сделать заказ";
    }
}
