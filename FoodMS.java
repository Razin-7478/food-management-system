package FoodMS;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Entry point of the Food Management System.
 */
public class FoodMS {

    public static void main(String[] args) {
        // Use system look-and-feel where possible, then launch on EDT
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // fallback to default
        }

        SwingUtilities.invokeLater(() -> {
            new WelcomeFrame().setVisible(true);
        });
    }
}
