package xyz.chutangluo.handler;

import xyz.chutangluo.DishType;
import xyz.chutangluo.MealException;
import xyz.chutangluo.meals.Lunch;
import xyz.chutangluo.meals.Meal;

import java.util.HashMap;

public class LunchHandler extends MealHandler{
    private static LunchHandler lunchHandler;
    private LunchHandler() {
        MAIN_DISH = "Sandwich";
        SIDE_DISH = "Chips";
        DRINK_DISH = "Soda";
    }

    public static LunchHandler getInstance() {
        if (lunchHandler == null) {
            lunchHandler = new LunchHandler();
        }
        return lunchHandler;
    }

    @Override
    public boolean checkSide(HashMap<DishType, Integer> items) throws MealException {
        if (!items.containsKey(DishType.SIDE)){
            throw new MealException("Side is missing");
        }
        return true;
    }

    @Override
    public Meal cookMeal(HashMap<DishType, Integer> items) throws MealException {
        if (!checkOrderValid(items)) {
            return null;
        }
        return new Lunch(getMain(items), getSide(items), getDrink(items));
    }
}
