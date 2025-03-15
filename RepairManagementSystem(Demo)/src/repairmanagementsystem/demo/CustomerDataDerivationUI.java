/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repairmanagementsystem.demo;

/**
 *
 * @author senud
 */
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDataDerivationUI extends JFrame {

    private JTextField customerIdField;
    private JTextArea resultTextArea;

    public CustomerDataDerivationUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Customer Data Derivation");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Derive Customer Data");
        titleLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

        JLabel customerIdLabel = new JLabel("Customer ID: ");
        customerIdField = new JTextField();
        JButton deriveButton = new JButton("Derive Data");

        inputPanel.add(customerIdLabel);
        inputPanel.add(customerIdField);
        inputPanel.add(deriveButton);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(resultTextArea);

        deriveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deriveDataButtonActionPerformed(e);
            }
        });

        getContentPane().add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void deriveDataButtonActionPerformed(ActionEvent evt) {
        String customerId = customerIdField.getText();

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/Repair_Management_System";
        String username = "root";
        String password = "Waybig@123";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM customer WHERE Customer_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, customerId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Replace the following line with actual data retrieval logic
                        String customerData = "Customer ID: " + resultSet.getString("Customer_ID")
                                + "\nTitle: " + resultSet.getString("title")
                                + "\nName: " + resultSet.getString("FName") + " " + resultSet.getString("LName")
                                + "\nPhone No: " + resultSet.getString("phoneno")
                                + "\nAddress: " + resultSet.getString("House_No") + ", " + resultSet.getString("Road")
                                + ", " + resultSet.getString("City") + "\nClaim Date : "+resultSet.getString("claimdate");

                        resultTextArea.setText(customerData);
                    } else {
                        resultTextArea.setText("Customer not found");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerDataDerivationUI().setVisible(true);
            }
        });
    }
}

