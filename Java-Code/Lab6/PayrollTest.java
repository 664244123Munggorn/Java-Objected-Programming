public class PayrollTest {
    public static void main(String[] args) {

        Payroll p1 = new Payroll(
            "E001", "สมชาย ใจดี", 25000
        );

        p1.setBonus(2000);
        p1.printPayslip();

        Payroll p2 = new Payroll(
            "E002", "สมหญิง รักงาน", -5000
        );

        p2.printPayslip();
    }
}
