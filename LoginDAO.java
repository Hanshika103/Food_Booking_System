package dao;

import java.sql.*;
import database.DBConnection;
import model.Registration;

public class LoginDAO {

    // Validate login against registration table
    public boolean validate(Registration user) {
        String sql = "SELECT * FROM registration WHERE email=? AND password=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());

            ResultSet rs = ps.executeQuery();
            return rs.next(); // returns true if user found

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}