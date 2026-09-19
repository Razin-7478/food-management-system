package FoodMS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

/**
 * Main inventory management window.
 * Supports Add, Update, Delete, View and file persistence.
 */
public class FoodFrame extends JFrame {

    private final ArrayList<Item> items = new ArrayList<>();
    private JTextField tfId, tfName, tfPrice, tfQty;
    private JTable table;
    private DefaultTableModel model;
    private final String DATA_FILE = "foods.txt";

    // Color palette
    private static final Color NAVY       = new Color(18, 32, 47);
    private static final Color CARD_BG    = new Color(28, 44, 62);
    private static final Color TEAL       = new Color(0, 168, 150);
    private static final Color TEAL_HOVER = new Color(0, 190, 170);
    private static final Color TEXT_LIGHT = new Color(220, 230, 240);
    private static final Color TEXT_MUTED = new Color(140, 160, 180);
    private static final Color DANGER     = new Color(220, 70, 70);
    private static final Color WARNING    = new Color(230, 150, 40);

    public FoodFrame() {
        setTitle("Food Management System");
        setSize(980, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900, 520));

        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(NAVY);
        add(root);

        // ===== HEADER =====
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(NAVY);
        header.setBorder(new EmptyBorder(16, 24, 12, 24));

        JLabel title = new JLabel("Food Inventory");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.WEST);

        JLabel badge = new JLabel("  MANAGEMENT  ");
        badge.setFont(new Font("Segoe UI", Font.BOLD, 11));
        badge.setForeground(Color.WHITE);
        badge.setBackground(TEAL);
        badge.setOpaque(true);
        badge.setBorder(new EmptyBorder(4, 8, 4, 8));
        header.add(badge, BorderLayout.EAST);

        root.add(header, BorderLayout.NORTH);

        // ===== MAIN CONTENT =====
        JPanel content = new JPanel(new BorderLayout(16, 0));
        content.setBackground(NAVY);
        content.setBorder(new EmptyBorder(0, 20, 20, 20));
        root.add(content, BorderLayout.CENTER);

        // Left form card
        content.add(buildFormCard(), BorderLayout.WEST);

        // Right table card
        content.add(buildTableCard(), BorderLayout.CENTER);

        // Load data
        loadFromFile();
        refreshTable();
    }

    // ===================== FORM CARD =====================
    private JPanel buildFormCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(40, 60, 80), 1),
                new EmptyBorder(20, 20, 20, 20)
        ));
        card.setPreferredSize(new Dimension(300, 0));

        JLabel formTitle = new JLabel("Item Details");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        formTitle.setForeground(TEXT_LIGHT);
        formTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(formTitle);
        card.add(Box.createVerticalStrut(18));

        tfId    = createField(card, "ITEM ID");
        tfName  = createField(card, "NAME");
        tfPrice = createField(card, "PRICE");
        tfQty   = createField(card, "QUANTITY");

        card.add(Box.createVerticalStrut(16));

        // Buttons
        JButton btnAdd = createButton("ADD ITEM", TEAL, TEAL_HOVER);
        btnAdd.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAdd.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnAdd.addActionListener(e -> addItem());
        card.add(btnAdd);
        card.add(Box.createVerticalStrut(10));

        JButton btnUpdate = createButton("UPDATE", new Color(40, 100, 180), new Color(50, 120, 210));
        btnUpdate.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnUpdate.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnUpdate.addActionListener(e -> updateItem());
        card.add(btnUpdate);
        card.add(Box.createVerticalStrut(10));

        JButton btnDelete = createButton("DELETE", DANGER, new Color(240, 90, 90));
        btnDelete.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnDelete.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnDelete.addActionListener(e -> deleteItem());
        card.add(btnDelete);
        card.add(Box.createVerticalStrut(10));

        JButton btnClear = createButton("CLEAR FIELDS", new Color(60, 80, 100), new Color(80, 100, 120));
        btnClear.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnClear.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        btnClear.addActionListener(e -> clearFields());
        card.add(btnClear);

        card.add(Box.createVerticalGlue());

        JButton btnExit = createButton("EXIT", new Color(50, 60, 75), new Color(70, 80, 95));
        btnExit.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnExit.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        btnExit.addActionListener(e -> System.exit(0));
        card.add(btnExit);

        return card;
    }

    private JTextField createField(JPanel parent, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 11));
        label.setForeground(TEAL);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        parent.add(label);
        parent.add(Box.createVerticalStrut(4));

        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(new Color(18, 32, 47));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 70, 90), 1),
                new EmptyBorder(7, 10, 7, 10)
        ));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        parent.add(field);
        parent.add(Box.createVerticalStrut(12));
        return field;
    }

    // ===================== TABLE CARD =====================
    private JPanel buildTableCard() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(40, 60, 80), 1),
                new EmptyBorder(12, 12, 12, 12)
        ));

        String[] columns = {"ID", "NAME", "PRICE", "QTY", "TOTAL VALUE"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(32);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setForeground(TEXT_LIGHT);
        table.setBackground(CARD_BG);
        table.setSelectionBackground(new Color(0, 100, 95));
        table.setSelectionForeground(Color.WHITE);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFocusable(false);

        // Header style
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(new Color(18, 32, 47));
        header.setForeground(TEAL);
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(0, 36));

        // Center alignment for numeric columns
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        center.setForeground(TEXT_LIGHT);
        center.setBackground(CARD_BG);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        // Click row -> fill form
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    tfId.setText(model.getValueAt(row, 0).toString());
                    tfName.setText(model.getValueAt(row, 1).toString());
                    tfPrice.setText(model.getValueAt(row, 2).toString());
                    tfQty.setText(model.getValueAt(row, 3).toString());
                }
            }
        });

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(CARD_BG);
        scroll.setBackground(CARD_BG);
        card.add(scroll, BorderLayout.CENTER);

        return card;
    }

    // ===================== BUTTON FACTORY =====================
    private JButton createButton(String text, Color bg, Color hover) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(bg);
            }
        });
        return btn;
    }

    // ===================== CRUD OPERATIONS =====================
    private void addItem() {
        try {
            String id = tfId.getText().trim();
            String name = tfName.getText().trim();
            double price = Double.parseDouble(tfPrice.getText().trim());
            int qty = Integer.parseInt(tfQty.getText().trim());

            if (id.isEmpty() || name.isEmpty()) {
                showError("Please fill in all fields.");
                return;
            }
            if (price < 0 || qty < 0) {
                showError("Price and quantity cannot be negative.");
                return;
            }

            items.add(new Food(id, name, price, qty));
            refreshTable();
            saveToFile();
            clearFields();
            showInfo("Item added successfully.");
        } catch (NumberFormatException ex) {
            showError("Invalid number format. Please check price and quantity.");
        }
    }

    private void updateItem() {
        int row = table.getSelectedRow();
        if (row < 0) {
            showError("Please select a row first.");
            return;
        }

        try {
            String id = tfId.getText().trim();
            String name = tfName.getText().trim();
            double price = Double.parseDouble(tfPrice.getText().trim());
            int qty = Integer.parseInt(tfQty.getText().trim());

            Item it = items.get(row);
            it.setId(id);
            it.setName(name);

            Food food = (Food) it;
            food.setPrice(price);
            food.setQuantity(qty);

            refreshTable();
            saveToFile();
            clearFields();
            showInfo("Item updated successfully.");
        } catch (NumberFormatException ex) {
            showError("Invalid data. Please check your inputs.");
        }
    }

    private void deleteItem() {
        int row = table.getSelectedRow();
        if (row < 0) {
            showError("Please select a row first.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this item?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            items.remove(row);
            refreshTable();
            saveToFile();
            clearFields();
            showInfo("Item deleted.");
        }
    }

    // ===================== TABLE & FILE =====================
    private void refreshTable() {
        model.setRowCount(0);
        for (Item it : items) {
            Food f = (Food) it;
            model.addRow(new Object[]{
                    f.getId(),
                    f.getName(),
                    String.format("%.1f", f.getPrice()),
                    f.getQuantity(),
                    String.format("%.1f", it.totalValue())   // polymorphic call
            });
        }
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Item it : items) {
                Food f = (Food) it;
                pw.println(f.getId() + "," + f.getName() + "," +
                           f.getPrice() + "," + f.getQuantity());
            }
        } catch (IOException e) {
            showError("Could not save data to file.");
        }
    }

    private void loadFromFile() {
        items.clear();
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] p = line.split(",");
                if (p.length == 4) {
                    items.add(new Food(
                            p[0].trim(),
                            p[1].trim(),
                            Double.parseDouble(p[2].trim()),
                            Integer.parseInt(p[3].trim())
                    ));
                }
            }
        } catch (Exception e) {
            showError("Could not load data from file.");
        }
    }

    private void clearFields() {
        tfId.setText("");
        tfName.setText("");
        tfPrice.setText("");
        tfQty.setText("");
        table.clearSelection();
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
