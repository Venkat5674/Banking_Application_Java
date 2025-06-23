package com.banking;

import java.util.ArrayList;
import java.util.List;

public class BankService {
    private double balance;
    private List<String> transactions;

    public BankService() {
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(String.format("Deposit: $%.2f", amount));
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add(String.format("Withdrawal: $%.2f", amount));
            return true;
        }
        return false;
    }

    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactions);
    }
}
