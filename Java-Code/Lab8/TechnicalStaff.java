public class TechnicalStaff extends Employee {

    private int completedJobs;
    private double bonusPerJob;

    public TechnicalStaff(String name, double baseSalary,
                          int completedJobs,
                          double bonusPerJob) {
        super(name, baseSalary);
        this.completedJobs = completedJobs;
        this.bonusPerJob = bonusPerJob;
    }

    @Override
    public double calculateBonus() {
        return completedJobs * bonusPerJob;
    }
}
