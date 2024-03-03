package org.example.views;

public interface MenuState {
    void doCommand(boolean hasLogged);
    String getCommandInfo();
}
