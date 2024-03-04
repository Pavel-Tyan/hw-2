package org.example.services.implementations;

import lombok.AllArgsConstructor;
import org.example.exceptions.ResourceNotFoundException;
import org.example.models.Order;
import org.example.repositories.OrderRepository;
import org.example.services.OrderService;

import java.util.ArrayList;

@AllArgsConstructor
public class OrderServiceImplementation implements OrderService {
    private OrderRepository orderRepository;
    @Override
    public boolean isOrderExists(String userName) {
        return false;
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
