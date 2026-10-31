public class Memo extends Document implements Approvable {
    private String to;
    private String message;
    private String status = "PENDING";

    public Memo(String docId, String title, String createdBy,
                String to, String message) {

        super(docId, title, createdBy);
        this.to = to;
        this.message = message;
    }

    @Override
    public String getDocumentType() {
        return "MEMO";
    }

    @Override
    public void printContent() {
        System.out.println("To: " + to);
        System.out.println("Message: " + message);
    }

    @Override
    public boolean approve(String approver) {
        status = "APPROVED by " + approver;
        return true;
    }

    @Override
    public String getStatus() {
        return status;
    }
}