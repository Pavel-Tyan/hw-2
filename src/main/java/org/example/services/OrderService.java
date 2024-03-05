package org.example.services;

import org.example.models.Meal;
import org.example.models.Order;
import org.example.models.User;

import java.util.ArrayList;

public interface OrderService {
    public boolean isOrderExists(String userName);
    void addMealInOrder(Meal meal, String userName, int maxPortionsCount);
    void addOrder(Order order);

    public double getOrderPrice(Order order);

    public Order findOrderByUserName(String userName);
    public ArrayList<Order> findAllOrders();

    public void updateOrder(Order order);

    public void deleteOrderByUserName(String userName);
}
