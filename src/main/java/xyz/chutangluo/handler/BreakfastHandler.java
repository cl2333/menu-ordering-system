package xyz.chutangluo.handler;

import xyz.chutangluo.DishType;
import xyz.chutangluo.MealException;
import xyz.chutangluo.meals.Breakfast;
import xyz.chutangluo.meals.Meal;

import java.util.HashMap;

public class BreakfastHandler extends MealHandler {
    private static BreakfastHandler breakfastHandler;

    private BreakfastHandler() {
        MAIN_DISH = "Eggs";
        SIDE_DISH = "Toast";
        DRINK_DISH = "Coffee";
    }

    public static BreakfastHandler getInstance() {
        if (breakfastHandler == null) {
            breakfastHandler = new BreakfastHandler();
        }
        return breakfastHandler;
    }

    @Override
    public boolean checkDrink(HashMap<DishType, Integer> items) throws MealException {
        return true;
    }

    @Override
    public Meal cookMeal(HashMap<DishType, Integer> items) throws MealException {
        if (!super.checkOrderValid(items)) {
            return null;
        }
        return new Breakfast(getMain(items), getSide(items), getDrink(items));
    }
}
