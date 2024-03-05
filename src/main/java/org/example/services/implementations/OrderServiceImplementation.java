package org.example.services.implementations;

import lombok.AllArgsConstructor;
import org.example.exceptions.ResourceAlreadyExistsException;
import org.example.exceptions.ResourceNotEnoughException;
import org.example.exceptions.ResourceNotFoundException;
import org.example.models.Meal;
import org.example.models.Order;
import org.example.repositories.OrderRepository;
import org.example.services.OrderService;

import java.util.ArrayList;
import java.util.Objects;

@AllArgsConstructor
public class OrderServiceImplementation implements OrderService {
    private OrderRepository orderRepository;
    @Override
    public boolean isOrderExists(String userName) {
        for (Order currentOrder: orderRepository.getOrders()) {
            if (userName.equals(currentOrder.getCustomer().getLogin())) {
                return true;
            }
        }

        return false;
    }

    public double getOrderPrice(Order order) {
        double price = 0;

        for (Meal meal: order.getOrderedMeals()) {
            price += meal.getCount() * meal.getPrice();
        }

        return price;
    }
    public void addOrder(Order order) {
        for (Order currentOrder: orderRepository.getOrders()) {
            if (Objects.equals(order.getCustomer().getLogin(),
                    currentOrder.getCustomer().getLogin())) {
                throw new ResourceAlreadyExistsException("Такой заказ уже существует");
            }
        }

        orderRepository.getOrders().add(order);
    }

    @Override
    public void addMealInOrder(Meal meal, String userName, int maxPortionsCount) {
        String mealName = meal.getName();

        for (Order currentOrder: orderRepository.getOrders()) {

            String currentUserName = currentOrder.getCustomer().getLogin();

            if (currentUserName.equals(userName)) {
                for (Meal currentMeal: currentOrder.getOrderedMeals()) {
                    if (currentMeal.getName().equals(mealName) &&
                    currentMeal.getCount() + meal.getCount() > maxPortionsCount) {
                        throw new ResourceNotEnoughException("Недостаточно порций.");
                    }

                    Meal updatedMeal = new Meal(
                            meal.getPrice(),
                            meal.getName(),
                            meal.getCount() + currentMeal.getCount(),
                            meal.getCookingTimeMinutes()
                    );

                    currentOrder.getOrderedMeals().remove(currentMeal);
                    currentOrder.getOrderedMeals().add(updatedMeal);
                    return;
                }

                if (meal.getCount() > maxPortionsCount) {
                    throw new ResourceNotEnoughException("Недостаточно порций.");
                }

                Meal updatedMeal = new Meal(
                        meal.getPrice(),
                        meal.getName(),
                        meal.getCount(),
                        meal.getCookingTimeMinutes()
                );

                currentOrder.getOrderedMeals().add(updatedMeal);
                return;
            }
        }
    }

    @Override
    public Order findOrderByUserName(String userName) {
        for (Order currentOrder: orderRepository.getOrders()) {
            if (userName.equals(currentOrder.getCustomer().getLogin())) {
                return currentOrder;
            }
        }

        throw new ResourceNotFoundException("Пользователя не существует либо он не делал заказ.");
    }

    @Override
    public ArrayList<Order> findAllOrders() {
        return orderRepository.getOrders();
    }

    @Override
    public void updateOrder(Order order) {
        for (Order currentOrder: orderRepository.getOrders()) {
            if (order.getCustomer().getLogin().equals(currentOrder.getCustomer().getLogin())) {
                orderRepository.getOrders().remove(currentOrder);
                orderRepository.getOrders().add(order);
                return;
            }
        }

        throw new ResourceNotFoundException("Пользователя не существует либо он не делал заказ.");
    }

    @Override
    public void deleteOrderByUserName(String userName) {
        for (Order currentOrder: orderRepository.getOrders()) {
            if (userName.equals(currentOrder.getCustomer().getLogin())) {
                orderRepository.getOrders().remove(currentOrder);
                return;
            }
        }

        throw new ResourceNotFoundException("Пользователя не существует либо он не делал заказ.");
    }
}
