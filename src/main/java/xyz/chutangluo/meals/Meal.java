package xyz.chutangluo.meals;

import xyz.chutangluo.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class Meal {
    protected Item main;
    protected Item side;
    protected List<Item> drinks;

    public Meal(Item main, Item side, Item drink) {
        this.main = main;
        this.side = side;
        this.drinks = new ArrayList<>();
        this.drinks.add(drink);
    }

    public String serve() {
        List<String> message = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        items.add(main);
        items.add(side);
        items.addAll(drinks);
        for (Item item : items) {
            if (item.getNumber() == 1) {
                message.add(item.getName());
            } else {
                message.add(String.format("%s(%d)", item.getName(), item.getNumber()));
            }
        }
        return String.join(", ", message);
    }
}
