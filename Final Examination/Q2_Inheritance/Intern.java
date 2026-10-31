public class Intern extends Employee {
    private int workingDays;
    private double dailyRate;

    public Intern(String empId, String name,
                  int workingDays, double dailyRate) {
        super(empId, name, 0);
        this.workingDays = workingDays;
        this.dailyRate = dailyRate;
    }

    @Override
    public double calculateMonthlyPay() {
        return workingDays * dailyRate;
    }

    @Override
    public void displayInfo() {
        System.out.printf(
            "[Intern] %s | %s | Monthly Pay: %,.2f (%d days x %,.2f)%n",
            empId, name, calculateMonthlyPay(),
            workingDays, dailyRate
        );
    }
}