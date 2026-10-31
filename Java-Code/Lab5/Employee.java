public class Employee {
    String employeeId;
    String name;
    String department;
    double salary;

    public Employee(String employeeId, String name,
                    String department, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public void showInfo() {
        System.out.println("รหัสพนักงาน : " + employeeId);
        System.out.println("ชื่อ : " + name);
        System.out.println("แผนก : " + department);
        System.out.println("เงินเดือน : " + salary + " บาท");
        System.out.println("----------------------");
    }
}
