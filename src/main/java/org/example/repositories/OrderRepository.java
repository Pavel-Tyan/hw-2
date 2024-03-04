package org.example.repositories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.models.Order;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
public class OrderRepository {
    @Getter
    private ArrayList<Order> orders = new ArrayList<Order>();
}
