package org.example.views;
import lombok.AllArgsConstructor;
import org.example.controllers.UserController;
import org.example.exceptions.ResourceAlreadyExistsException;
import org.example.models.User;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
@AllArgsConstructor
public class RegisterState implements MenuState{
    private UserController userController;
    @Override
    public void doCommand(boolean hasLogged) {
        String login;
        String password;

        if (hasLogged) {
            System.out.println("Чтобы зарегистрироваться, выйдите из текущего аккаунта.");
            System.out.println();
            return;
        }

        Scanner scanner = new Scanner(System.in);

        boolean hasRegistered = false;

        while (!hasRegistered) {
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
                userController.addNewUser(new User(login, password));
                hasRegistered = true;
                System.out.print("Вы успешно зарегистрировались.");
                System.out.println();
            } catch (ResourceAlreadyExistsException ex) {
                System.out.println(ex.getMessage());
                System.out.println();
            }
        }
    }

    @Override
    public String getCommandInfo() {
        return "Регистрация нового пользователя";
    }
}
