import java.util.ArrayList;

public class OfficeSystem {

    private ArrayList<Employee> employees =
        new ArrayList<>();

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public double getTotalPayroll() {

        double total = 0;

        for (Employee e : employees) {
            total += e.calculateSalary();
        }

        return total;
    }

    public void printReport() {

        System.out.println(
            "========== รายงานเงินเดือนพนักงาน =========="
        );

        for (Employee e : employees) {
            e.showInfo();
        }

        System.out.printf(
            "รวมค่าใช้จ่ายเงินเดือนทั้งหมด: %.2f บาท%n",
            getTotalPayroll()
        );

        System.out.println(
            "=========================================="
        );
    }
}
