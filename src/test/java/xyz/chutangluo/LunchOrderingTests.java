package xyz.chutangluo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LunchOrderingTests {
    private OrderHandler orderHandler;

    @BeforeEach
    public void setUp() {
        orderHandler = OrderHandler.getInstance();
    }

    @Test
    public void testLunch() {
        assertEquals("Sandwich, Chips, Soda", orderHandler.order("Lunch 1,2,3"));
    }

    @Test
    public void testLunch2() {
        assertEquals("Sandwich, Chips, Water", orderHandler.order("Lunch 1,2"));
    }

    @Test
    public void testLunch3() {
        assertEquals("Unable to process: Sandwich cannot be ordered more than once", orderHandler.order("Lunch 1,1,2,3"));
    }

    @Test
    public void testLunch4() {
        assertEquals("Sandwich, Chips(2), Water", orderHandler.order("Lunch 1,2,2"));
    }

    @Test
    public void testLunch5() {
        assertEquals("Unable to process: Main is missing, Side is missing", orderHandler.order("Lunch"));
    }
}
