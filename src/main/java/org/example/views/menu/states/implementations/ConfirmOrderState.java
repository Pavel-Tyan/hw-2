package org.example.views.menu.states.implementations;

import lombok.AllArgsConstructor;
import org.example.controllers.MealController;
import org.example.controllers.OrderController;
import org.example.controllers.ProfitController;
import org.example.models.Meal;
import org.example.models.Order;
import org.example.models.Profit;
import org.example.models.User;
import org.example.views.menu.states.MenuState;

import java.util.ArrayList;

@AllArgsConstructor
public class ConfirmOrderState implements MenuState {
    public class CookingThread extends Thread{
        public CookingThread(String name, MealController mealController, Meal meal){
            super(name);
            this.mealController = mealController;
            this.meal = meal;
        }

        private MealController mealController;
        private Meal meal;
        public void run() {
            Meal savedMeal = mealController.findMealByName(meal.getName());
            Meal updatedMeal = new Meal(
                    meal.getPrice(),
                    meal.getName(),
                    savedMeal.getCount() - meal.getCount(),
                    meal.getCookingTimeMinutes()
            );
            synchronized (mealController.findAllMeals()) {
                mealController.updateMeal(updatedMeal);
            }
        }
    }
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
        double totalPrice = orderController.getOrderPrice(currentOrder);

        ArrayList<CookingThread> cookingThreads = new ArrayList<CookingThread>();

        for (Meal meal: currentOrder.getOrderedMeals()) {
            CookingThread currentThread = new CookingThread(meal.getName(), mealController, meal);
            cookingThreads.add(currentThread);
            currentThread.run();
        }

        for (CookingThread currentThread: cookingThreads) {
            try {
                currentThread.join();
            } catch (InterruptedException ex) {
                System.out.println("Во время готовки что-то пошло не так...");
                continue;
            }
        }

        System.out.println("Заказ приготовлен.");
        for (Meal orderedMeal: currentOrder.getOrderedMeals()) {
            System.out.printf("Название блюда: %s, количество порций: %d",
                    orderedMeal.getName(), orderedMeal.getCount());
            System.out.println();
        }

        orderController.deleteOrderByUserName(user.getLogin());

        profitController.updateProfit(new Profit(profitController.getProfit().getMoney() + totalPrice));
        profitController.saveProfit();
        return user;
    }

    @Override
    public String getCommandInfo() {
        return "Подтвердить и оплатить заказ";
    }
}
