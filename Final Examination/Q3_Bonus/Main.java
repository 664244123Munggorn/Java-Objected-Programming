import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Employee[] employees = {
            new Employee("Somchai", 20000),
            new SalesEmployee("Malee", 18000, 200000),
            new Manager("Suda", 50000)
        };

        char[] grades = new char[employees.length];

        System.out.println("===== Bonus Calculation System =====");

        for (int i = 0; i < employees.length; i++) {
            System.out.print("Enter grade for " +
                    employees[i].getName() + " (" +
                    employees[i].getType() + ") [A/B/C/D]: ");

            String input = sc.nextLine().trim();

            grades[i] = input.isEmpty()
                    ? 'D'
                    : Character.toUpperCase(input.charAt(0));
        }

        System.out.println();
        System.out.println("----- Bonus Report -----");

        double totalBonus = 0;

        for (int i = 0; i < employees.length; i++) {
            double baseBonus = employees[i].calculateBonus();
            double finalBonus = employees[i].calculateBonus(grades[i]);

            totalBonus += finalBonus;

            System.out.printf(
                "%s | %s | Base Bonus: %,.2f | Grade: %c | Final Bonus: %,.2f%n",
                employees[i].getName(),
                employees[i].getType(),
                baseBonus,
                grades[i],
                finalBonus
            );
        }

        System.out.println("------------------------------");
        System.out.printf("Total Bonus: %,.2f%n", totalBonus);

        sc.close();
    }
}