public class InheritanceTest {
    public static void main(String[] args) {

        Manager m = new Manager(
            "คุณสมชาย", 30000, 5000
        );

        Intern i = new Intern(
            "คุณนักศึกษาฝึกงาน", 15000
        );

        m.showInfo();
        i.showInfo();
    }
}
