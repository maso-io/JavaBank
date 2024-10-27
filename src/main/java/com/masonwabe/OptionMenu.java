package com.masonwabe;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OptionMenu extends Account {
    private BigDecimal accountNumber;
    private String pin_number;

    //Scanner input = new Scanner(System.in);
    public OptionMenu() {
    };

    public void getLogin() {
        int x;
        try {
            System.out.println("Enter account number:");
            setAccount(input.nextInt());
            System.out.println("Enter pin number:");
            setPin(input.nextInt());

            if (!validate(getAccountNumber(), String.valueOf(getPin()))) {
                throw new Exception();
            }else{
                x = 1;
            }
            while (x == 1)
                 x = getAccountOptions();
        } catch (InputMismatchException e) {
            System.out.println("Inappropriate input supplied. Please read instruction and try again.");
        } catch (Exception e) {
            System.out.println("Incorrect Account or Pin");
        }
    }

    public int getAccountOptions()
    {
        int x = 1;
        System.out.println("Banking Account:");
        System.out.println("Type 1 - View balance");
        System.out.println("Type 2 - Withdraw amount");
        System.out.println("Type 3 - Deposit amount");
        System.out.println("Type 4 - Exit");
        System.out.printf("%s", "Choice: ");
        int selection = input.nextInt();
        System.out.println(selection);
        switch (selection)
        {
            case 1:
                System.out.printf("Available balance: %s\n", df.format(viewbalance()));
                break;
            case 2:
                withdraw();
                break;
            case 3:
                deposit();
                break;
            case 4:
                System.out.println("Thank you for using ATM service.");
                x = -1;
                break;
            default:
                System.out.println("Invalid selection.");
        }
        return x;
    }
    public void onExit() {
        Connection conn = getDBConnection();
        try {
            conn.close();
            input.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
