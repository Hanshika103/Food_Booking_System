

    package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import dao.BookingDAO;
import model.Order;

public class PaymentFrame extends JFrame {

    private JComboBox<String> orderCombo;
    private JComboBox<String> paymentMethodCombo;
    private JButton payButton, backButton;
    private String userEmail;
    private List<Order> userOrders;

    public PaymentFrame(String userEmail) {
        this.userEmail = userEmail;
        initUI();
    }

    private void initUI() {
        setTitle("Payment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // Title
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        JLabel title = new JLabel("Select Order to Pay", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(33, 150, 243));
        panel.add(title, gbc);

        // Load user orders
        BookingDAO dao = new BookingDAO();
        userOrders = dao.getOrdersForUser(userEmail);

        String[] orderList = userOrders.stream()
                .map(o -> "Order #" + o.getOrderId() + " - Total: ₹" + o.getTotalPrice())
                .toArray(String[]::new);

        orderCombo = new JComboBox<>(orderList);
        orderCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 1; gbc.gridwidth = 2;
        panel.add(orderCombo, gbc);

        // Payment method
        gbc.gridwidth = 1; gbc.gridy = 2; gbc.gridx = 0;
        JLabel methodLabel = new JLabel("Payment Method:");
        methodLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(methodLabel, gbc);

        paymentMethodCombo = new JComboBox<>(new String[]{"UPI", "Cash", "Card"});
        paymentMethodCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        panel.add(paymentMethodCombo, gbc);

        // Buttons
        payButton = createStyledButton("Pay Now", new Color(33, 150, 243));
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(payButton, gbc);

        backButton = createStyledButton("Back to Menu", new Color(244, 67, 54));
        gbc.gridx = 1;
        panel.add(backButton, gbc);

        add(panel);

        payButton.addActionListener(e -> makePayment());
        backButton.addActionListener(e -> goBack());

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

    private void makePayment() {
        int selectedIndex = orderCombo.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "No order selected.");
            return;
        }

        String paymentMethod = (String) paymentMethodCombo.getSelectedItem();
        Order order = userOrders.get(selectedIndex);
        BookingDAO dao = new BookingDAO();

        if (dao.markOrderPaid(order.getOrderId())) {
            JOptionPane.showMessageDialog(this, "Payment successful for Order #" + order.getOrderId()
                    + " via " + paymentMethod);
        } else {
            JOptionPane.showMessageDialog(this, "Payment failed.");
        }
    }

    private void goBack() {
        dispose();
        new MenuFrame(userEmail);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentFrame("test@example.com"));
    }
}
