

package dao;

import model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.DBConnection;

public class BookingDAO {

    // Place Order
    public boolean placeOrder(Order order) {

        String sql = "INSERT INTO orders (user_email, items, total_price, order_date, paid, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, order.getUserEmail());
            ps.setString(2, order.getItems());
            ps.setInt(3, order.getTotalPrice());
            ps.setTimestamp(4, new Timestamp(order.getOrderDate().getTime()));
            ps.setBoolean(5, order.isPaid());
            ps.setString(6, order.getStatus());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get Orders For Logged In User
    public List<Order> getOrdersForUser(String userEmail) {

        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM orders WHERE user_email=? ORDER BY order_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userEmail);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getString("user_email"),
                        rs.getString("items"),
                        rs.getInt("total_price"),
                        rs.getTimestamp("order_date"),
                        rs.getBoolean("paid"),
                        rs.getString("status")
                );

                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    // Admin View All Orders
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM orders ORDER BY order_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getString("user_email"),
                        rs.getString("items"),
                        rs.getInt("total_price"),
                        rs.getTimestamp("order_date"),
                        rs.getBoolean("paid"),
                        rs.getString("status")
                );

                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    // Update Order Status (Admin)
    public boolean updateOrderStatus(int orderId, String status) {

        String sql = "UPDATE orders SET status=? WHERE order_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Mark Order as Paid
    public boolean markOrderPaid(int orderId) {

        String sql = "UPDATE orders SET paid=1 WHERE order_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}