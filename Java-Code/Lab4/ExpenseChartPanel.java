import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ExpenseChartPanel extends JPanel {

    private Map<String, Double> expenseData;

    public ExpenseChartPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500, 300));
    }

    public void setExpenseData(Map<String, Double> data) {
        this.expenseData = data;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setFont(new Font("Tahoma", Font.BOLD, 16));
        g2.setColor(new Color(40, 55, 80));
        g2.drawString("กราฟเปรียบเทียบค่าใช้จ่าย", 20, 30);

        if (expenseData == null || expenseData.isEmpty()) {
            g2.setFont(new Font("Tahoma", Font.PLAIN, 14));
            g2.setColor(Color.GRAY);
            g2.drawString("ยังไม่มีข้อมูลค่าใช้จ่าย", 30, 90);
            g2.dispose();
            return;
        }

        int left = 120;
        int top = 60;
        int chartWidth = Math.max(100, getWidth() - 160);
        int barHeight = 25;
        int gap = 18;

        double max = 0;

        for (double value : expenseData.values()) {
            max = Math.max(max, value);
        }

        if (max == 0) {
            max = 1;
        }

        Color[] colors = {
            new Color(52, 152, 219),
            new Color(46, 204, 113),
            new Color(241, 196, 15),
            new Color(231, 76, 60),
            new Color(155, 89, 182),
            new Color(26, 188, 156)
        };

        int index = 0;

        for (Map.Entry<String, Double> entry :
                expenseData.entrySet()) {

            int y = top + index * (barHeight + gap);

            String category = entry.getKey();
            double amount = entry.getValue();

            int barWidth = (int) (
                (amount / max) * chartWidth
            );

            g2.setFont(new Font("Tahoma", Font.PLAIN, 12));
            g2.setColor(Color.DARK_GRAY);
            g2.drawString(category, 15, y + 18);

            g2.setColor(new Color(235, 238, 242));
            g2.fillRoundRect(
                left, y, chartWidth, barHeight, 10, 10
            );

            g2.setColor(colors[index % colors.length]);
            g2.fillRoundRect(
                left, y, Math.max(2, barWidth),
                barHeight, 10, 10
            );

            g2.setColor(Color.DARK_GRAY);
            g2.drawString(
                String.format("%,.2f บาท", amount),
                left + barWidth + 8,
                y + 18
            );

            index++;
        }

        g2.dispose();
    }
}
