public class Employee {

    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public double calculateBonus() {
        return baseSalary * 0.05;
    }

    // Method Overloading
    public double calculateBonus(double percentage) {
        return baseSalary * percentage;
    }

    public double calculateBonus(
        double percentage, double capAmount
    ) {
        return Math.min(
            baseSalary * percentage, capAmount
        );
    }

    public void showBonus() {
        System.out.printf(
            "%s ได้รับโบนัส: %.2f บาท%n",
            name, calculateBonus()
        );
    }
}
