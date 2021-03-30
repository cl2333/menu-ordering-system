package xyz.chutangluo;

public class Item {
    private final String name;
    private final Integer number;

    public Item(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public Item(String name) {
        this.name = name;
        this.number = 1;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }
}
