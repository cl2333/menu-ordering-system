package xyz.chutangluo;

import xyz.chutangluo.handler.BreakfastHandler;
import xyz.chutangluo.handler.DinnerHandler;
import xyz.chutangluo.handler.LunchHandler;
import xyz.chutangluo.handler.MealHandler;
import xyz.chutangluo.meals.Meal;

import java.util.HashMap;

public class OrderHandler {

    private final MealHandler breakfastHandler;
    private final MealHandler lunchHandler;
    private final MealHandler dinnerHandler;

    private static HashMap<String, DishType> idToDishType;

    private static OrderHandler orderHandler = null;

    private OrderHandler() {
        breakfastHandler = BreakfastHandler.getInstance();
        lunchHandler = LunchHandler.getInstance();
        dinnerHandler = DinnerHandler.getInstance();

        idToDishType = new HashMap<>();
        idToDishType.put("1", DishType.MAIN);
        idToDishType.put("2", DishType.SIDE);
        idToDishType.put("3", DishType.DRINK);
        idToDishType.put("4", DishType.DESERT);
    }

    public static OrderHandler getInstance() {
        if (orderHandler == null) {
            orderHandler = new OrderHandler();
        }
        return orderHandler;
    }

    public String order(String orderString) {
        try {
            MealDetails mealDetails = parseOrder(orderString.toLowerCase());
            Meal meal;
            switch (mealDetails.mealType) {
                case BREAKFAST:
                    meal = breakfastHandler.cookMeal(mealDetails.items);
                    break;
                case LUNCH:
                    meal = lunchHandler.cookMeal(mealDetails.items);
                    break;
                case DINNER:
                    meal = dinnerHandler.cookMeal(mealDetails.items);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + mealDetails.mealType);
            }
            String results = meal.serve();
            System.out.println(results);
            return results;
        } catch (MealException e) {
            String results = "Unable to process: " + e.getMessage();
            System.out.println(results);
            return results;
        }
    }

    //get mealDetails
    private MealDetails parseOrder(String orderString) throws MealException {
        MealDetails mealDetails = new MealDetails();

        if (orderString.contains("breakfast")){
            mealDetails.mealType = MealType.BREAKFAST;
        }
        else if (orderString.contains("lunch")){
            mealDetails.mealType = MealType.LUNCH;
        }
        else if (orderString.contains("dinner")){
            mealDetails.mealType = MealType.DINNER;
        } else {
            throw new MealException("Can't locate meal type! Should be one of Breakfast, Lunch or Dinner");
        }

        String[] tokens = orderString.split(" ");
        if (tokens.length > 1) {
            for (String dishId : tokens[1].split(",")){
                DishType dishType = idToDishType.get(dishId);
                if (dishType == null) {
                    throw new MealException("Meal Id is not exist");
                }
                mealDetails.items.put(dishType, mealDetails.items.getOrDefault(dishType, 0) + 1);
            }
        }

        return mealDetails;
    }

    public static void main(String[] args) {
        OrderHandler.getInstance().order(args[0]);
    }
}
