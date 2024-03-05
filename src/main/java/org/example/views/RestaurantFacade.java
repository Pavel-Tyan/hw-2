package org.example.views;

import lombok.NoArgsConstructor;
import org.example.controllers.MealController;
import org.example.controllers.OrderController;
import org.example.controllers.UserController;
import org.example.repositories.MealRepository;
import org.example.repositories.OrderRepository;
import org.example.repositories.UserRepository;
import org.example.services.MealService;
import org.example.services.OrderService;
import org.example.services.UserService;
import org.example.services.implementations.MealServiceImplementation;
import org.example.services.implementations.OrderServiceImplementation;
import org.example.services.implementations.UserServiceImplementation;
import org.example.views.menu.states.*;
import org.example.views.menu.states.implementations.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
public class RestaurantFacade {
    private Menu menu;

    private MealController mealController;
    private UserController userController;
    private OrderController orderController;

    private MealService mealService;
    private UserService userService;
    private OrderService orderService;

    private MealRepository mealRepository;

    private UserRepository userRepository;
    private OrderRepository orderRepository;

    public void runApp() {
        configureApp();

        boolean isEnd = false;

        while (!isEnd) {
            menu.showMenu();
            menu.selectCommand();
        }
    }

    public void configureApp() {
        configureRepositories();
        configureServices();
        configureControllers();
        configureMenu();
    }

    public void configureRepositories() {
        mealRepository = new MealRepository();
        userRepository = new UserRepository();
        orderRepository = new OrderRepository();
    }
    public void configureServices() {
        mealService = new MealServiceImplementation(mealRepository);
        userService = new UserServiceImplementation(userRepository);
        orderService = new OrderServiceImplementation(orderRepository);
    }
    public void configureControllers() {
        mealController = new MealController(mealService);
        userController = new UserController(userService);
        orderController = new OrderController(orderService);
    }

    public void configureMenu() {
        MenuState registerState = new RegisterState(userController);
        MenuState authState = new AuthState(userController);
        MenuState addMealToOrderState = new AddMealToOrderState(orderController, mealController);
        MenuState showOrderState = new ShowOrderState(orderController);
        MenuState showMealsMenuState = new ShowMealsMenuState(mealController);
        MenuState addNewMeal = new AddNewMealState(mealController);
        MenuState updateMeal = new UpdateMealState(mealController);
        MenuState deleteMeal = new DeleteMealState(mealController);
        MenuState exitState = new ExitState();

        List<MenuState> menuStates = new ArrayList<MenuState>();

        menuStates.add(registerState);
        menuStates.add(authState);
        menuStates.add(addMealToOrderState);
        menuStates.add(showOrderState);
        menuStates.add(showMealsMenuState);
        menuStates.add(addNewMeal);
        menuStates.add(updateMeal);
        menuStates.add(deleteMeal);
        menuStates.add(exitState);

        menu = new Menu(menuStates, null);
    }
}
