package FoodMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Professional welcome screen.
 */
public class WelcomeFrame extends JFrame {

    public WelcomeFrame() {
        setTitle("Food Management System");
        setSize(560, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(false);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(18, 32, 47)); // deep navy
        add(root);

        // Top accent bar
        JPanel topBar = new JPanel();
        topBar.setPreferredSize(new Dimension(0, 6));
        topBar.setBackground(new Color(0, 168, 150)); // teal accent
        root.add(topBar, BorderLayout.NORTH);

        // Center content
        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(50, 40, 40, 40));

        JLabel subtitle = new JLabel("WELCOME TO", SwingConstants.CENTER);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(160, 180, 200));
        center.add(subtitle);

        center.add(Box.createVerticalStrut(12));

        JLabel title = new JLabel("Food Management System", SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        center.add(title);

        center.add(Box.createVerticalStrut(8));

        JLabel tagline = new JLabel("Simple * Smart * Reliable Inventory", SwingConstants.CENTER);
        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);
        tagline.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tagline.setForeground(new Color(0, 168, 150));
        center.add(tagline);

        center.add(Box.createVerticalStrut(36));

        JButton nextBtn = createPrimaryButton("GET STARTED  ->");
        nextBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextBtn.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
        center.add(nextBtn);

        center.add(Box.createVerticalStrut(28));

        JLabel author = new JLabel("by Mohammad Razin Masud", SwingConstants.CENTER);
        author.setAlignmentX(Component.CENTER_ALIGNMENT);
        author.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        author.setForeground(new Color(120, 140, 160));
        center.add(author);

        root.add(center, BorderLayout.CENTER);

        // Bottom bar
        JPanel bottomBar = new JPanel();
        bottomBar.setPreferredSize(new Dimension(0, 4));
        bottomBar.setBackground(new Color(0, 168, 150));
        root.add(bottomBar, BorderLayout.SOUTH);
    }

    private JButton createPrimaryButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0, 168, 150));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 44));
        btn.setMaximumSize(new Dimension(220, 44));

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
