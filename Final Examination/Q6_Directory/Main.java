import java.util.Scanner;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EmployeeDirectory directory = new EmployeeDirectory();

        System.out.println("===== Employee Directory =====");

        System.out.print("Enter number of employees: ");
        int count = Integer.parseInt(sc.nextLine().trim());

        // Input Employees
        for (int i = 1; i <= count; i++) {

            System.out.print(
                "Employee " + i +
                " (id,name,department,salary): "
            );

            String input = sc.nextLine();

            String[] data = input.split(",");

            String id = data[0].trim();
            String name = data[1].trim();
            String department = data[2].trim();
            double salary = Double.parseDouble(data[3].trim());

            Employee employee = new Employee(
                id,
                name,
                department,
                salary
            );

            if (!directory.addEmployee(employee)) {

                System.out.println(
                    "Duplicate ID " + id + " - skipped."
                );
            }
        }

        // All Employees
        System.out.println();

        System.out.println(
            "----- All Employees (sorted by name) -----"
        );

        EmployeeDirectory.printList(
            directory.getSortedByName()
        );

        // Department Summary
        System.out.println();

        System.out.println("----- Department Summary -----");

        Map<String, List<Employee>> groups =
            directory.groupByDepartment();

        for (Map.Entry<String, List<Employee>> entry
                : groups.entrySet()) {

            String department = entry.getKey();

            List<Employee> employees = entry.getValue();

            double total = 0;

            for (Employee e : employees) {
                total += e.getSalary();
            }

            double average = total / employees.size();

            System.out.printf(
                "%s: %d employee(s), Total: %,.2f, Average: %,.2f%n",
                department,
                employees.size(),
                total,
                average
            );
        }

        // Highest Paid
        Pair<String, Double> highest =
            directory.findHighestPaid();

        if (highest != null) {

            System.out.printf(
                "%nHighest Paid: %s (%,.2f)%n",
                highest.getKey(),
                highest.getValue()
            );
        }

        // Search Department
        System.out.print("\nEnter department to search: ");

        String search = sc.nextLine().trim();

        List<Employee> result =
            directory.searchByDepartment(search);

        if (result.isEmpty()) {

            System.out.println(
                "No employees found in department: " + search
            );

        } else {

            String department =
                result.get(0).getDepartment();

            System.out.printf(
                "----- Employees in %s (%d) -----%n",
                department,
                result.size()
            );

            EmployeeDirectory.printList(result);
        }

        sc.close();
    }
}