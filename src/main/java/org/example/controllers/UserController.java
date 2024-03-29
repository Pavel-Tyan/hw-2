package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.models.User;
import org.example.services.UserService;

@AllArgsConstructor
public class UserController {
    private UserService userService;

    public User getUserByName(String userName) {
        return userService.getUserByName(userName);
    }

    public void addNewUser(User user) {
        userService.addNewUser(user);
    }

    public boolean isUserExists(User user) {
        return userService.isUserExists(user);
    }
}
