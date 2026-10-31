import java.util.*;

public class CollectionTest {

    public static void main(String[] args) {

        ArrayList<Employee> empList = new ArrayList<>();

        empList.add(
            new Employee("E001", "สมชาย", 25000)
        );

        empList.add(
            new Employee("E002", "สมหญิง", 32000)
        );

        empList.add(
            new Employee("E003", "วิชัย", 18000)
        );

        System.out.println("=== รายชื่อพนักงาน ===");

        for (Employee e : empList) {
            System.out.println(e);
        }

        // เรียงเงินเดือนจากมากไปน้อย
        empList.sort(
            (a, b) -> Double.compare(b.salary, a.salary)
        );

        System.out.println(
            "\n=== เรียงตามเงินเดือน (มากไปน้อย) ==="
        );

        for (Employee e : empList) {
            System.out.println(e);
        }

        // HashMap เก็บจำนวนพนักงานแต่ละแผนก
        HashMap<String, Integer> deptCount =
            new HashMap<>();

        deptCount.put("บัญชี", 5);
        deptCount.put("ฝ่ายขาย", 3);
        deptCount.put("ไอที", 4);

        System.out.println(
            "\n=== จำนวนพนักงานแต่ละแผนก ==="
        );

        for (Map.Entry<String, Integer> entry
                : deptCount.entrySet()) {

            System.out.println(
                entry.getKey() + ": "
                + entry.getValue() + " คน"
            );
        }

        // ค้นหาพนักงานด้วยรหัส
        HashMap<String, Employee> employeesById =
            new HashMap<>();

        for (Employee e : empList) {
            employeesById.put(e.id, e);
        }

        System.out.println(
            "\nค้นหา E001: " + employeesById.get("E001")
        );

        // กรองเงินเดือนมากกว่า 20,000
        System.out.println(
            "\n=== เงินเดือนมากกว่า 20,000 ==="
        );

        empList.stream()
            .filter(e -> e.salary > 20000)
            .forEach(System.out::println);
    }
}
