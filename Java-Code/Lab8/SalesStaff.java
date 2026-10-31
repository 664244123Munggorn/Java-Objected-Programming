public class SalesStaff extends Employee {

    private double salesAmount;

    public SalesStaff(String name, double baseSalary,
                      double salesAmount) {
        super(name, baseSalary);
        this.salesAmount = salesAmount;
    }

    @Override
    public double calculateBonus() {
        return salesAmount * 0.02;
    }
}
