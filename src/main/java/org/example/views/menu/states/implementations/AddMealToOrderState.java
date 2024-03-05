package org.example.views.menu.states.implementations;

import lombok.AllArgsConstructor;
import org.example.controllers.MealController;
import org.example.controllers.OrderController;
import org.example.exceptions.ResourceNotEnoughException;
import org.example.models.Meal;
import org.example.models.Order;
import org.example.models.User;
import org.example.views.menu.states.MenuState;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

@AllArgsConstructor
public class AddMealToOrderState implements MenuState {
    OrderController orderController;
    MealController mealController;
    @Override
    public User doCommand(User user) {
        if (user == null) {
            System.out.println("Сначала войдите в аккаунт.");
            System.out.println();
            return user;
        }

        Scanner scanner = new Scanner(System.in);

        String mealName;
        int count;
        double price = 0;

        boolean hasCreatedOrder = false;

        while (!hasCreatedOrder) {
            System.out.print("Введите название блюда:");
            mealName = scanner.next();

            if (mealName == null || !mealController.isMealExists(mealName)) {
                System.out.println("Неверное название блюда.");
                return user;
            }

            System.out.print("Введите число порций: ");

            try {
                count = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод данных.");
                return user;
            }

            Meal currentMeal = mealController.findMealByName(mealName);

            Meal orderedMeal = new Meal(
                currentMeal.getPrice(),
                currentMeal.getName(),
                count,
                currentMeal.getCookingTimeMinutes()
            );

            Order currentOrder = new Order();
            if (orderController.isOrderExists(user.getLogin())) {
                Order savedOrder = orderController.findOrderByUserName(user.getLogin());

                ArrayList<Meal> updatedOrderedMeals = new ArrayList<Meal>();
                for (Meal meal: savedOrder.getOrderedMeals()) {
                    updatedOrderedMeals.add(new Meal(
                            meal.getPrice(),
                            meal.getName(),
                            meal.getCount(),
                            meal.getCookingTimeMinutes()
                    ));
                }
                currentOrder = new Order(
                        savedOrder.getCustomer(),
                        updatedOrderedMeals
                );
                orderController.deleteOrderByUserName(user.getLogin());
            } else {
                currentOrder = new Order(
                        user,
                        new ArrayList<Meal>()
                );
            }

            orderController.addOrder(currentOrder);

            try {
                orderController.addMealInOrder(orderedMeal,
                        user.getLogin(), currentMeal.getCount());
                System.out.println("Блюдо добавлено в заказ.");
                System.out.println();
                return user;
            } catch (ResourceNotEnoughException ex) {
                System.out.println(ex.getMessage());
                return user;
            }
        }

        return user;
    }

    @Override
    public String getCommandInfo() {
        return "Добавить блюдо в заказ";
    }
}
