package org.example.views;

public class ExitState implements MenuState{

    @Override
    public void doCommand(boolean hasLogged) {
        Runtime.getRuntime().exit(0);
    }

    @Override
    public String getCommandInfo() {
        return "Закрыть программу";
    }
}
