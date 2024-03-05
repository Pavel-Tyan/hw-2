package org.example.views.menu.states.implementations;

import lombok.AllArgsConstructor;
import org.example.controllers.UserController;
import org.example.exceptions.ResourceAlreadyExistsException;
import org.example.models.User;
import org.example.views.menu.states.MenuState;

import java.util.Scanner;

@AllArgsConstructor
public class AuthState implements MenuState {

    private UserController userController;
    @Override
    public User doCommand(User user) {
        String login;
        String password;

        Scanner scanner = new Scanner(System.in);

        boolean hasLogged = false;
        User currentUser;

        while (!hasLogged) {
            System.out.print("Введите логин: ");
            login = scanner.next();

            System.out.println();

            System.out.print("Введите пароль: ");
            password = scanner.next();

            if (login == null || password == null) {
                System.out.println("Логин и пароль не должны быть пустыми.");
                System.out.println();
                continue;
            }

            if (!login.matches(".*[a-zA-Z].*") ||
                    !password.matches(".*[a-zA-Z].*")) {
                System.out.println("Логин и пароль должны содержать латинские буквы.");
                System.out.println();
                continue;
            }

            try {
                currentUser = new User(login, password);
                if (userController.isUserExists(currentUser)) {
                    hasLogged = true;
                } else {
                    System.out.println("Такого пользователя не существует");
                    break;
                }

                hasLogged = true;
                System.out.println();
                System.out.print("Вы успешно вошли в аккаунт.");
                System.out.println();
                return currentUser;
            } catch (ResourceAlreadyExistsException ex) {
                System.out.println(ex.getMessage());
                System.out.println();
            }
        }
        return null;
    }

    @Override
    public String getCommandInfo() {
        return "Войти в аккаунт / Сменить аккаунт";
    }
}
