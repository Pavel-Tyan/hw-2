package org.example.views.menu.states.implementations;

import lombok.AllArgsConstructor;
import org.example.controllers.ProfitController;
import org.example.models.User;
import org.example.views.menu.states.MenuState;

@AllArgsConstructor
public class ShowProfitState implements MenuState {
    private ProfitController profitController;
    @Override
    public User doCommand(User user) {
        if (user == null || !user.isAdmin()) {
            System.out.println("Вы не вошли в аккаунт либо у вас недостаточно прав для этой операции.");
            return user;
        }

        System.out.printf("Прибыль ресторана с заказов: %f", profitController.getProfit().getMoney());
        System.out.println();

        return user;
    }

    @Override
    public String getCommandInfo() {
        return "Показать прибыль ресторана.";
    }
}
