//package org.example.views;
//
//import lombok.AllArgsConstructor;
//import org.example.controllers.MealController;
//import org.example.models.Meal;
//import org.example.models.User;
//
//import java.util.Scanner;
//
//@AllArgsConstructor
//public class AddMealToOrderState implements MenuState{
//    MealController mealController;
//    @Override
//    public User doCommand(User user) {
//        Scanner scanner = new Scanner(System.in);
//
//        String mealName;
//        int count;
//        double price = 0;
//
//        boolean hasCreatedOrder = false;
//
//        while (!hasCreatedOrder) {
//            System.out.print("Введите название блюда:");
//            mealName = scanner.next();
//
//            if (mealName == null || !mealController.isMealExists(mealName)) {
//                System.out.println("Неверное название блюда.");
//                return user;
//            }
//
//            System.out.print("Введите число порций: ");
//            count = scanner.nextInt(); //Отлови ошибку ввода
//
//            Meal currentMeal = mealController.findMealByName(mealName);
//            int maxCount = currentMeal.getCount();
//
//            if (count > maxCount) {
//                System.out.println("У ресторана нет возможности приготовить столько порций.");
//                return user;
//            }
//
//            Meal updatedMeal = new Meal(
//                    currentMeal.getPrice(),
//                    currentMeal.getName(),
//                    currentMeal.getCount() - count,
//                    currentMeal.getCookingTimeMinutes()
//            );
//
//            mealController.updateMeal(updatedMeal);
//            System.out.println();
//        }
//
//
//    }
//
//    @Override
//    public String getCommandInfo() {
//        return "Добавить блюдо в заказ.";
//    }
//}
