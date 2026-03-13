

package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class AdminLoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;

    public AdminLoginFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 260);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245)); // light gray
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // Title
        JLabel title = new JLabel("Admin Login", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(156, 39, 176)); // purple
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);
        gbc.gridwidth = 1; // reset

        // Username Label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(userLabel, gbc);

        // Username Field
        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setBorder(new LineBorder(Color.GRAY, 1, true));
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        // Password Label
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passLabel, gbc);

        // Password Field
        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(new LineBorder(Color.GRAY, 1, true));
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Login Button
        loginButton = createStyledButton("Login", new Color(156, 39, 176));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);
        loginButton.addActionListener(e -> adminLogin());

        // Back Button
        backButton = createStyledButton("Back to Menu", new Color(33, 150, 243));
        gbc.gridy = 4;
        panel.add(backButton, gbc);
        backButton.addActionListener(e -> goBack());

        add(panel);
        setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private void adminLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if ("admin".equals(username) && "admin123".equals(password)) {
            dispose();
            new AdminOrdersFrame(); // open admin panel
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials");
        }
    }

    private void goBack() {
        dispose();
        new MenuFrame("guest@example.com"); // or last logged-in user
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminLoginFrame::new);
    }
}