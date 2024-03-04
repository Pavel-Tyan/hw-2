package org.example.services.implementations;

import lombok.AllArgsConstructor;
import org.example.exceptions.ResourceAlreadyExistsException;
import org.example.exceptions.ResourceNotFoundException;
import org.example.models.Meal;
import org.example.repositories.MealRepository;
import org.example.services.MealService;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class MealServiceImplementation implements MealService {
    MealRepository mealRepository;

    @Override
    public Meal findMealByName(String name) {
        for (Meal currentMeal: mealRepository.getMeals()) {
            if (name.equals(currentMeal.getName())) {
                return currentMeal;
            }
        }

        throw new ResourceNotFoundException("Такого блюда нет в меню.");
    }

    @Override
    public List<Meal> findAllMeals() {
        return mealRepository.getMeals();
    }

    @Override
    public void addNewMeal(Meal meal) {
        for (Meal currentMeal: mealRepository.getMeals()) {
            if (Objects.equals(meal.getName(), currentMeal.getName())) {
                throw new ResourceAlreadyExistsException("Такое блюдо уже есть в меню.");
            }
        }

        mealRepository.getMeals().add(meal);
        mealRepository.serializeMeals();
    }

    @Override
    public void deleteMealByName(String name) {
        for (Meal currentMeal: mealRepository.getMeals()) {
            if (name.equals(currentMeal.getName())) {
                mealRepository.getMeals().remove(currentMeal);
                mealRepository.serializeMeals();
                return;
            }
        }

        throw new ResourceNotFoundException("Такого блюда нет в меню.");
    }

    @Override
    public void updateMeal(Meal meal) {
        for (Meal currentMeal: mealRepository.getMeals()) {
            if (meal.getName().equals(currentMeal.getName())) {
                currentMeal = meal;
                mealRepository.serializeMeals();
                return;
            }
        }

        throw new ResourceNotFoundException("Такого блюда нет в меню.");
    }
}
