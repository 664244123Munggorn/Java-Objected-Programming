import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;

/**
 * ปฏิทินจีน 2570 (2027) - ปีมะแม (丁未年 - Year of the Goat)
 * Chinese Calendar with lunar dates, festivals, and solar terms
 */
public class Lab3 extends JFrame {

    private static final int YEAR_GREGORIAN = 2027;
    private static final int YEAR_BE = 2570;

    // Thai month names
    private static final String[] THAI_MONTHS = {
        "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน",
        "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"
    };

    // Chinese lunar month names
    private static final String[] CN_MONTHS = {
        "正月", "二月", "三月", "四月", "五月", "六月",
        "七月", "八月", "九月", "十月", "十一月", "十二月"
    };

    // Chinese day names (1-based: day 1 = 初一)
    private static final String[] CN_DAYS = {
        "初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十",
        "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十",
        "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"
    };

    // Chinese Heavenly Stems and Earthly Branches for Year
    private static final String STEM_BRANCH_YEAR = "丁未年";

    // 2027 (2570) Lunar calendar data
    // Each lunar month: {start_month (1-12), start_day, days_in_month}
    private static final int[][] LUNAR_MONTHS = {
        {2, 6, 30},   // Month 1 (正月) CNY Feb 6
        {3, 8, 29},   // Month 2 (二月)
        {4, 6, 30},   // Month 3 (三月)
        {5, 6, 29},   // Month 4 (四月)
        {6, 4, 30},   // Month 5 (五月)
        {7, 4, 29},   // Month 6 (六月)
        {8, 2, 30},   // Month 7 (七月)
        {8, 31, 29},  // Month 8 (八月)
        {9, 29, 30},  // Month 9 (九月)
        {10, 29, 29}, // Month 10 (十月)
        {11, 27, 30}, // Month 11 (十一月)
        {12, 27, 29}  // Month 12 (十二月)
    };

    // Chinese Festivals 2027 (Gregorian YYYY-MM-DD -> Festival Name)
    private static final LinkedHashMap<String, String> FESTIVALS = new LinkedHashMap<>();
    static {
        FESTIVALS.put("2027-02-06", "ตรุษจีน (春节)");
        FESTIVALS.put("2027-02-12", "วันไหว้พระจันทร์ (元宵节)");
        FESTIVALS.put("2027-04-05", "เชงเม้ง (清明节)");
        FESTIVALS.put("2027-05-31", "วันไหว้บ๊ะจ่าง (端午节)");
        FESTIVALS.put("2027-08-07", "วันแห่งความรัก (七夕节)");
        FESTIVALS.put("2027-08-17", "สารทจีน (中元节)");
        FESTIVALS.put("2027-09-29", "วันไหว้พระจันทร์ (中秋节)");
        FESTIVALS.put("2027-10-28", "วันไหว้บรรพบุรุษ (重阳节)");
        FESTIVALS.put("2027-12-22", "วันเหมายัน (冬至)");
    }

    // 24 Solar Terms (节气) dates for 2027
    private static final LinkedHashMap<String, String> SOLAR_TERMS = new LinkedHashMap<>();
    static {
        SOLAR_TERMS.put("2027-01-05", "เสี่ยวหาน (小寒)");
        SOLAR_TERMS.put("2027-01-20", "ต้าหาน (大寒)");
        SOLAR_TERMS.put("2027-02-04", "ลี่ชุน (立春)");
        SOLAR_TERMS.put("2027-02-19", "อวี่สุ่ย (雨水)");
        SOLAR_TERMS.put("2027-03-06", "จิงเจ๋อ (惊蛰)");
        SOLAR_TERMS.put("2027-03-21", "ชุนเฟิน (春分)");
        SOLAR_TERMS.put("2027-04-05", "ชิงหมิง (清明)");
        SOLAR_TERMS.put("2027-04-20", "กู๋อวี่ (谷雨)");
        SOLAR_TERMS.put("2027-05-06", "ลี่เซี่ย (立夏)");
        SOLAR_TERMS.put("2027-05-21", "เสี่ยวหมาน (小满)");
        SOLAR_TERMS.put("2027-06-06", "มั่งจง (芒种)");
        SOLAR_TERMS.put("2027-06-21", "เซี่ยจื้อ (夏至)");
        SOLAR_TERMS.put("2027-07-07", "เสี่ยวสู่ (小暑)");
        SOLAR_TERMS.put("2027-07-23", "ต้าสู่ (大暑)");
        SOLAR_TERMS.put("2027-08-07", "ลี่ชิว (立秋)");
        SOLAR_TERMS.put("2027-08-23", "ชู่สู่ (处暑)");
        SOLAR_TERMS.put("2027-09-07", "ไป๋ลู่ (白露)");
        SOLAR_TERMS.put("2027-09-23", "ชิวเฟิน (秋分)");
        SOLAR_TERMS.put("2027-10-08", "หานลู่ (寒露)");
        SOLAR_TERMS.put("2027-10-23", "ซวงเจี้ยง (霜降)");
        SOLAR_TERMS.put("2027-11-07", "ลี่ตง (立冬)");
        SOLAR_TERMS.put("2027-11-22", "เสี่ยวเสวี่ย (小雪)");
        SOLAR_TERMS.put("2027-12-07", "ต้าเสวี่ย (大雪)");
        SOLAR_TERMS.put("2027-12-22", "ตงจื้อ (冬至)");
    }

    // === UI Components ===
    private JTabbedPane monthTabs;
    private Color redColor = new Color(200, 35, 35);
    private Color darkRed = new Color(160, 20, 20);
    private Color goldColor = new Color(255, 215, 0);
    private Color creamBg = new Color(255, 248, 235);
    private Color lightRed = new Color(255, 235, 235);
    private Color lightBlue = new Color(235, 240, 255);
    private Color festiveBg = new Color(255, 235, 200);
    private Color chineseText = new Color(150, 30, 30);

    public Lab3() {
        super("ปฏิทินจีน " + YEAR_BE + " / " + YEAR_GREGORIAN + " - ปีมะแม (Goat) " + STEM_BRANCH_YEAR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1150, 820);
        setLocationRelativeTo(null);
        setResizable(true);

        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(redColor);

        // === Top Header ===
        mainPanel.add(createHeader(), BorderLayout.NORTH);

        // === Tabbed Months ===
        monthTabs = new JTabbedPane();
        monthTabs.setFont(new Font("Segoe UI", Font.BOLD, 14));
        monthTabs.setBackground(darkRed);
        monthTabs.setForeground(Color.WHITE);
        monthTabs.setBorder(BorderFactory.createEmptyBorder());

        for (int m = 0; m < 12; m++) {
            JPanel monthPanel = createMonthPanel(m);
            monthTabs.addTab(getShortThaiMonth(m) + " " + YEAR_BE, monthPanel);
        }

        mainPanel.add(monthTabs, BorderLayout.CENTER);

        // === Footer Legend ===
        mainPanel.add(createFooter(), BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(darkRed);
        header.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        // Main title
        JLabel title = new JLabel("ปฏิทินจีน (Chinese Calendar)", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(goldColor);

        // Subtitle
        JLabel sub = new JLabel(
            YEAR_BE + " / " + YEAR_GREGORIAN + "  |  ปีมะแม  " + STEM_BRANCH_YEAR + "  (Year of the Goat)",
            SwingConstants.CENTER
        );
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sub.setForeground(Color.WHITE);

        // Zodiac icon area
        JLabel goatIcon = new JLabel("🐐 ปีมะแม 羊年 丁未", SwingConstants.CENTER);
        goatIcon.setFont(new Font("Segoe UI", Font.BOLD, 16));
        goatIcon.setForeground(new Color(255, 200, 100));

        JPanel textPanel = new JPanel(new GridLayout(3, 1, 2, 2));
        textPanel.setOpaque(false);
        textPanel.add(title);
        textPanel.add(sub);
        textPanel.add(goatIcon);

        header.add(textPanel, BorderLayout.CENTER);
        return header;
    }

    private String getShortThaiMonth(int m) {
        String[] shortNames = {"ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.", "พ.ค.", "มิ.ย.",
                               "ก.ค.", "ส.ค.", "ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค."};
        return shortNames[m];
    }

    private JPanel createMonthPanel(int monthIndex) {
        int year = YEAR_GREGORIAN;
        int month = monthIndex;

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1=Sunday

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(creamBg);
        panel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        // Month header
        JPanel monthHeader = new JPanel(new BorderLayout());
        monthHeader.setBackground(darkRed);
        monthHeader.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        JLabel monthLabel = new JLabel(
            THAI_MONTHS[monthIndex] + " " + YEAR_BE +
            "  |  " + CN_MONTHS[monthIndex] + "  (เดือนที่ " + (monthIndex + 1) + " ตามจันทรคติจีน)",
            SwingConstants.CENTER
        );
        monthLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        monthLabel.setForeground(goldColor);
        monthHeader.add(monthLabel, BorderLayout.CENTER);

        panel.add(monthHeader, BorderLayout.NORTH);

        // Calendar grid
        JPanel grid = new JPanel(new GridBagLayout());
        grid.setBackground(creamBg);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(1, 1, 1, 1);

        // Column headers: Sun | Mon | Tue | Wed | Thu | Fri | Sat | 日 Chinese
        String[] colHeaders = {"อา.", "จ.", "อ.", "พ.", "พฤ.", "ศ.", "ส.", "วันจีน"};
        for (int i = 0; i < 8; i++) {
            gbc.gridx = i;
            gbc.gridy = 0;
            JLabel hdr = new JLabel(colHeaders[i], SwingConstants.CENTER);
            hdr.setFont(new Font("Segoe UI", Font.BOLD, 13));
            hdr.setOpaque(true);
            hdr.setBackground(darkRed);
            hdr.setForeground(goldColor);
            hdr.setBorder(BorderFactory.createLineBorder(goldColor, 1));
            grid.add(hdr, gbc);
        }

        // Track row
        int row = 1;

        // Empty cells before first day (2 columns each: date + Chinese date)
        for (int d = 1; d < firstDayOfWeek; d++) {
            gbc.gridy = row;
            gbc.gridx = (d - 1) * 1;
            // We'll use 8 columns, so skip columns by adding placeholder
        }

        // Fill days
        for (int day = 1; day <= daysInMonth; day++) {
            String dateKey = String.format("%d-%02d-%02d", year, month + 1, day);

            boolean isFestival = FESTIVALS.containsKey(dateKey);
            boolean isSolarTerm = SOLAR_TERMS.containsKey(dateKey);
            boolean isSunday = getDayOfWeek(year, month + 1, day) == 1; // Sunday
            boolean isSaturday = getDayOfWeek(year, month + 1, day) == 7; // Saturday

            int col = getDayOfWeek(year, month + 1, day) - 1; // 0=Sun .. 6=Sat

            gbc.gridx = col;
            gbc.gridy = row;
            gbc.weightx = 1;

            // Gregorian date cell
            JPanel dayPanel = new JPanel(new BorderLayout(2, 2));
            dayPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 180, 150), 1));

            Color bg = Color.WHITE;
            if (isFestival || isSolarTerm) bg = festiveBg;
            else if (isSunday) bg = lightRed;
            else if (isSaturday) bg = lightBlue;
            dayPanel.setBackground(bg);

            // Day number
            JLabel dayNum = new JLabel(String.valueOf(day), SwingConstants.CENTER);
            dayNum.setFont(new Font("Segoe UI", Font.BOLD, 16));
            dayNum.setForeground(new Color(60, 10, 10));

            // Event text
            String eventText = "";
            if (isFestival) {
                eventText = FESTIVALS.get(dateKey);
            } else if (isSolarTerm) {
                eventText = SOLAR_TERMS.get(dateKey);
            }

            JPanel innerDay = new JPanel(new BorderLayout());
            innerDay.setOpaque(false);
            innerDay.add(dayNum, BorderLayout.NORTH);

            if (!eventText.isEmpty()) {
                JLabel eventLbl = new JLabel(eventText, SwingConstants.CENTER);
                eventLbl.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                eventLbl.setForeground(new Color(180, 40, 40));
                innerDay.add(eventLbl, BorderLayout.SOUTH);
            }

            dayPanel.add(innerDay, BorderLayout.CENTER);

            // Chinese date cell
            JPanel cnPanel = new JPanel(new BorderLayout());
            cnPanel.setBackground(new Color(255, 242, 225));
            cnPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 180, 150), 1));

            String cnDate = getChineseDate(year, month + 1, day);
            JLabel cnLbl = new JLabel(cnDate, SwingConstants.CENTER);
            cnLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            cnLbl.setForeground(chineseText);

            // Highlight important lunar days
            if (cnDate.equals("初一") || cnDate.equals("十五")) {
                cnLbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
                cnLbl.setForeground(new Color(200, 0, 0));
                cnPanel.setBackground(new Color(255, 225, 200));
            }

            cnPanel.add(cnLbl, BorderLayout.CENTER);

            // Combine both cells in a sub-panel
            JPanel cellPanel = new JPanel(new GridLayout(1, 2, 2, 0));
            cellPanel.setOpaque(false);
            cellPanel.add(dayPanel);
            cellPanel.add(cnPanel);

            gbc.gridwidth = 1;
            grid.add(cellPanel, gbc);

            // Move to next row if Saturday
            if (col == 6) {
                row++;
            }
        }

        // Fill remaining cells in last row
        int lastCol = getDayOfWeek(year, month + 1, daysInMonth) - 1;
        if (lastCol < 6) {
            for (int c = lastCol + 1; c <= 6; c++) {
                gbc.gridx = c;
                gbc.gridy = row;
                JPanel emptyDay = new JPanel();
                emptyDay.setOpaque(false);
                JPanel emptyCn = new JPanel();
                emptyCn.setOpaque(false);
                JPanel emptyCell = new JPanel(new GridLayout(1, 2, 2, 0));
                emptyCell.setOpaque(false);
                emptyCell.add(emptyDay);
                emptyCell.add(emptyCn);
                grid.add(emptyCell, gbc);
            }
        }

        JScrollPane scroll = new JScrollPane(grid);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(null);
        scroll.setBackground(creamBg);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(creamBg);
        wrapper.add(scroll, BorderLayout.CENTER);

        panel.add(wrapper, BorderLayout.CENTER);

        return panel;
    }

    private int getDayOfWeek(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day);
        return c.get(Calendar.DAY_OF_WEEK); // 1=Sun .. 7=Sat
    }

    /**
     * Get Chinese lunar day name for a given Gregorian date
     */
    private String getChineseDate(int year, int month, int day) {
        // Find which lunar month this date belongs to
        for (int i = 0; i < LUNAR_MONTHS.length; i++) {
            int lmMonth = LUNAR_MONTHS[i][0]; // 1-12
            int lmDay = LUNAR_MONTHS[i][1];
            int lmDays = LUNAR_MONTHS[i][2];

            Calendar start = Calendar.getInstance();
            start.set(year, lmMonth - 1, lmDay);

            Calendar end = Calendar.getInstance();
            end.set(year, lmMonth - 1, lmDay + lmDays - 1);

            Calendar target = Calendar.getInstance();
            target.set(year, month - 1, day);

            if (!target.before(start) && !target.after(end)) {
                long diff = (target.getTimeInMillis() - start.getTimeInMillis()) / (1000 * 60 * 60 * 24);
                int dayIndex = (int) diff;
                if (dayIndex >= 0 && dayIndex < 30) {
                    return CN_DAYS[dayIndex];
                }
                return String.valueOf(dayIndex + 1);
            }
        }

        // Dates before Chinese New Year (before Feb 6, 2027)
        Calendar cny = Calendar.getInstance();
        cny.set(2027, 1, 6);
        Calendar target = Calendar.getInstance();
        target.set(year, month - 1, day);

        if (target.before(cny)) {
            // These dates belong to lunar year 2026 (Bing Wu year)
            // Month 12 of 2026 lunar year starts around Jan 7, 2027
            Calendar prevLunarStart = Calendar.getInstance();
            prevLunarStart.set(2027, 0, 7); // Approx start of 腊月 (12th month lunar)
            if (!target.before(prevLunarStart)) {
                long diff = (target.getTimeInMillis() - prevLunarStart.getTimeInMillis()) / (1000 * 60 * 60 * 24);
                if (diff >= 0 && diff < 30) {
                    return CN_DAYS[(int) diff];
                }
            }
            return "腊月"; // 12th lunar month of previous year
        }

        // After Dec 27, 2027 (last lunar month start)
        Calendar lastLunarStart = Calendar.getInstance();
        lastLunarStart.set(2027, 11, 27);
        if (!target.before(lastLunarStart)) {
            long diff = (target.getTimeInMillis() - lastLunarStart.getTimeInMillis()) / (1000 * 60 * 60 * 24);
            if (diff >= 0 && diff < 29) {
                return CN_DAYS[(int) diff];
            }
            return "腊月";
        }

        return "-";
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new GridLayout(1, 5, 5, 0));
        footer.setBackground(darkRed);
        footer.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        addLegend(footer, "■ ตรุษจีน/เทศกาล", festiveBg);
        addLegend(footer, "■ อาทิตย์", lightRed);
        addLegend(footer, "■ เสาร์", lightBlue);
        addLegend(footer, "■ 初一/十五 (วันพระจีน)", new Color(255, 225, 200));
        addLegend(footer, "■ หน้าแรก = วันที่เกรกอเรียน | หลัง = วันจีน", Color.WHITE);

        return footer;
    }

    private void addLegend(JPanel panel, String text, Color color) {
        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 1));
        item.setOpaque(false);

        JLabel box = new JLabel("   ");
        box.setOpaque(true);
        box.setBackground(color);
        box.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        box.setPreferredSize(new Dimension(18, 14));

        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lbl.setForeground(Color.WHITE);

        item.add(box);
        item.add(lbl);
        panel.add(item);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Lab3().setVisible(true);
        });
    }
}