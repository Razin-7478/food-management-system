package FoodMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Secure login screen.
 * Default password: razin
 */
public class LoginFrame extends JFrame {

    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login * Food Management System");
        setSize(420, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(18, 32, 47));
        add(root);

        // Top accent
        JPanel topBar = new JPanel();
        topBar.setPreferredSize(new Dimension(0, 5));
        topBar.setBackground(new Color(0, 168, 150));
        root.add(topBar, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(40, 50, 30, 50));

        JLabel title = new JLabel("Sign In", SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        center.add(title);

        center.add(Box.createVerticalStrut(6));

        JLabel subtitle = new JLabel("Enter your password to continue", SwingConstants.CENTER);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(new Color(140, 160, 180));
        center.add(subtitle);

        center.add(Box.createVerticalStrut(32));

        JLabel passLabel = new JLabel("PASSWORD");
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        passLabel.setForeground(new Color(0, 168, 150));
        center.add(passLabel);

        center.add(Box.createVerticalStrut(8));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        passwordField.setMaximumSize(new Dimension(280, 38));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 70, 90), 1),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        passwordField.setBackground(new Color(28, 44, 62));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setEchoChar('*');
        center.add(passwordField);

        center.add(Box.createVerticalStrut(28));

        JButton loginBtn = createPrimaryButton("LOGIN");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.addActionListener(e -> attemptLogin());
        passwordField.addActionListener(e -> attemptLogin());
        center.add(loginBtn);

        root.add(center, BorderLayout.CENTER);
    }

    private void attemptLogin() {
        String entered = new String(passwordField.getPassword());
        if ("razin".equals(entered)) {
            new FoodFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Incorrect password. Please try again.",
                    "Authentication Failed",
                    JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
            passwordField.requestFocus();
        }
    }

    private JButton createPrimaryButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0, 168, 150));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 42));
        btn.setMaximumSize(new Dimension(280, 42));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(0, 190, 170));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(0, 168, 150));
            }
        });
        return btn;
    }
}
