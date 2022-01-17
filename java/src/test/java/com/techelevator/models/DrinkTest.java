package com.techelevator.models;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DrinkTest {

    @Test
    public void testDrinkConstructor() {
        Drink drink = new Drink("Mountain Melter", new BigDecimal("1.50"), "C3");

        assertEquals("Mountain Melter", drink.getName());
        assertEquals(new BigDecimal("1.50"), drink.getPrice());
        assertEquals("C3", drink.getIdentifier());
    }
}