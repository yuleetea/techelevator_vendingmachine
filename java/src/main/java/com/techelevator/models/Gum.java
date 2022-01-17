package com.techelevator.models;

import java.math.BigDecimal;

public class Gum extends VendingItems {

    public Gum(String name, BigDecimal price, String identifier) {
        super(name, price, identifier);
    }

    public String message() {
        return "Chew Chew, Yum!";
    }
}
