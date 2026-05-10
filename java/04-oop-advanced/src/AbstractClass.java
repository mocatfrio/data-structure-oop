abstract class Employee {
    protected String name;
    protected String id;
    protected double baseSalary;

    public Employee(String name, String id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }

    public abstract double calculateSalary();

    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Total Salary: " + calculateSalary());
    }
}

class FullTimeEmployee extends Employee {
    private double bonus;

    public FullTimeEmployee(String name, String id, double baseSalary, double bonus) {
        super(name, id, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + bonus;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, String id, int hoursWorked, double hourlyRate) {
        super(name, id, 0);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class ContractEmployee extends Employee {
    private int contractMonths;

    public ContractEmployee(String name, String id, double baseSalary, int contractMonths) {
        super(name, id, baseSalary);
        this.contractMonths = contractMonths;
    }

    @Override
    public double calculateSalary() {
        return baseSalary * contractMonths;
    }
}

public class AbstractClass {
    public static void main(String[] args) {
        System.out.println("=== ABSTRACT CLASS ===\n");

        Employee[] employees = {
            new FullTimeEmployee("Alice", "FT001", 5000, 1000),
            new PartTimeEmployee("Bob", "PT001", 80, 25),
            new ContractEmployee("Charlie", "CT001", 4000, 3)
        };

        for (Employee emp : employees) {
            emp.displayInfo();
            System.out.println();
        }
    }
}
