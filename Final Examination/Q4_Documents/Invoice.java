public class Invoice extends Document implements Approvable, Payable {
    private String customer;
    private String itemName;
    private int quantity;
    private double unitPrice;

    public static final double APPROVAL_LIMIT = 50000.0;

    private String status = "PENDING";

    public Invoice(String docId, String title, String createdBy,
                   String customer, String itemName,
                   int quantity, double unitPrice) {

        super(docId, title, createdBy);

        this.customer = customer;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    @Override
    public String getDocumentType() {
        return "INVOICE";
    }

    private double getSubtotal() {
        return quantity * unitPrice;
    }

    @Override
    public double getTotalAmount() {
        double subtotal = getSubtotal();
        double vat = subtotal * VAT_RATE;

        return subtotal + vat;
    }

    @Override
    public void printContent() {
        double subtotal = getSubtotal();
        double vat = subtotal * VAT_RATE;
        double total = subtotal + vat;

        System.out.println("Customer: " + customer);

        System.out.printf(
            "Item: %s x %d @ %,.2f%n",
            itemName,
            quantity,
            unitPrice
        );

        System.out.printf("Subtotal: %,.2f%n", subtotal);
        System.out.printf("VAT 7%%: %,.2f%n", vat);
        System.out.printf("Total: %,.2f%n", total);
    }

    @Override
    public boolean approve(String approver) {
        if (getTotalAmount() > APPROVAL_LIMIT) {
            status = "REJECTED (total exceeds 50,000.00)";
            return false;
        }

        status = "APPROVED by " + approver;
        return true;
    }

    @Override
    public String getStatus() {
        return status;
    }
}