package org.example.views;

import org.example.models.User;

public class LogOutState implements MenuState{
    @Override
    public void doCommand(User user) {
        if (user == null) {
            System.out.println("Вы не можете выйти из аккаунта, т.к. не вошли ни в какой аккаунт");
        } else {
            user = null;
        }
    }

    @Override
    public String getCommandInfo() {
        return "Выйти из аккаунта.";
    }
}
