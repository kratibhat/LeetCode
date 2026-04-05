package codingwithhaman;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private double salary;
    private String department;

    public Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return name + " - " + salary + " - " + department;
    }
}

public class topthreeehighestpaidemployeeusingstreams {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 90000, "IT"),
                new Employee("Bob", 120000, "HR"),
                new Employee("Charlie", 110000, "IT"),
                new Employee("David", 95000, "HR"),
                new Employee("Eve", 105000, "IT")
        );

        // Case 1: Top 3 highest-paid employees overall
        System.out.println("Top 3 highest-paid employees:");
        List<Employee> topThree = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(3)
                .collect(Collectors.toList());
        topThree.forEach(System.out::println);

        // Case 2: Sort employees by salary in descending order within each department
        System.out.println("\nEmployees sorted by salary in each department:");
        Map<String, List<Employee>> sortedByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                        .collect(Collectors.toList())
                        )
                ));
        sortedByDept.forEach((dept, empList) -> {
            System.out.println("Department: " + dept);
            empList.forEach(System.out::println);
        });
    }
}
