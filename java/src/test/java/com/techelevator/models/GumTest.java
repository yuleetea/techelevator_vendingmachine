package com.techelevator.models;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GumTest {

    @Test
    public void testGumConstructor() {

        Gum gum = new Gum("Little League Chew", new BigDecimal("0.95"), "D2");

        assertEquals("Little League Chew", gum.getName());
        assertEquals(new BigDecimal("0.95"), gum.getPrice());
        assertEquals("D2", gum.getIdentifier());
    }

}