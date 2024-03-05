package org.example.views;

import lombok.NoArgsConstructor;
import org.example.controllers.MealController;
import org.example.controllers.OrderController;
import org.example.controllers.ProfitController;
import org.example.controllers.UserController;
import org.example.repositories.MealRepository;
import org.example.repositories.OrderRepository;
import org.example.repositories.ProfitRepository;
import org.example.repositories.UserRepository;
import org.example.services.MealService;
import org.example.services.OrderService;
import org.example.services.ProfitService;
import org.example.services.UserService;
import org.example.services.implementations.MealServiceImplementation;
import org.example.services.implementations.OrderServiceImplementation;
import org.example.services.implementations.ProfitServiceImplementation;
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
    private ProfitController profitController;

    private MealService mealService;
    private UserService userService;
    private OrderService orderService;
    private ProfitService profitService;

    private MealRepository mealRepository;

    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private ProfitRepository profitRepository;

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
        profitRepository = new ProfitRepository();
    }
    public void configureServices() {
        mealService = new MealServiceImplementation(mealRepository);
        userService = new UserServiceImplementation(userRepository);
        orderService = new OrderServiceImplementation(orderRepository);
        profitService = new ProfitServiceImplementation(profitRepository);
    }
    public void configureControllers() {
        mealController = new MealController(mealService);
        userController = new UserController(userService);
        orderController = new OrderController(orderService);
        profitController = new ProfitController(profitService);
    }

    public void configureMenu() {
        MenuState registerState = new RegisterState(userController);
        MenuState authState = new AuthState(userController);
        MenuState addMealToOrderState = new AddMealToOrderState(orderController, mealController);
        MenuState showOrderState = new ShowOrderState(orderController);
        MenuState confirmOrderState = new ConfirmOrderState(profitController, mealController, orderController);
        MenuState showMealsMenuState = new ShowMealsMenuState(mealController);
        MenuState showProfitState = new ShowProfitState(profitController);
        MenuState addNewMealState = new AddNewMealState(mealController);
        MenuState updateMealState = new UpdateMealState(mealController);
        MenuState deleteMealState = new DeleteMealState(mealController);
        MenuState exitState = new ExitState();

        List<MenuState> menuStates = new ArrayList<MenuState>();

        menuStates.add(registerState);
        menuStates.add(authState);
        menuStates.add(addMealToOrderState);
        menuStates.add(showOrderState);
        menuStates.add(confirmOrderState);
        menuStates.add(showMealsMenuState);
        menuStates.add(showProfitState);
        menuStates.add(addNewMealState);
        menuStates.add(updateMealState);
        menuStates.add(deleteMealState);
        menuStates.add(exitState);

        menu = new Menu(menuStates, null);
    }
}
