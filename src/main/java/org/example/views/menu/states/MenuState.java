package org.example.views.menu.states;

import org.example.models.User;

public interface MenuState {
    User doCommand(User user);
    String getCommandInfo();
}
