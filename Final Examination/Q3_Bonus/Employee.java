public class Employee {
    protected String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return "Employee";
    }

    public double calculateBonus() {
        return salary;
    }

    public double calculateBonus(char grade) {
        double multiplier;

        switch (Character.toUpperCase(grade)) {
            case 'A':
                multiplier = 1.5;
                break;
            case 'B':
                multiplier = 1.2;
                break;
            case 'C':
                multiplier = 1.0;
                break;
            default:
                multiplier = 0.0;
        }

        return calculateBonus() * multiplier;
    }
}