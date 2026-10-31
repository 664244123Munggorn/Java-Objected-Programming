import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Employee[] employees = new Employee[2];

        System.out.println("===== Payroll System =====");

        // Input Employee Information
        for (int i = 0; i < employees.length; i++) {

            System.out.println("\n[Employee " + (i + 1) + "]");

            System.out.print("Employee ID: ");
            String id = sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Department: ");
            String department = sc.nextLine();

            System.out.print("Base Salary: ");
            double salary = Double.parseDouble(sc.nextLine());

            System.out.print("Overtime Hours: ");
            int hours = Integer.parseInt(sc.nextLine());

            employees[i] = new Employee(
                id,
                name,
                department,
                salary,
                hours
            );
        }

        // Display Payslips
        System.out.println("\n===== Payslips =====");

        double totalNetSalary = 0;

        for (Employee emp : employees) {

            emp.printPayslip();

            totalNetSalary += emp.calculateNetSalary();

            System.out.println();
        }

        // Display Summary
        System.out.println("===== Summary =====");

        System.out.println(
            "Total employees created: "
            + Employee.getEmployeeCount()
        );

        System.out.printf(
            "Total net salary: %.2f%n",
            totalNetSalary
        );

        // Test Setter
        System.out.println("\n===== Setter Test =====");

        System.out.println(
            "Test: setBaseSalary(-1000) on "
            + employees[0].getEmpId()
        );

        employees[0].setBaseSalary(-1000);

        System.out.printf(
            "Base salary after test: %.2f%n",
            employees[0].getBaseSalary()
        );

        sc.close();
    }
}