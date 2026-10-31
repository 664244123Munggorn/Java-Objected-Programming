public class Manager extends Employee {
    private double allowance;

    public Manager(String empId, String name,
                   double baseSalary, double allowance) {
        super(empId, name, baseSalary);
        this.allowance = allowance;
    }

    @Override
    public double calculateMonthlyPay() {
        return baseSalary + allowance;
    }

    @Override
    public void displayInfo() {
        System.out.printf(
            "[Manager] %s | %s | Monthly Pay: %,.2f (Allowance: %,.2f)%n",
            empId, name, calculateMonthlyPay(), allowance
        );
    }
}