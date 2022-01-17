package com.techelevator.models;

import java.math.BigDecimal;

public class Candy extends VendingItems {

    public Candy(String name, BigDecimal price, String identifier) {
        super(name, price, identifier);
    }

    public String message() {
        return "Munch Munch, Yum!";
    }
}
