package org.example.services;

import org.example.models.Order;

import java.util.ArrayList;

public interface OrderService {
    public boolean isOrderExists(String userName);

    public Order findOrderByUserName(String userName);
    public ArrayList<Order> findAllOrders();

    public void updateOrder(Order order);

    public void deleteOrderByUserName(String userName);
}
