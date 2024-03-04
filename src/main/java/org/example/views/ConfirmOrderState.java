package org.example.views;

import org.example.models.User;

public class ConfirmOrderState implements MenuState {
    @Override
    public User doCommand(User user) {
        return null;
    }

    @Override
    public String getCommandInfo() {
        return "Подтвердить и оплатить заказ.";
    }
}
