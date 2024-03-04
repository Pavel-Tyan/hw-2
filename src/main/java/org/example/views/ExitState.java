package org.example.views;

import org.example.models.User;

public class ExitState implements MenuState{

    @Override
    public void doCommand(User user) {
        Runtime.getRuntime().exit(0);
    }

    @Override
    public String getCommandInfo() {
        return "Закрыть программу";
    }
}
