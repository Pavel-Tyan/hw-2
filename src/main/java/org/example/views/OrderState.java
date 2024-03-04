package org.example.views;

import org.example.models.User;

public class OrderState implements MenuState{

    @Override
    public User doCommand(User user) {
        return user; // sdasd
    }

    @Override
    public String getCommandInfo() {
        return "Сделать заказ.";
    }
}
