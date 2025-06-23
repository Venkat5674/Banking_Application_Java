package com.banking;

import javax.swing.*;
import java.awt.*;

public class BankingWindow extends JFrame {
    private final BankService bankService;
    private final JTextArea transactionArea;

    public BankingWindow() {
        bankService = new BankService();

        setTitle("Banking Application");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton historyButton = new JButton("Transaction History");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(checkBalanceButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(exitButton);

        // Create transaction history area
        transactionArea = new JTextArea();
        transactionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(transactionArea);

        // Add components to main panel
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add action listeners
        checkBalanceButton.addActionListener(e -> showBalance());
        depositButton.addActionListener(e -> handleDeposit());
        withdrawButton.addActionListener(e -> handleWithdraw());
        historyButton.addActionListener(e -> showTransactionHistory());
        exitButton.addActionListener(e -> System.exit(0));

        add(mainPanel);
    }

    private void showBalance() {
        JOptionPane.showMessageDialog(this,
                String.format("Current Balance: $%.2f", bankService.getBalance()),
                "Balance",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleDeposit() {
        String input = JOptionPane.showInputDialog(this,
                "Enter amount to deposit:",
                "Deposit",
                JOptionPane.PLAIN_MESSAGE);
        
        if (input != null && !input.trim().isEmpty()) {
            try {
                double amount = Double.parseDouble(input);
                bankService.deposit(amount);
                updateTransactionDisplay();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid amount!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleWithdraw() {
        String input = JOptionPane.showInputDialog(this,
                "Enter amount to withdraw:",
                "Withdraw",
                JOptionPane.PLAIN_MESSAGE);
        
        if (input != null && !input.trim().isEmpty()) {
            try {
                double amount = Double.parseDouble(input);
                if (bankService.withdraw(amount)) {
                    updateTransactionDisplay();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Insufficient funds!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid amount!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showTransactionHistory() {
        updateTransactionDisplay();
    }

    private void updateTransactionDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaction History:\n\n");
        for (String transaction : bankService.getTransactionHistory()) {
            sb.append(transaction).append("\n");
        }
        sb.append("\nCurrent Balance: $").append(String.format("%.2f", bankService.getBalance()));
        transactionArea.setText(sb.toString());
    }
}
