package org.example.views.menu.states.implementations;

import lombok.AllArgsConstructor;
import org.example.controllers.OrderController;
import org.example.models.Meal;
import org.example.models.Order;
import org.example.models.User;
import org.example.views.menu.states.MenuState;

@AllArgsConstructor
public class ShowOrderState implements MenuState {
    private OrderController orderController;
    @Override
    public User doCommand(User user) {
        if (user == null) {
            System.out.println("Сначала войдите в аккаунт.");
            System.out.println();
            return user;
        }

        if (!orderController.isOrderExists(user.getLogin())) {
            System.out.println("Вы не выбрали блюда для заказа");
            return user;
        }

        Order currentOrder = orderController.findOrderByUserName(user.getLogin());
        for (Meal orderedMeal: currentOrder.getOrderedMeals()) {
            System.out.printf("Название блюда: %s, количество порций: %d",
                    orderedMeal.getName(), orderedMeal.getCount());
            System.out.println();
        }
        System.out.printf("Цена заказа: %f", orderController.getOrderPrice(currentOrder));
        return user;
    }

    @Override
    public String getCommandInfo() {
        return "Показать содержимое заказа перед подтверждением заказа";
    }
}
