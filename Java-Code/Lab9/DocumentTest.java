public class DocumentTest {
    public static void main(String[] args)
            throws Exception {

        Memo memo = new Memo(
            "บันทึกแจ้งเตือน",
            "ฝ่าย HR",
            "แจ้งวันหยุดประจำเดือน"
        );

        Invoice invoice = new Invoice(
            "ใบแจ้งหนี้ #001",
            "ฝ่ายบัญชี",
            15000
        );

        memo.printContent();
        invoice.printContent();

        invoice.markAsPaid();

        invoice.printContent();

        invoice.printToFile("invoice.txt");

        System.out.println("บันทึกไฟล์เรียบร้อย");
    }
}
