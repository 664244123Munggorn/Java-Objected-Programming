public class Employee {
    protected String empId;
    protected String name;
    protected double baseSalary;

    public Employee(String empId, String name, double baseSalary) {
        this.empId = empId;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public double calculateMonthlyPay() {
        return baseSalary;
    }

    public void displayInfo() {
        System.out.printf(
            "[Employee] %s | %s | Monthly Pay: %,.2f%n",
            empId, name, calculateMonthlyPay()
        );
    }
}