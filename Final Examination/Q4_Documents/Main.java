public class Main {
    public static void main(String[] args) {

        Document[] documents = {
            new Memo(
                "MEM-001",
                "Staff Meeting",
                "HR",
                "All Staff",
                "Meeting on Friday at 10:00 in Room 301"
            ),

            new Invoice(
                "INV-001",
                "Office Supplies",
                "Finance",
                "ABC Co., Ltd.",
                "A4 Paper",
                50,
                120
            ),

            new Invoice(
                "INV-002",
                "Laptop Purchase",
                "Finance",
                "XYZ Co., Ltd.",
                "Laptop",
                3,
                25000
            )
        };

        double totalApproved = 0;

        System.out.println("===== Office Document System =====");

        for (Document doc : documents) {

            doc.printHeader();
            doc.printContent();

            boolean approved = false;

            if (doc instanceof Approvable) {
                Approvable app = (Approvable) doc;

                approved = app.approve("Somchai");

                System.out.println("Status: " + app.getStatus());
            }

            if (approved && doc instanceof Payable) {
                Payable payable = (Payable) doc;

                totalApproved += payable.getTotalAmount();
            }

            System.out.println("----------------------------------------");
        }

        System.out.printf(
            "Total Approved Invoice Amount: %,.2f%n",
            totalApproved
        );
    }
}