package com.banking;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginWindow() {
        setTitle("Banking Application - Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        panel.add(loginButton);
        panel.add(cancelButton);

        loginButton.addActionListener(e -> handleLogin());
        cancelButton.addActionListener(e -> System.exit(0));

        add(panel);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // For demonstration, using simple credentials
        if (username.equals("admin") && password.equals("admin")) {
            dispose(); // Close login window
            new BankingWindow().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid credentials!",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
