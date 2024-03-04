package org.example.views;

import org.example.models.User;

public class ExitState implements MenuState{

    @Override
    public User doCommand(User user) {
        Runtime.getRuntime().exit(0);
        return user;
    }

    @Override
    public String getCommandInfo() {
        return "Закрыть программу";
    }
}
