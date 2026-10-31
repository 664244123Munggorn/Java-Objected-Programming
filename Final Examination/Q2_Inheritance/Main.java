public class Main {
    public static void main(String[] args) {

        Employee employee = new Employee(
            "E001", "Somchai Jaidee", 25000
        );

        Employee manager = new Manager(
            "M001", "Suda Rakdee", 50000, 10000
        );

        Employee intern = new Intern(
            "I001", "Anan Sukjai", 20, 500
        );

        Employee[] employees = {
            employee, manager, intern
        };

        System.out.println("===== Employee Types =====");

        double totalPayroll = 0;

        for (Employee e : employees) {
            e.displayInfo();
            totalPayroll += e.calculateMonthlyPay();
        }

        System.out.println("------------------------------");

        System.out.printf(
            "Total Payroll: %,.2f%n", totalPayroll
        );

        System.out.println(
            "Is Manager an Employee? " + (manager instanceof Employee)
        );
    }
}