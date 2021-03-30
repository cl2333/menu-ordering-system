package xyz.chutangluo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BreakfastOrderingTests {
    private OrderHandler orderHandler;

    @BeforeEach
    public void setUp() {
        orderHandler = OrderHandler.getInstance();
    }

    @Test
    public void testBreakfast() {
        assertEquals("Eggs, Toast, Coffee", orderHandler.order("Breakfast 1,2,3"));
    }

    @Test
    public void testBreakfast2() {
        assertEquals("Eggs, Toast, Coffee", orderHandler.order("Breakfast 2,3,1"));
    }

    @Test
    public void testBreakfast3() {
        assertEquals("Eggs, Toast, Coffee(3)", orderHandler.order("Breakfast 1,2,3,3,3"));
    }

    @Test
    public void testBreakfast4() {
        assertEquals("Unable to process: Side is missing", orderHandler.order("Breakfast 1"));
    }
}
