import java.util.ArrayList;

public class PolymorphismTest {
    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(
            new Employee("พนักงาน A", 18000)
        );

        employees.add(
            new SalesStaff("พนักงาน B", 15000, 100000)
        );

        employees.add(
            new Manager("ผู้จัดการ C", 35000)
        );

        employees.add(
            new TechnicalStaff("ช่างเทคนิค D", 20000, 8, 500)
        );

        for (Employee e : employees) {
            e.showBonus();
        }

        System.out.printf(
            "โบนัส Overloading 10%%: %.2f บาท%n",
            employees.get(0).calculateBonus(0.10)
        );

        System.out.printf(
            "โบนัสแบบกำหนดเพดาน: %.2f บาท%n",
            employees.get(0).calculateBonus(0.10, 1500)
        );
    }
}
