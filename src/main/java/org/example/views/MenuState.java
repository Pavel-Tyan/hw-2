package org.example.views;

import org.example.models.User;

public interface MenuState {
    void doCommand(User user);
    String getCommandInfo();
}
