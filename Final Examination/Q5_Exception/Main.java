import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LeaveAccount leave = new LeaveAccount("Somchai", 10);

        Product[] products = {
            new Product("P001", "Notebook", 20),
            new Product("P002", "Pen", 100),
            new Product("P003", "Stapler", 5)
        };

        Inventory inventory = new Inventory(products);

        System.out.println("===== Leave & Inventory System =====");

        // Part A: Leave Request
        System.out.println("--- Part A: Leave Request ---");

        System.out.printf(
            "Employee: %s (Leave balance: %d days)%n",
            leave.getName(),
            leave.getBalance()
        );

        while (true) {

            System.out.print("Enter leave days: ");
            String input = sc.nextLine().trim();

            try {
                int days = Integer.parseInt(input);

                leave.requestLeave(days);

                System.out.printf(
                    "Leave approved: %d days. Remaining balance: %d days.%n",
                    days,
                    leave.getBalance()
                );

                break;

            } catch (NumberFormatException e) {

                System.out.println(
                    "Error: Please enter a whole number."
                );

            } catch (InsufficientLeaveException |
                     IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());
            }
        }

        // Part B: Inventory Withdrawal
        System.out.println();
        System.out.println("--- Part B: Inventory Withdrawal ---");

        while (true) {

            System.out.print("Enter product code (0 to exit): ");
            String code = sc.nextLine().trim();

            if (code.equals("0")) {
                break;
            }

            try {
                System.out.print("Enter quantity: ");

                String quantityInput = sc.nextLine().trim();

                int quantity = Integer.parseInt(quantityInput);

                Product product = inventory.findProduct(code);

                product.withdraw(quantity);

                System.out.printf(
                    "Success: Withdrew %d x %s. Remaining stock: %d%n",
                    quantity,
                    product.getName(),
                    product.getStock()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                    "Error: Quantity must be a whole number."
                );

            } catch (ProductNotFoundException |
                     InsufficientStockException |
                     IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());

            } finally {

                System.out.println("[Transaction finished]");
            }
        }

        // Final Stock
        System.out.println();
        System.out.println("===== Final Stock =====");

        for (Product product : inventory.getProducts()) {

            System.out.printf(
                "%s %s: %d%n",
                product.getCode(),
                product.getName(),
                product.getStock()
            );
        }

        sc.close();
    }
}