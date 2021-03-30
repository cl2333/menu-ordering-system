package xyz.chutangluo;

import java.util.HashMap;

public class MealDetails {
    MealType mealType;
    HashMap<DishType, Integer> items;

    public MealDetails() {
        this.items = new HashMap<>();
    }
}
