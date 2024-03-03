package org.example.views;

import lombok.AllArgsConstructor;
import org.example.controllers.UserController;
import org.example.exceptions.ResourceAlreadyExistsException;
import org.example.models.User;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@AllArgsConstructor
public class AuthState implements MenuState{
    private UserController userController;

    @Override
    public void doCommand(boolean hasLogged) {
        String login;
        String password;

        if (hasLogged) {
            System.out.println("Чтобы войти в другой аккаунт, выйдите из текущего аккаунта.");
            System.out.println();
            return;
        }

        Scanner scanner = new Scanner(System.in);

        hasLogged = false;

        while (!hasLogged) {
            System.out.print("Введите логин: ");
            login = scanner.nextLine();

            System.out.println();

            System.out.print("Введите пароль: ");
            password = scanner.nextLine();

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
                User user = new User(login, password);
                if (userController.isUserExists(new User(login, password))) {
                    hasLogged = true;
                } else {
                    System.out.println("Такого пользователя не существует");
                    System.out.println(user.getLogin());
                    System.out.println(user.getPasswordHash());

                    break;
                }

                hasLogged = true;
                System.out.print("Вы успешно вошли в аккаунт.");
                System.out.println();
            } catch (ResourceAlreadyExistsException ex) {
                System.out.println(ex.getMessage());
                System.out.println();
            }
        }
    }

    @Override
    public String getCommandInfo() {
        return "Вход в аккаунт.";
    }
}
