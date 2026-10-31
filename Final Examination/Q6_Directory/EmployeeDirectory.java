import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;

public class EmployeeDirectory {

    private List<Employee> employees = new ArrayList<>();
    private Set<String> ids = new HashSet<>();

    // Add Employee and check duplicate ID
    public boolean addEmployee(Employee e) {

        if (ids.contains(e.getEmpId())) {
            return false;
        }

        ids.add(e.getEmpId());
        employees.add(e);

        return true;
    }

    // Sort employees by name A-Z
    public List<Employee> getSortedByName() {

        List<Employee> sorted = new ArrayList<>(employees);

        sorted.sort(
            Comparator.comparing(
                Employee::getName,
                String.CASE_INSENSITIVE_ORDER
            )
        );

        return sorted;
    }

    // Group employees by department
    public Map<String, List<Employee>> groupByDepartment() {

        Map<String, List<Employee>> groups = new TreeMap<>();

        for (Employee e : employees) {

            groups.computeIfAbsent(
                e.getDepartment(),
                k -> new ArrayList<>()
            ).add(e);
        }

        return groups;
    }

    // Find highest paid employee
    public Pair<String, Double> findHighestPaid() {

        if (employees.isEmpty()) {
            return null;
        }

        Employee highest = employees.get(0);

        for (Employee e : employees) {

            if (e.getSalary() > highest.getSalary()) {
                highest = e;
            }
        }

        return new Pair<>(
            highest.getName(),
            highest.getSalary()
        );
    }

    // Search by department
    public List<Employee> searchByDepartment(String dept) {

        List<Employee> result = new ArrayList<>();

        for (Employee e : employees) {

            if (e.getDepartment().equalsIgnoreCase(dept)) {
                result.add(e);
            }
        }

        return result;
    }

    // Generic method
    public static <T> void printList(List<T> list) {

        for (T item : list) {
            System.out.println(item);
        }
    }
}