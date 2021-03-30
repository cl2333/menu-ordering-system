package xyz.chutangluo.handler;

import xyz.chutangluo.DishType;
import xyz.chutangluo.Item;
import xyz.chutangluo.MealException;
import xyz.chutangluo.meals.Meal;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class MealHandler {
    protected String MAIN_DISH;
    protected String SIDE_DISH;
    protected String DRINK_DISH;

    public boolean checkMain(HashMap<DishType, Integer> items) throws MealException {
        if (!items.containsKey(DishType.MAIN)) {
            throw new MealException("Main is missing");
        }

        if (items.get(DishType.MAIN) > 1) {
            throw new MealException(String.format("%s cannot be ordered more than once", MAIN_DISH));
        }

        return true;
    }

    public Item getMain(HashMap<DishType, Integer> items) {
        return new Item(MAIN_DISH, items.get(DishType.MAIN));
    }

    public boolean checkSide(HashMap<DishType, Integer> items) throws MealException {
        if (!items.containsKey(DishType.SIDE)) {
            throw new MealException("Side is missing");
        }
        if (items.get(DishType.SIDE) > 1) {
            throw new MealException(String.format("%s cannot be ordered more than once", SIDE_DISH));
        }

        return true;
    }

    public Item getSide(HashMap<DishType, Integer> items) {
        return new Item(SIDE_DISH, items.get(DishType.SIDE));
    }

    public boolean checkDrink(HashMap<DishType, Integer> items) throws MealException {
        if (items.get(DishType.DRINK) != null && items.get(DishType.DRINK) > 1) {
            throw new MealException(String.format("%s cannot be ordered more than once", DRINK_DISH));
        }
        return true;
    }

    public Item getDrink(HashMap<DishType, Integer> items) {
        if (items.get(DishType.DRINK) == null) {
            return new Item("Water");
        } else {
            return new Item(DRINK_DISH, items.get(DishType.DRINK));
        }
    }

    public boolean checkOrderValid(HashMap<DishType, Integer> items) throws MealException {
        List<String> errorMessage = new ArrayList<>();
        String[] checkMethods = new String[]{"checkMain", "checkSide", "checkDrink"};
        for (String checkMethod : checkMethods) {
            try {
                getClass().getSuperclass().getMethod(checkMethod, HashMap.class).invoke(this, items);
            } catch (NoSuchMethodException | IllegalAccessException e) {
                System.out.println("Check method reflection error! Aborting...");
                e.printStackTrace();
                System.exit(1);
            } catch (InvocationTargetException e) {
                errorMessage.add(e.getCause().getMessage());
            }
        }
        if (!errorMessage.isEmpty()) {
            throw new MealException(String.join(", ", errorMessage));
        } else {
            return true;
        }
    }

    public abstract Meal cookMeal(HashMap<DishType, Integer> items) throws MealException;
}
