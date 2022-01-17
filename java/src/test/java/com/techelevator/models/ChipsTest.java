package com.techelevator.models;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ChipsTest {

    @Test
    public void testChipsConstructor() {
        Chips chips = new Chips("Potato Crisps", new BigDecimal("3.05"), "A1");

        assertEquals("Potato Crisps", chips.getName());
        assertEquals(new BigDecimal("3.05"), chips.getPrice());
        assertEquals("A1", chips.getIdentifier());
    }
}