package com.techelevator.models;

import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    // for constructor, take name of file?

    public static void auditVendingFeedMoney(String userInput, Funds balance) {

        File logFile = new File("log.txt");


        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

        String log = LocalDateTime.now().format(targetFormat) + " FEED MONEY: "
                + NumberFormat.getCurrencyInstance().format(new BigDecimal(userInput)) + ", "
                + NumberFormat.getCurrencyInstance().format(balance.getCurrentMoneyProvided()) + "\n";

        PrintWriter writer = null;

        try {
            if (logFile.exists()) {
                writer = new PrintWriter(new FileOutputStream(logFile.getAbsoluteFile(), true));
            } else {
                writer = new PrintWriter(logFile.getAbsoluteFile());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        writer.append(log);
        writer.flush();
        writer.close();

    }

    public static void auditVendingSelectProduct(VendingItems item, Funds balance) {

        File logFile = new File("log.txt");


        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
        String log = LocalDateTime.now().format(targetFormat) + " " + item.getName() + " " + item.getIdentifier() + ": "
                + NumberFormat.getCurrencyInstance().format(balance.getCurrentMoneyProvided().add(item.getPrice())) + ", "
                + NumberFormat.getCurrencyInstance().format(balance.getCurrentMoneyProvided()) +"\n";

        PrintWriter writer = null;

        try {
            if (logFile.exists()) {
                writer = new PrintWriter(new FileOutputStream(logFile.getAbsoluteFile(), true));
            } else {
                writer = new PrintWriter(logFile.getAbsoluteFile());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        writer.append(log);
        writer.flush();
        writer.close();

    }

    public static void auditVendingFinishTransaction(BigDecimal currentMoney, BigDecimal dollars) {

        File logFile = new File("log.txt");


        // dollars is currentBalance, should always be zero after finishing transaction
        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
        String log = LocalDateTime.now().format(targetFormat) + " GIVE CHANGE: "
                + NumberFormat.getCurrencyInstance().format(currentMoney) + ", "
                + NumberFormat.getCurrencyInstance().format(dollars) + "\n";

        PrintWriter writer = null;

        try {
            if (logFile.exists()) {
                writer = new PrintWriter(new FileOutputStream(logFile.getAbsoluteFile(), true));
            } else {
                writer = new PrintWriter(logFile.getAbsoluteFile());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        writer.append(log);
        writer.flush();
        writer.close();
    }
}
