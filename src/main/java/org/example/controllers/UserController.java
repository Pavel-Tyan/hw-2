package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.models.User;
import org.example.services.UserService;

@AllArgsConstructor
public class UserController {
    private UserService userService;

    public void addNewUser(User user) {
        userService.addNewUser(user);
    }
    // Ошибки отлови в контроллерах

    public boolean isUserExists(User user) {
        return userService.isUserExists(user);
    }
}
