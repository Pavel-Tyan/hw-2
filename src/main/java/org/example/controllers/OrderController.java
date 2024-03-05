package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.models.Meal;
import org.example.models.Order;
import org.example.services.OrderService;

import java.util.ArrayList;

@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    public boolean isOrderExists(String userName) {
        return orderService.isOrderExists(userName);
    }

    public Order findOrderByUserName(String userName) {
        return orderService.findOrderByUserName(userName);
    }
    public ArrayList<Order> findAllOrders() {
        return orderService.findAllOrders();
    }

    public void addOrder(Order order) {
        orderService.addOrder(order);
    }

    public double getOrderPrice(Order order) {
        return orderService.getOrderPrice(order);
    }
    public void addMealInOrder(Meal meal, String userName, int maxPortionsCount) {
        orderService.addMealInOrder(meal, userName, maxPortionsCount);
    }
    public void updateOrder(Order order) {
        orderService.updateOrder(order);
    }

    public void deleteOrderByUserName(String userName) {
        orderService.deleteOrderByUserName(userName);
    }
}
