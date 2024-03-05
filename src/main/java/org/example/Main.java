package org.example;
import org.example.models.User;
import org.example.views.RestaurantFacade;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class Main {
    public static void main(String[] args) {
        RestaurantFacade restaurantFacade = new RestaurantFacade();
        restaurantFacade.runApp();
    }
}