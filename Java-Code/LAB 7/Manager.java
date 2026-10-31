public class Manager extends Employee {

    private double positionAllowance;

    public Manager(String name, double baseSalary,
                   double positionAllowance) {
        super(name, baseSalary);
        this.positionAllowance = positionAllowance;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + positionAllowance;
    }

    @Override
    public void showInfo() {
        System.out.println("ตำแหน่ง : ผู้จัดการ");
        System.out.println("ชื่อ : " + name);
        System.out.println(
            "เงินเดือนพื้นฐาน : " + baseSalary
        );
        System.out.println(
            "เงินประจำตำแหน่ง : " + positionAllowance
        );
        System.out.println(
            "เงินเดือนรวม : " + calculateSalary()
        );
    }
}
