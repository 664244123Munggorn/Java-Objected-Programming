public class Employee {

    // Attributes
    private String empId;
    private String name;
    private String department;
    private double baseSalary;
    private int overtimeHours;

    // Static variable
    private static int employeeCount = 0;

    // Constructor
    public Employee(String empId, String name, String department,
                    double baseSalary, int overtimeHours) {

        this.empId = empId;
        this.name = name;
        this.department = department;

        setBaseSalary(baseSalary);
        setOvertimeHours(overtimeHours);

        employeeCount++;
    }

    // Setter
    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0) {
            System.out.println(
                "Error: Base salary cannot be negative."
            );
        } else {
            this.baseSalary = baseSalary;
        }
    }

    public void setOvertimeHours(int overtimeHours) {
        if (overtimeHours < 0) {
            System.out.println(
                "Error: Overtime hours cannot be negative."
            );
        } else {
            this.overtimeHours = overtimeHours;
        }
    }

    // Getter
    public String getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }

    // Calculate Overtime Pay
    public double calculateOvertimePay() {
        return (baseSalary / 30.0 / 8.0) * 1.5 * overtimeHours;
    }

    // Calculate Social Security
    public double calculateSocialSecurity() {
        return Math.min(baseSalary * 0.05, 750);
    }

    // Calculate Net Salary
    public double calculateNetSalary() {
        return baseSalary
                + calculateOvertimePay()
                - calculateSocialSecurity();
    }

    // Display Payslip
    public void printPayslip() {
        System.out.println("===== Payslip =====");
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);

        System.out.printf("Base Salary: %.2f%n", baseSalary);
        System.out.printf("Overtime Pay: %.2f%n", calculateOvertimePay());
        System.out.printf("Social Security: %.2f%n", calculateSocialSecurity());
        System.out.printf("Net Salary: %.2f%n", calculateNetSalary());
    }
}