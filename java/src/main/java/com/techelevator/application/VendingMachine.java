package com.techelevator.application;

import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

    private List<VendingItems> vendingProducts = new ArrayList<>();

    // place initialize vending machine items here
    public List<VendingItems> initializeVendingMachine() {

        File file = new File("vendingmachine.csv");
        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] lineBits = line.split("\\|");

                // remove redundancy

//                VendingItems item;

                if (lineBits[3].equals("Chip")) {
                    VendingItems item = new Chips(lineBits[1], new BigDecimal(lineBits[2]), lineBits[0]);
                    vendingProducts.add(item);
                } else if (lineBits[3].equals("Candy")) {
                    VendingItems item = new Candy(lineBits[1], new BigDecimal(lineBits[2]), lineBits[0]);
                    vendingProducts.add(item);
                } else if (lineBits[3].equals("Drink")) {
                    VendingItems item = new Drink(lineBits[1], new BigDecimal(lineBits[2]), lineBits[0]);
                    vendingProducts.add(item);
                } else if (lineBits[3].equals("Gum")) {
                    VendingItems item = new Gum(lineBits[1], new BigDecimal(lineBits[2]), lineBits[0]);
                    vendingProducts.add(item);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid Data");
            System.exit(1);
        }
        return vendingProducts;
    }

    public void run()
    {
        // in here we keep the logic to move between the menus
        List<VendingItems> vendingItems = initializeVendingMachine();

        while(true)
        {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getMainMenu();

            if(choice.equals("display"))
            {
                // display the vending machine slots

                for (VendingItems item : vendingItems) {
                    System.out.println(item.getIdentifier() + " >> " + item.getName()
                            + " >> " + item.getPrice() + " >> " + item.getQuantity());
                }
            }
            else if(choice.equals("purchase"))
            {
                // make a purchase
                handleLevel2();

            }
            else if(choice.equals("exit"))
            {
                // good bye
                break;
            }
        }

    }

    // place handleLevel2 method here and use do while loop
    public void handleLevel2() {

        Funds balance = new Funds();

        while(true)
        {
            UserInput input = new UserInput();

            String choice = UserInput.getPurchaseMenu(balance);

            if(choice.equals("Feed Money"))
            {

                input.getMoney(balance);
            }
            else if(choice.equals("Select Product"))
            {
                // print out list of product
                for (VendingItems item : vendingProducts) {
                    System.out.println(item.getIdentifier() + " | " + item.getName() + " | " + item.getPrice());
                }

                // make a purchase

                input.getProductIdentifier(vendingProducts, balance);


            }
            else if(choice.equals("Finish Transaction"))
            {
                System.out.println(balance.returnChange());
                System.out.println("Current balance in vending machine is: " + NumberFormat.getCurrencyInstance().format(balance.getCurrentMoneyProvided()));
                break;
            }
        }
    }
}
