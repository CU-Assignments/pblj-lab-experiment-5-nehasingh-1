import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L; 
    private String name;
    private int id;
    private String designation;
    private double salary;
    public Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary;
    }
}
public class Project1 {
    private static final String FILE_NAME = "employees.ser"; 
    public static void addEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Designation: ");
        String designation = scanner.nextLine();
        System.out.print("Salary: ");
        double salary = scanner.nextDouble();
        Employee employee = new Employee(name, id, designation, salary);
        saveEmployeeToFile(employee);
        System.out.println("Employee added successfully!");
    }
    private static void saveEmployeeToFile(Employee employee) {
        List<Employee> employees = readEmployeesFromFile(); 
        employees.add(employee); 
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            for (Employee emp : employees) {
                oos.writeObject(emp); 
            }
        } catch (IOException e) {
            System.out.println("Error saving employee to file: " + e.getMessage());
        }
    }
    public static void displayAllEmployees() {
        List<Employee> employees = readEmployeesFromFile();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }
    private static List<Employee> readEmployeesFromFile() {
        List<Employee> employees = new ArrayList<>();
        try {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        Employee employee = (Employee) ois.readObject();
                        employees.add(employee);
                    }
                } catch (EOFException e) {
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error reading employees from file: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return employees;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3 . Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayAllEmployees();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }
}
