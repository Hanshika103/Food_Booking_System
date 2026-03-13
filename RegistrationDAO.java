package dao;

import model.Registration;
import java.sql.*;
import database.DBConnection;

public class RegistrationDAO {

    // Add new registration
    public boolean addRegistration(Registration reg) {
        String sql = "INSERT INTO registration (name, email, password) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, reg.getName());
            ps.setString(2, reg.getEmail());
            ps.setString(3, reg.getPassword());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get registration by email
    public Registration getRegistrationByEmail(String email) {
        String sql = "SELECT * FROM registration WHERE email=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Registration(
                        rs.getInt("reg_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
