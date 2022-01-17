package com.techelevator.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;

public class Funds {
    // make everything not static here

    // balance
    private BigDecimal currentMoneyProvided = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);

    //getters


    public  BigDecimal getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }

    public BigDecimal moneyProvided(BigDecimal customerMoney) throws InputMismatchException {
        try {
            if (customerMoney.equals(new BigDecimal("1")) || customerMoney.equals(new BigDecimal("2")) || customerMoney.equals(new BigDecimal("5")) || customerMoney.equals(new BigDecimal("10"))) {
                currentMoneyProvided = currentMoneyProvided.add(customerMoney);
            } else {
                throw new InputMismatchException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input");
        }
        return currentMoneyProvided;
    }

    public BigDecimal updateBalance(BigDecimal itemPrice) throws Exception {
        if (itemPrice.compareTo(currentMoneyProvided) <= 0) {
            return currentMoneyProvided = currentMoneyProvided.subtract(itemPrice);
        } else {
            System.out.println("Not enough money!");
            throw new Exception();
        }
    }

    public String returnChange() {

        int changeDollars = currentMoneyProvided.multiply(new BigDecimal("100")).intValue();
//        currentMoneyProvided = new BigDecimal("0.00"); <--- peep this, we choose not to use this
        // start with 140
        // this should give us the amount of quarters
        int inQuarters = changeDollars / 25;
        changeDollars = changeDollars % 25;


        int inDimes = changeDollars / 10;
        changeDollars = changeDollars % 10;


        int inNickels = changeDollars / 5;
        changeDollars = changeDollars % 5;

//        if (changeDollars % 5 == 0) {
            // return change back in quarters and dimes and nickles
            String outputString = "Your change: " + inQuarters + " Quarters, " + inDimes + " Dimes, and " + inNickels + " Nickels";

//        }
        // we need to take the value of changeDollar/ transform back to BigDeciMal == balance
        // sout vending machines balance which SHOULD be 0

        Logger.auditVendingFinishTransaction(currentMoneyProvided, new BigDecimal(changeDollars));
        currentMoneyProvided = BigDecimal.valueOf(changeDollars);

        return outputString;
    }
}
