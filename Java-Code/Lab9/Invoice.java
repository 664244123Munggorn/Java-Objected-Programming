import java.io.FileWriter;
import java.io.IOException;

public class Invoice extends Document
        implements Payable, Printable {

    private double amount;
    private boolean paid = false;

    public Invoice(String title, String createdBy,
                   double amount) {
        super(title, createdBy);
        this.amount = amount;
    }

    @Override
    public void printContent() {
        showHeader();

        System.out.printf(
            "จำนวนเงิน: %.2f บาท | สถานะ: %s%n",
            amount,
            paid ? "ชำระแล้ว" : "ยังไม่ชำระ"
        );
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void markAsPaid() {
        paid = true;
    }

    @Override
    public void printToFile(String fileName)
            throws IOException {

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("เอกสาร: " + title + "\n");
            writer.write("ผู้จัดทำ: " + createdBy + "\n");
            writer.write("จำนวนเงิน: " + amount + " บาท\n");
            writer.write(
                "สถานะ: " + (paid ? "ชำระแล้ว" : "ยังไม่ชำระ")
            );
        }
    }
}
