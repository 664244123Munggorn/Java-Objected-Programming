import java.time.LocalDate;

public class HouseholdExpense {
    private String category;
    private double amount;
    private String note;
    private LocalDate date;

    public HouseholdExpense(String category, double amount,
                            String note, LocalDate date) {
        this.category = category;
        this.amount = amount;
        this.note = note;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public LocalDate getDate() {
        return date;
    }
}
