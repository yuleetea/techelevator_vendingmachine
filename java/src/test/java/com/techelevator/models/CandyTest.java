package com.techelevator.models;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CandyTest {

    @Test
    public void testCandyConstructor() {
        Candy candy = new Candy("Reeses", new BigDecimal("1.50"), "E7");

        assertEquals("Reeses", candy.getName());
        assertEquals(new BigDecimal("1.50"), candy.getPrice());
        assertEquals("E7", candy.getIdentifier());
    }

}