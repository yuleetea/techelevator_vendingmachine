package com.techelevator.models;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.InputMismatchException;

import static org.junit.Assert.*;

public class FundsTest {

    private Funds balance;

    @Before
    public void doBefore() {
        balance = new Funds();
    }

    @Test
    public void testMoneyProvidedMethod() {
        BigDecimal actualInput = new BigDecimal("1");
        BigDecimal actualResult = balance.moneyProvided(actualInput);
        assertEquals(new BigDecimal("1.00"), actualResult);
    }

    @Test
    public void testMoneyProvidedBadNumberInputShouldThrowException() {
        BigDecimal badInput = new BigDecimal("20");
        try {
            BigDecimal badResult = balance.moneyProvided(badInput);
        } catch (InputMismatchException e) {
            return;
        }
        fail();
    }

    @Test
    public void testMoneyProvidedBadStringInputShouldThrowException() {
        String badInput = "potato";
        try {
            BigDecimal badResult = balance.moneyProvided(new BigDecimal(badInput));
        } catch (NumberFormatException e) {
            return;
        }
        fail();
    }

    @Test
    public void testUpdateBalanceMethod() {
        balance.moneyProvided(new BigDecimal("5"));
        BigDecimal item = new BigDecimal("1.50");
        BigDecimal actualResult = null;
        try {
            actualResult = balance.updateBalance(item);
        } catch (Exception e) {
            System.out.println("you shouldn't be seeing this message...");
        }
        assertEquals(balance.getCurrentMoneyProvided(), actualResult);
    }

    @Test
    public void testUpdateBalanceNotEnoughMoney() {

        BigDecimal item = new BigDecimal("1.50");
        try {
            BigDecimal actualResult = balance.updateBalance(item);
            assertEquals(balance.getCurrentMoneyProvided(), actualResult);
        } catch (Exception e) {
            System.out.println("if you're seeing this, we're awesome");
        }
    }

    @Test
    public void testReturnChangeMethod() {
        balance.moneyProvided(new BigDecimal("5"));

        String actualResult = balance.returnChange();

        assertEquals("Your change: " + 20 + " Quarters, " + 0 + " Dimes, and " + 0 + " Nickels", actualResult);
    }

}