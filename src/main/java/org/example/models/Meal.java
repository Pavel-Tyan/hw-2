package org.example.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class Meal implements Serializable {
    private double price;
    private String name;
    private int count;
    private int cookingTimeMinutes;

    @Override
    public boolean equals(Object meal) {
        Meal otherMeal = (Meal) meal;
        return Objects.equals(this.name, otherMeal.name) &&
                this.price == otherMeal.price &&
                this.count == otherMeal.count &&
                this.cookingTimeMinutes == otherMeal.cookingTimeMinutes;
    }
}
