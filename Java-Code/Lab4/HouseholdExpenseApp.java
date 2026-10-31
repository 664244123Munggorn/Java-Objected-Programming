import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class HouseholdExpenseApp extends JFrame {

    private ArrayList<HouseholdExpense> expenses =
            new ArrayList<>();

    private JComboBox<String> categoryBox;
    private JTextField amountField;
    private JTextField noteField;

    private JLabel totalLabel;
    private JTable expenseTable;
    private DefaultTableModel tableModel;

    private ExpenseChartPanel chartPanel;

    private final String[] categories = {
        "ค่าอาหาร",
        "ค่าเดินทาง",
        "ค่าน้ำ",
        "ค่าไฟ",
        "ค่าของใช้",
        "อื่น ๆ"
    };

    public HouseholdExpenseApp() {
        setTitle("ระบบจัดการค่าใช้จ่ายครัวเรือน");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createComponents();
        updateDashboard();
    }

    private void createComponents() {

        setLayout(new BorderLayout(10, 10));

        // หัวข้อโปรแกรม
        JLabel title = new JLabel(
            "ระบบจัดการค่าใช้จ่ายครัวเรือน",
            SwingConstants.CENTER
        );

        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(new Color(35, 75, 120));
        title.setBorder(
            BorderFactory.createEmptyBorder(20, 10, 10, 10)
        );

        add(title, BorderLayout.NORTH);

        // ส่วนกรอกข้อมูล
        JPanel inputPanel = new JPanel(
            new GridLayout(2, 4, 10, 10)
        );

        inputPanel.setBorder(
            BorderFactory.createTitledBorder(
                "เพิ่มรายการค่าใช้จ่าย"
            )
        );

        categoryBox = new JComboBox<>(categories);
        amountField = new JTextField();
        noteField = new JTextField();

        JButton addButton = new JButton("เพิ่มรายการ");

        inputPanel.add(new JLabel("หมวดหมู่"));
        inputPanel.add(new JLabel("จำนวนเงิน (บาท)"));
        inputPanel.add(new JLabel("รายละเอียด"));
        inputPanel.add(new JLabel(""));

        inputPanel.add(categoryBox);
        inputPanel.add(amountField);
        inputPanel.add(noteField);
        inputPanel.add(addButton);

        // ตารางแสดงข้อมูล
        String[] columns = {
            "วันที่", "หมวดหมู่", "จำนวนเงิน (บาท)", "รายละเอียด"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        expenseTable = new JTable(tableModel);
        expenseTable.setRowHeight(28);

        JScrollPane tableScroll = new JScrollPane(expenseTable);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(
            BorderFactory.createTitledBorder(
                "รายการค่าใช้จ่าย"
            )
        );
        tablePanel.add(tableScroll, BorderLayout.CENTER);

        // Dashboard
        chartPanel = new ExpenseChartPanel();

        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBorder(
            BorderFactory.createTitledBorder("Dashboard")
        );
        dashboardPanel.add(chartPanel, BorderLayout.CENTER);

        totalLabel = new JLabel(
            "ยอดรวมค่าใช้จ่าย: 0.00 บาท"
        );

        totalLabel.setFont(
            new Font("Tahoma", Font.BOLD, 18)
        );
        totalLabel.setForeground(new Color(220, 70, 70));

        JPanel summaryPanel = new JPanel(
            new FlowLayout(FlowLayout.LEFT)
        );
        summaryPanel.add(totalLabel);

        dashboardPanel.add(summaryPanel, BorderLayout.SOUTH);

        // แบ่งพื้นที่ตารางกับกราฟ
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.VERTICAL_SPLIT,
            tablePanel,
            dashboardPanel
        );

        splitPane.setResizeWeight(0.5);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(
            BorderFactory.createEmptyBorder(5, 15, 15, 15)
        );

        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(splitPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // ปุ่มเพิ่มรายการ
        addButton.addActionListener(e -> addExpense());

        ThaiFontSupport.applyTo(this);
    }

    private void addExpense() {

        try {
            String category =
                    categoryBox.getSelectedItem().toString();

            String amountText =
                    amountField.getText().trim();

            String note = noteField.getText().trim();

            if (amountText.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "กรุณากรอกจำนวนเงิน"
                );
                return;
            }

            double amount = Double.parseDouble(amountText);

            if (amount <= 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "จำนวนเงินต้องมากกว่า 0"
                );
                return;
            }

            HouseholdExpense expense =
                    new HouseholdExpense(
                        category,
                        amount,
                        note,
                        LocalDate.now()
                    );

            expenses.add(expense);

            String date = expense.getDate().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
            );

            tableModel.addRow(new Object[]{
                date,
                category,
                String.format("%,.2f", amount),
                note
            });

            amountField.setText("");
            noteField.setText("");

            updateDashboard();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "กรุณากรอกจำนวนเงินเป็นตัวเลขเท่านั้น"
            );
        }
    }

    private void updateDashboard() {

        double total = 0;

        Map<String, Double> categoryTotals =
                new LinkedHashMap<>();

        for (String category : categories) {
            categoryTotals.put(category, 0.0);
        }

        for (HouseholdExpense expense : expenses) {

            total += expense.getAmount();

            String category = expense.getCategory();

            categoryTotals.put(
                category,
                categoryTotals.get(category)
                    + expense.getAmount()
            );
        }

        totalLabel.setText(
            String.format(
                "ยอดรวมค่าใช้จ่าย: %,.2f บาท",
                total
            )
        );

        chartPanel.setExpenseData(categoryTotals);
    }

    public static void main(String[] args) {

        ThaiFontSupport.apply();

        SwingUtilities.invokeLater(() -> {
            new HouseholdExpenseApp().setVisible(true);
        });
    }
}
