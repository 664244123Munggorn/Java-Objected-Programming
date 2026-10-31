public class Payroll {

    private String employeeId;
    private String name;
    private double baseSalary;
    private double bonus;

    public Payroll(String employeeId, String name,
                   double baseSalary) {
        this.employeeId = employeeId;
        this.name = name;
        setBaseSalary(baseSalary);
        this.bonus = 0;
    }

    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0) {
            System.out.println(
                "แจ้งเตือน: เงินเดือนต้องไม่ติดลบ"
            );
            this.baseSalary = 0;
        } else {
            this.baseSalary = baseSalary;
        }
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double calculateNetSalary() {
        double socialSecurity = baseSalary * 0.05;

        double tax = (baseSalary > 20000)
                ? baseSalary * 0.03 : 0;

        return baseSalary + bonus
                - socialSecurity - tax;
    }

    public void printPayslip() {
        System.out.println("===== ใบแจ้งเงินเดือน =====");
        System.out.println("รหัสพนักงาน : " + employeeId);
        System.out.println("ชื่อ : " + name);

        System.out.printf(
            "เงินเดือนพื้นฐาน : %.2f บาท%n", baseSalary
        );

        System.out.printf(
            "โบนัส : %.2f บาท%n", bonus
        );

        System.out.printf(
            "เงินเดือนสุทธิ : %.2f บาท%n",
            calculateNetSalary()
        );

        System.out.println("==========================");
    }
}
