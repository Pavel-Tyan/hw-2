package org.example.views;

import org.example.models.User;

public interface MenuState {
    User doCommand(User user);
    String getCommandInfo();
}
