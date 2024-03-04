package org.example.repositories;

import lombok.Getter;
import org.example.models.Meal;

import java.io.*;
import java.util.ArrayList;


public class MealRepository {
    private final String fileName = "meals.dat";

    @Getter
    private ArrayList<Meal> meals = new ArrayList<Meal>();
    public MealRepository() {
        ArrayList<Meal> savedMeals = new ArrayList<Meal>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName)))
        {
            savedMeals = ((ArrayList<Meal>)inputStream.readObject());
            meals = savedMeals;
        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println("Десериализация объектов еды не удалась");
        }
    }

    public void serializeMeals() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName)))
        {
            oos.writeObject(meals);
        }
        catch(IOException ex){
            System.out.println("Сериализация еды не удалась");
        }
    }
}
