/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repairmanagementsystem.demo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement; // Only if you plan to use Statement

public class Customer extends Person {

    private String customerId;
    private String houseNo;
    private String road;
    private String city;
    private String phoneNumber;
    private String date;

    // Constructor
    public Customer(String customerId, String title, String firstName, String lastName,
            String phoneNumber, String houseNo, String road, String city, String date) {
        super(title, firstName, lastName); // Call to superclass constructor
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.houseNo = houseNo;
        this.road = road;
        this.city = city;
        this.date = date;
    }

    // Getters and setters for Customer-specific fields
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.matches("\\d{10}")) {
            this.phoneNumber = phoneNumber;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid character(s) in telephone number: " + phoneNumber + ". It must consist of exactly 10 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Phone number must contain exactly 10 digits.");
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (isValidDate(date)) {
            this.date = date;
        } else {
            // The isValidDate method already handles showing error messages.
        }
    }

    private boolean isValidDate(String date) {
        try {
            LocalDate claimDate = LocalDate.parse(date);
            LocalDate currentDate = LocalDate.now();
            if (claimDate.isAfter(currentDate)) {
                JOptionPane.showMessageDialog(null, "Claim date cannot be a future date.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid date format for claim date. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void addToDatabase() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // Assuming you have a JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "username", "password");

            String sql = "INSERT INTO customers (customerId, title, firstName, lastName, phoneNumber, houseNo, road, city, claimDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, this.getCustomerId());
            pstmt.setString(2, this.getTitle());
            pstmt.setString(3, this.getFirstName());
            pstmt.setString(4, this.getLastName());
            pstmt.setString(5, this.getPhoneNumber());
            pstmt.setString(6, this.getHouseNo());
            pstmt.setString(7, this.getRoad());
            pstmt.setString(8, this.getCity());
            pstmt.setString(9, this.getDate());

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Customer added successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Implementing the abstract method from Person
    @Override
    public String getFullDetails() {
        return "Customer ID: " + customerId + "\n"
                + "Title: " + getTitle() + "\n"
                + "Name: " + getFirstName() + " " + getLastName() + "\n"
                + "Phone Number: " + phoneNumber + "\n"
                + "House No: " + houseNo + "\n"
                + "Road: " + road + "\n"
                + "City: " + city + "\n"
                + "Date: " + date;
    }

}
