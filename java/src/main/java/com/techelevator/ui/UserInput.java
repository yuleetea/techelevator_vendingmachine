package com.techelevator.ui;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.Funds;
import com.techelevator.models.Logger;
import com.techelevator.models.VendingItems;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * <p>
 * Dependencies: None
 */
public class UserInput {
    private static Scanner scanner = new Scanner(System.in);

    public static String getMainMenu() {
        // remember to loop back in event they dont choose next option layer/exit
        boolean keepGoing = true;

        do {
            System.out.println("What would you like to do?");
            System.out.println();

            System.out.println("1) Display Vending Machine Items");
            System.out.println("2) Purchase");
            System.out.println("3) Exit");

            System.out.println();
            System.out.print("Please select an option: ");

            String selectedOption = scanner.nextLine();
            String option = selectedOption.trim().toLowerCase();

            if (option.equals("1")) {
                return "display";
            } else if (option.equals("2")) {
                return "purchase";
            } else if (option.equals("3")) {
                return "exit";
            } else {
                return "";
            }
        } while (keepGoing);

    }

    public static String getPurchaseMenu(Funds balance) {

        // remember to loop back in event they don't choose next option layer/exit

        boolean keepGoing = true;

        do {
            System.out.println("What would you like to do?");
            System.out.println();

            System.out.println("1) Feed Money");
            System.out.println("2) Select Product");
            System.out.println("3) Finish Transaction");

            // balance
            System.out.println("Current balance: " + NumberFormat.getCurrencyInstance().format(balance.getCurrentMoneyProvided()));
            System.out.println();
            System.out.print("Please select an option: ");


            String selectedOption = scanner.nextLine();
            String option = selectedOption.trim().toLowerCase();


            if (option.equals("1")) {
                return "Feed Money";
            } else if (option.equals("2")) {
                return "Select Product";
            } else if (option.equals("3")) {
                // finish transaction method
                keepGoing = false;
                return "Finish Transaction";

            }

        } while (keepGoing);

        return "";
    }

    public BigDecimal getMoney(Funds balance) {

        boolean keepGoing = true;
        do {
            System.out.println("Place money here: ");
            String userInput = scanner.nextLine();
            try {
                BigDecimal money = balance.moneyProvided(new BigDecimal(userInput));
                Logger.auditVendingFeedMoney(userInput, balance);
                keepGoing = false;
//                return Funds.moneyProvided(new BigDecimal(userInput));
                return money;
            } catch (InputMismatchException e) {
                System.out.println("This vending machine accepts whole dollar amounts (e.g. \\$1, \\$2, \\$5, or \\$10) ");
            } catch (Exception e) {
                System.out.println("cmon, we're adults out here. Do better, be better.");
            }
        } while (keepGoing);

        return new BigDecimal("0");
    }

    // Select Product
    public String getProductIdentifier(List<VendingItems> vendingProducts, Funds balance) {

        boolean keepGoing = true;
        VendingItems selectedItem = null;

        do {
            System.out.println("Select an item: ");
            String userInput = scanner.nextLine();

            // find the item in the list
            for (VendingItems item : vendingProducts) {

                if (item.getIdentifier().equals(userInput)) {
                    selectedItem = item;

                }
            }

            // handle if it doesn't exist
            if (selectedItem == null) {
                System.out.println("Product does not exist." + "\n");
                continue;
            }
            try {
                balance.updateBalance(selectedItem.getPrice());
                selectedItem.buyItem();
                Logger.auditVendingSelectProduct(selectedItem, balance);
                System.out.println("Dispensing " + selectedItem.getName() + ": $" + selectedItem.getPrice());
                System.out.println("Remaining balance: " + NumberFormat.getCurrencyInstance().format(balance.getCurrentMoneyProvided()));
                System.out.println(selectedItem.message() + "\n");
                keepGoing = false;
            } catch (ArithmeticException e) {

            } catch (Exception e) {
                keepGoing = false;
            }

        } while (keepGoing);

        return "";
    }
}
    

