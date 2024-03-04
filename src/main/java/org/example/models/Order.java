package org.example.models;


import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Order {
    User customer;
    ArrayList<Meal> orderedMeals;
}
