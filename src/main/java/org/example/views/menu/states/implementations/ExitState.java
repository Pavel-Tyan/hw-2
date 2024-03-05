package org.example.views.menu.states.implementations;

import org.example.models.User;
import org.example.views.menu.states.MenuState;

public class ExitState implements MenuState {

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
