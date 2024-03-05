package org.example;
import org.example.models.Meal;
import org.example.models.Profit;
import org.example.models.User;
import org.example.views.RestaurantFacade;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat")))
        {
            ArrayList<User> users = new ArrayList<>();
            users.add(new User( "admin", "admin"));
            oos.writeObject(users);
        }
        catch (IOException ex) {
            System.out.println("Сериализация пользователей не удалась");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
//
//        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("meals.dat")))
//        {
//            List<Meal> meals = new ArrayList<Meal>();
//            meals.add(new Meal(20, "Сырная пицца", 30, 20));
//            meals.add(new Meal(20, "Мексиканская пицца", 20, 20));
//            meals.add(new Meal(15, "Бургер", 35, 25));
//            oos.writeObject(meals);
//        }
//        catch(IOException ex){
//            System.out.println("Сериализация еды не удалась");
//        }
//        Profit profit = new Profit();
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("profit.dat")))
//        {
//            oos.writeObject(profit);
//        }
//        catch (IOException ex) {
//            System.out.println("Сериализация информации о прибыли не удалась");
//        }

        try {
            RestaurantFacade restaurantFacade = new RestaurantFacade();
            restaurantFacade.runApp();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
//        RestaurantFacade restaurantFacade = new RestaurantFacade();
//        restaurantFacade.runApp();
    }
}