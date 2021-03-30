package xyz.chutangluo.meals;

import xyz.chutangluo.Item;

public class Dinner extends Meal{
    Item desert;

    public Dinner(Item main, Item side, Item drink, Item desert) {
        super(main, side, drink);
        if (!drink.getName().equals("Water")) {
            drinks.add(new Item("Water"));
        }
        this.desert = desert;
    }

    @Override
    public String serve() {
        return super.serve() + ", " + desert.getName();
    }
}
