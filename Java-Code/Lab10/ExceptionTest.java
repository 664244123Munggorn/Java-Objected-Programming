public class ExceptionTest {

    public static void main(String[] args) {

        Inventory printerPaper =
            new Inventory("กระดาษ A4", 50);

        try {
            printerPaper.withdrawStock(30);
            printerPaper.withdrawStock(40);

        } catch (InsufficientStockException e) {

            System.out.println(
                "เกิดข้อผิดพลาด: " + e.getMessage()
            );

        } finally {
            System.out.println(
                "ตรวจสอบสต๊อกสินค้าเสร็จสิ้น"
            );
        }

        try {
            LeaveRequest.requestLeave(5);
            LeaveRequest.requestLeave(-2);

        } catch (InvalidLeaveDaysException e) {

            System.out.println(
                "เกิดข้อผิดพลาด: " + e.getMessage()
            );
        }
    }
}
