package org.example.services;

import org.example.models.User;

import java.util.List;

public interface UserService {
    boolean isUserExists(User user);
    List<User> findAllUsers();
    void addNewUser(User user);

    User getUserByName(String userName);
}
