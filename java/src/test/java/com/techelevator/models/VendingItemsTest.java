package com.techelevator.models;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VendingItemsTest {

    @Test
    public void buyItem() {
        // first test is --
        VendingItems item = new Candy("Cowtales", new BigDecimal("1.50"), "B2");

        int actualResult = item.buyItem();

        assertEquals(4, actualResult);

    }

    @Test
    public void buyItemFail() {

        // second test for throw Arithmetic Exception

        VendingItems item = new Candy("Cowtales", new BigDecimal("1.50"), "B2");

        item.buyItem();
        item.buyItem();
        item.buyItem();
        item.buyItem();
        item.buyItem();

        try {
            item.buyItem();
        } catch (ArithmeticException e) {
            return;
        }
        fail();
    }
}