package com.masonwabe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Account extends Backend
{
    private int accountNumber;
    private int pinNumber;

    Scanner input = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("R #,##0.00");

    public Account() {
    }

    public int viewbalance()
    {
        int balance;
        try {
            ResultSet rs = getRow(accountNumber);
            if (!rs.next())
                return 0;
            balance = rs.getInt("amount");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return balance;
    };

    public void withdraw() {
        System.out.println("Enter amount to withdraw:");
        int cash = input.nextInt();
        int balance = viewbalance();
        if ((balance - cash) >= 0){
            setAmount(accountNumber ,balance - cash);
            System.out.println("Withdrew " + df.format(cash));
            System.out.println("Remaining balance: " + df.format(balance - cash));
        } else {
            System.out.println("Not enough balance available.");
            System.out.println("Available balance: " + df.format(balance));
        }
    }

    public void deposit() {
        System.out.println("Enter amount to deposit:");
        int cash = input.nextInt();
        int balance = viewbalance();
        if (cash > 0){
            setAmount(accountNumber,balance + cash);
            System.out.println("Deposited: " + df.format(cash));
            System.out.println("New balance: " + df.format(balance + cash));
        } else {
            System.out.println("Cannot deposit negative balance.");
        }
    }

    public void setAccount(int acc) {
        accountNumber = acc;
    };

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public int getPin() {
        return pinNumber;
    };

    public void setPin(int pin) {
        pinNumber = pin;
    };

}
