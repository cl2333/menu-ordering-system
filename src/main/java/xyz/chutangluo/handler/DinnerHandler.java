package xyz.chutangluo.handler;

import xyz.chutangluo.DishType;
import xyz.chutangluo.Item;
import xyz.chutangluo.MealException;
import xyz.chutangluo.meals.Dinner;
import xyz.chutangluo.meals.Meal;

import java.util.HashMap;

public class DinnerHandler extends MealHandler{
    private static DinnerHandler dinnerHandler;

    private static final String DESSERT_DISH = "Cake";

    private DinnerHandler() {
        MAIN_DISH = "Steak";
        SIDE_DISH = "Potatoes";
        DRINK_DISH = "Wine";
    }

    public static DinnerHandler getInstance() {
        if (dinnerHandler == null) {
            dinnerHandler = new DinnerHandler();
        }
        return dinnerHandler;
    }

    public boolean checkDesert(HashMap<DishType, Integer> items) throws MealException {
        if (!items.containsKey(DishType.DESERT)){
            throw new MealException("Dessert is missing");
        }

        if (items.get(DishType.DESERT) > 1){
            throw new MealException(String.format("%s cannot be ordered more than once", DESSERT_DISH));
        }

        return true;
    }

    public Item getDesert(HashMap<DishType, Integer> items) throws MealException {
        return new Item(DESSERT_DISH, items.get(DishType.DESERT));
    }

    public Meal cookMeal(HashMap<DishType, Integer> items) throws MealException {
        if (!checkOrderValid(items) || !checkDesert(items)) {
            return null;
        }
        return new Dinner(getMain(items), getSide(items), getDrink(items), getDesert(items));
    }
}
