package com.techelevator.models;

import java.math.BigDecimal;
import java.rmi.server.ExportException;

public abstract class VendingItems {

    private String name;
    private BigDecimal price;
    private String identifier;
    private int quantity;

    public VendingItems(String name, BigDecimal price, String identifier) {
        this.name = name;
        this.price = price;
        this.identifier = identifier;
        this.quantity = 5;
    }

    // methods
    public int buyItem() throws ArithmeticException {
        // subtract 1 when customer buys item

        if (this.quantity == 0) {
            System.out.println("SOLD OUT");
            throw new ArithmeticException();
        } else {
            return --this.quantity;
        }

    }

    // getters

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getIdentifier() {
        return identifier;
    }

    public abstract String message();
}
