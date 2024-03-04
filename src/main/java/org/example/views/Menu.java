package org.example.views;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.models.User;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
@Getter
public class Menu {
    @Setter(AccessLevel.PRIVATE)
    List<MenuState> menuStates;

    @Setter
    private User user = new User();

    public void showMenu() {
        for (int i = 0; i < menuStates.size(); i++) {
            System.out.printf("%d %s%n", i + 1, menuStates.get(i).getCommandInfo());
        }
    }
    public void doCommand(int commandNumber) {
        user = menuStates.get(commandNumber).doCommand(user);
        System.out.println();
    }

    public void selectCommand() {
        Scanner scanner = new Scanner(System.in);

        int commandNumber;

        do {
            System.out.println();
            System.out.print("Введите номер команды, чтобы продолжить: ");

            try {
                commandNumber = scanner.nextInt();
                System.out.println();
            } catch (Exception e) {
                System.out.println();
                System.out.println("Некорректный ввод числа. Повторите попытку.");
                continue;
            }

            if (!isCorrectCommand(commandNumber)) {
                System.out.println("Некорректный номер команды. Повторите попытку.");
                showMenu();
            } else {
                doCommand(commandNumber - 1);
                break;
            }


        } while (true);
    }

    private boolean isCorrectCommand(int commandNumber) {
        return commandNumber - 1 >= 0
                && commandNumber - 1 < menuStates.size();
    }
}
