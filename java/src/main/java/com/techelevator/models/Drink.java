package com.techelevator.models;

import java.math.BigDecimal;

public class Drink extends VendingItems {

    public Drink(String name, BigDecimal price, String identifier) {
        super(name, price, identifier);
    }

    public String message() {
        return "Glug Glug, Yum!";
    }
}
