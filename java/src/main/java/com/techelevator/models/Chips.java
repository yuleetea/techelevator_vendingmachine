package com.techelevator.models;

import java.math.BigDecimal;

public class Chips extends VendingItems {

    public Chips(String name, BigDecimal price, String identifier) {
        super(name, price, identifier);
    }

    public String message() {
        return "Crunch Crunch, Yum!";
    }
}
