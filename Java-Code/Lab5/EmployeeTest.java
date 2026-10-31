public class EmployeeTest {
    public static void main(String[] args) {

        Employee emp1 = new Employee(
            "E001", "สมชาย ใจดี", "ฝ่ายบัญชี", 25000
        );

        Employee emp2 = new Employee(
            "E002", "สมหญิง รักงาน", "ฝ่ายขาย", 28000
        );

        emp1.showInfo();
        emp2.showInfo();
    }
}
