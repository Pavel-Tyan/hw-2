package org.example.repositories;
import org.example.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class UserRepository {
    final String fileName = "users.dat";

    @Getter
    private ArrayList<User> users = new ArrayList<User>();;
    public UserRepository() {
        ArrayList<User> savedUsers;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName)))
        {
            savedUsers = ((ArrayList<User>)inputStream.readObject());
            users = savedUsers;
        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println("Десериализация пользователей не удалась");
        }
    }

    public void serializeUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName)))
        {
            oos.writeObject(users);
        }
        catch (IOException ex) {
            System.out.println("Сериализация пользователей не удалась");
        }
    }
}
