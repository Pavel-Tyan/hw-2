package org.example.views.menu.states.implementations;

import org.example.models.User;
import org.example.views.menu.states.MenuState;

public class OrderState implements MenuState {

    @Override
    public User doCommand(User user) {
        if (user == null) {
            System.out.println("Сначала войдите в аккаунт.");
            System.out.println();
            return user;
        }
        return user; // sdasd
    }

    @Override
    public String getCommandInfo() {
        return "Сделать заказ.";
    }
}
