public class OfficeSystemTest {

    public static void main(String[] args) {

        OfficeSystem office = new OfficeSystem();

        try {

            office.addEmployee(
                new Staff("E001", "สมชาย ใจดี", 20000)
            );

            office.addEmployee(
                new Manager(
                    "E002", "สมหญิง บริหารเก่ง",
                    35000, 8000
                )
            );

            office.addEmployee(
                new Staff("E003", "พนักงานใหม่", -5000)
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                "เกิดข้อผิดพลาดในการเพิ่มพนักงาน: "
                + e.getMessage()
            );
        }

        office.printReport();
    }
}
