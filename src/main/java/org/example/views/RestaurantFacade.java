package org.example.views;

import lombok.NoArgsConstructor;
import org.example.controllers.MealController;
import org.example.controllers.UserController;
import org.example.repositories.MealRepository;
import org.example.repositories.UserRepository;
import org.example.services.MealService;
import org.example.services.UserService;
import org.example.services.implementations.MealServiceImplementation;
import org.example.services.implementations.UserServiceImplementation;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
public class RestaurantFacade {
    private Menu menu;
    private MealController mealController;
    private UserController userController;

    private MealService mealService;
    private UserService userService;

    private MealRepository mealRepository;

    private UserRepository userRepository;

    public void runApp() {
        configureApp();

        boolean isEnd = false;

        while (!isEnd) { //
            try {
                menu.showMenu();
                menu.selectCommand();
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
    }

    public void configureApp() {
        mealRepository = new MealRepository();
        userRepository = new UserRepository();

        mealService = new MealServiceImplementation(mealRepository);
        userService = new UserServiceImplementation(userRepository);

        mealController = new MealController(mealService);
        userController = new UserController(userService);

        MenuState registerState = new RegisterState(userController);
        MenuState authState = new AuthState(userController);
        MenuState showMealsMenuState = new ShowMealsMenuState(mealController);
        MenuState exitState = new ExitState();

        List<MenuState> menuStates = new ArrayList<MenuState>();
        menuStates.add(registerState);
        menuStates.add(authState);
        menuStates.add(showMealsMenuState);
        menuStates.add(exitState);

        menu = new Menu(menuStates, false);
    }
}
