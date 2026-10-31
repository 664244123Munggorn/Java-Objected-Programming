import java.awt.Font;
import java.awt.Component;
import java.awt.Container;
import javax.swing.UIManager;

public class ThaiFontSupport {

    private static final Font THAI_FONT =
            new Font("Tahoma", Font.PLAIN, 14);

    public static void apply() {
        String[] components = {
            "Button.font",
            "Label.font",
            "TextField.font",
            "TextArea.font",
            "ComboBox.font",
            "Table.font",
            "TableHeader.font",
            "TabbedPane.font",
            "OptionPane.font",
            "Menu.font",
            "MenuItem.font"
        };

        for (String component : components) {
            UIManager.put(component, THAI_FONT);
        }
    }

    public static void applyTo(Component component) {
        component.setFont(THAI_FONT);

        if (component instanceof Container) {
            for (Component child :
                    ((Container) component).getComponents()) {
                applyTo(child);
            }
        }
    }
}
