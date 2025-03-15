/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repairmanagementsystem.demo;

public abstract class Person {
    private String title;
    private String firstName;
    private String lastName;

    // Constructor
    public Person(String title, String firstName, String lastName) {
        this.setTitle(title);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    // Common getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty or null.");
        }
        this.title = title.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty or null.");
        }
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty or null.");
        }
        this.lastName = lastName.trim();
    }

    // Abstract method for getting full details
    public abstract String getFullDetails();
}