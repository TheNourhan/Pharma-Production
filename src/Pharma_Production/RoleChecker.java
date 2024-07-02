package Pharma_Production;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleChecker {

    // Method to check if the user with given ID is a manager
    public static boolean isManager(int id) {
        String sql = "SELECT * FROM Employees WHERE EmployeeID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("Role");
                return "Manager".equalsIgnoreCase(role); // Assuming role is stored as lowercase "manager"
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(conn, pstmt, rs);
        }

        return false;
    }

    // Method to check if the user with given ID is a distributor
    public static boolean isDistributor(int id) {
        String sql = "SELECT * FROM Distributors WHERE DistributorID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true; // If a distributor with matching ID is found
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(conn, pstmt, rs);
        }

        return false;
    }
}
