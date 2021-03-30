package xyz.chutangluo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DinnerOrderingTests {
    private OrderHandler orderHandler;

    @BeforeEach
    public void setUp() {
        orderHandler = OrderHandler.getInstance();
    }

    @Test
    public void testDinner() {
        assertEquals("Steak, Potatoes, Wine, Water, Cake", orderHandler.order("Dinner 1,2,3,4"));
    }

    @Test
    public void testDinner1() {
        assertEquals("Unable to process: Dessert is missing", orderHandler.order("Dinner 1,2,3"));
    }
}
