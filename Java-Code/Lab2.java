import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lab2 extends JFrame implements ActionListener {
    private JTextField display;
    private String currentInput = "";
    private String operator = "";
    private double firstValue = 0;
    private boolean startNewNumber = true;

    public Lab2() {
        super("Cute Pink Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 520);
        setLocationRelativeTo(null);
        setResizable(false);

        CatPanel mainPanel = new CatPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Segoe UI", Font.BOLD, 28));
        display.setEditable(false);
        display.setOpaque(true);
        display.setBackground(new Color(255, 240, 245));
        display.setForeground(new Color(95, 38, 70));
        display.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(247, 143, 179), 2),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        mainPanel.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 8, 8));
        buttonPanel.setOpaque(false);

        String[] labels = {"C", "/", "*", "⌫", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "=", "0", ".", "%", "±"};

        for (String label : labels) {
            JButton button = new JButton(label);
            button.setFocusPainted(false);
            button.setFont(new Font("Segoe UI", Font.BOLD, 18));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(255, 170, 196));
            button.setBorder(BorderFactory.createLineBorder(new Color(255, 130, 173), 2));
            button.setOpaque(true);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        setContentPane(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();

        if (text.matches("[0-9]")) {
            appendDigit(text);
            return;
        }

        switch (text) {
            case ".":
                appendDigit(".");
                break;
            case "C":
                clearAll();
                break;
            case "⌫":
                backspace();
                break;
            case "%":
                percent();
                break;
            case "±":
                toggleSign();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                chooseOperator(text);
                break;
            case "=":
                calculate();
                break;
        }
    }

    private void appendDigit(String digit) {
        if (startNewNumber) {
            currentInput = "";
            startNewNumber = false;
        }
        if (digit.equals(".") && currentInput.contains(".")) {
            return;
        }
        if (currentInput.isEmpty()) {
            currentInput = digit.equals(".") ? "0." : digit;
        } else {
            currentInput += digit;
        }
        display.setText(currentInput);
    }

    private void chooseOperator(String op) {
        if (!operator.isEmpty() && !startNewNumber) {
            calculate();
        }
        if (!currentInput.isEmpty()) {
            firstValue = Double.parseDouble(currentInput);
        }
        operator = op;
        startNewNumber = true;
        display.setText("0");
    }

    private void calculate() {
        if (operator.isEmpty()) {
            return;
        }

        double secondValue = currentInput.isEmpty() ? firstValue : Double.parseDouble(currentInput);

        switch (operator) {
            case "+":
                firstValue += secondValue;
                break;
            case "-":
                firstValue -= secondValue;
                break;
            case "*":
                firstValue *= secondValue;
                break;
            case "/":
                if (secondValue == 0) {
                    display.setText("Error");
                    currentInput = "";
                    operator = "";
                    startNewNumber = true;
                    return;
                }
                firstValue /= secondValue;
                break;
        }

        currentInput = formatResult(firstValue);
        display.setText(currentInput);
        startNewNumber = true;
        operator = "";
    }

    private void clearAll() {
        display.setText("0");
        currentInput = "";
        operator = "";
        firstValue = 0;
        startNewNumber = true;
    }

    private void backspace() {
        if (currentInput.length() > 1) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            display.setText(currentInput);
        } else {
            currentInput = "";
            display.setText("0");
        }
    }

    private void percent() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput) / 100.0;
            currentInput = formatResult(value);
            display.setText(currentInput);
        }
    }

    private void toggleSign() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput) * -1;
            currentInput = formatResult(value);
            display.setText(currentInput);
        }
    }

    private String formatResult(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value);
        }
        return String.format("%.8f", value).replaceAll("0*$", "").replaceAll("\\.$", "");
    }

    static class CatPanel extends JPanel {
        CatPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            GradientPaint bg = new GradientPaint(0, 0, new Color(255, 240, 245), 0, getHeight(), new Color(255, 191, 214));
            g2.setPaint(bg);
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(new Color(255, 170, 196));
            g2.fillOval(70, 220, 170, 140);
            g2.fillOval(190, 220, 170, 140);
            g2.fillOval(120, 180, 150, 120);

            g2.setColor(new Color(255, 130, 173));
            g2.fillPolygon(new int[]{120, 155, 95}, new int[]{205, 160, 180}, 3);
            g2.fillPolygon(new int[]{250, 215, 285}, new int[]{205, 160, 180}, 3);

            g2.setColor(new Color(255, 240, 245));
            g2.fillOval(150, 250, 25, 25);
            g2.fillOval(215, 250, 25, 25);
            g2.setColor(Color.BLACK);
            g2.fillOval(155, 255, 10, 10);
            g2.fillOval(220, 255, 10, 10);

            g2.setColor(new Color(120, 70, 90));
            g2.drawArc(170, 285, 40, 20, 0, -180);
            g2.drawLine(160, 280, 135, 290);
            g2.drawLine(235, 280, 260, 290);
            g2.drawLine(165, 295, 145, 325);
            g2.drawLine(225, 295, 245, 325);

            g2.setColor(new Color(255, 130, 173));
            g2.fillOval(110, 330, 20, 20);
            g2.fillOval(240, 330, 20, 20);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Lab2 calculator = new Lab2();
            calculator.setVisible(true);
        });
    }
}
