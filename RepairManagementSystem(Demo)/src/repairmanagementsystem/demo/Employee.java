package repairmanagementsystem.demo;

/**
 * Employee class represents an employee in the repair management system.
 * Extends the Person class and implements the displayDetails method.
 */
class Employee extends Person {
    private String employeeId;
    private String employeeName;  // Assuming a single field for full name
    private String employeeAddress;
    private String employeeDateOfBirth;

    /**
     * Constructor for creating an Employee object.
     *
     * @param employeeId          The ID of the employee.
     * @param employeeName        The name of the employee.
     * @param employeeAddress     The address of the employee.
     * @param employeeDateOfBirth The date of birth of the employee.
     */
    public Employee(String employeeId, String employeeName, String employeeAddress, String employeeDateOfBirth) {
        super(employeeName.split("\\s+")[0], employeeName.split("\\s+")[1]);  // Assuming first and last name
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
        this.employeeDateOfBirth = employeeDateOfBirth;
    }

    // Implementing the abstract method from the Person class
    @Override
    public void displayDetails() {
        System.out.println("Employee Details:");
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + employeeName);
        System.out.println("Address: " + employeeAddress);
        System.out.println("Date of Birth: " + employeeDateOfBirth);
        // Additional employee-specific details...
    }

    // Additional methods and code specific to the Employee class can be added here...
}
