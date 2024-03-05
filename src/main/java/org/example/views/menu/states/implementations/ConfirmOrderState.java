package org.example.views.menu.states.implementations;

import lombok.AllArgsConstructor;
import org.example.controllers.MealController;
import org.example.controllers.OrderController;
import org.example.controllers.ProfitController;
import org.example.models.Order;
import org.example.models.User;
import org.example.views.menu.states.MenuState;

@AllArgsConstructor
public class ConfirmOrderState implements MenuState {
    private ProfitController profitController;
    private MealController mealController;
    private OrderController orderController;

    @Override
    public User doCommand(User user) {
        if (user == null) {
            System.out.println("Сначала войдите в аккаунт.");
            System.out.println();
            return user;
        }

        if (!orderController.isOrderExists(user.getLogin()) &&
                orderController.findOrderByUserName(user.getLogin()).getOrderedMeals().isEmpty()) {
            System.out.println("Вы не выбрали блюда для заказа");
        }

        Order currentOrder = orderController.findOrderByUserName(user.getLogin());

        // Сериализуй профит
        profitController.saveProfit();
        // Поменяй профит.
        return null;
    }

    @Override
    public String getCommandInfo() {
        return "Подтвердить и оплатить заказ.";
    }
}
