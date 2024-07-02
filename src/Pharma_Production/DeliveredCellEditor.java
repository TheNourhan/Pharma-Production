package Pharma_Production;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import java.time.LocalDate;

public class DeliveredCellEditor extends AbstractCellEditor implements TableCellEditor {

    private JCheckBox checkBox;
    private Object originalValue;

    public DeliveredCellEditor() {
        checkBox = new JCheckBox();
        checkBox.addActionListener((e) -> {
            // No action needed here, as the actual update happens in stopCellEditing()
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        originalValue = table.getValueAt(row, 0); // Assuming OrderID is in the first column (adjust if necessary)
        checkBox.setSelected((boolean) value);

        int orderId = (int) originalValue;

        try {
            int deliveredValue = getDeliveredValueFromDatabase(orderId);

            if (deliveredValue == 1) {
                checkBox.setEnabled(false); // Disable checkbox if already delivered
            } else {
                checkBox.setEnabled(true); // Enable checkbox if not delivered
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return checkBox;
    }

    @Override
    public Object getCellEditorValue() {
        return checkBox.isSelected();
    }

    @Override
    public boolean stopCellEditing() {
        if (checkBox.isSelected()) {
            if (!confirmDelivery()) {
                return false; // User canceled delivery confirmation
            }
        }
        checkBox.setEnabled(false); // Disable checkbox after confirming delivery

        // Perform database update
        updateDeliveredValueInDatabase((int) originalValue, checkBox.isSelected() ? 1 : 0);

        return super.stopCellEditing();
    }

    private boolean confirmDelivery() {
        int option = JOptionPane.showConfirmDialog(checkBox.getParent(),
                "Mark this order as delivered?", "Confirm Delivery", JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }

    private int getDeliveredValueFromDatabase(int orderId) throws SQLException {
        int deliveredValue = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT Delivered FROM PurchaseOrders WHERE OrderID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                deliveredValue = rs.getInt("Delivered");
            }
        } finally {
            DatabaseUtil.close(conn, pstmt, rs);
        }

        return deliveredValue;
    }

    private void updateDeliveredValueInDatabase(int orderId, int deliveredValue) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            conn.setAutoCommit(false);

            String sqlUpdate = "UPDATE PurchaseOrders SET Delivered = ? WHERE OrderID = ?";
            pstmt = conn.prepareStatement(sqlUpdate);
            pstmt.setInt(1, deliveredValue);
            pstmt.setInt(2, orderId);
            int rowsUpdated = pstmt.executeUpdate();
            pstmt.close();

            if (deliveredValue == 1 && rowsUpdated > 0) {
                double amount = calculateAmount(orderId);

                String sqlInsert = "INSERT INTO Invoices (OrderID, InvoiceDate, Amount) VALUES (?, ?, ?)";
                pstmt = conn.prepareStatement(sqlInsert);
                pstmt.setInt(1, orderId);
                pstmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
                pstmt.setDouble(3, amount);
                pstmt.executeUpdate();
                pstmt.close();
            }

            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            DatabaseUtil.close(conn, pstmt);
        }
    }

    private double calculateAmount(int orderId) throws SQLException {
        double amount = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();

            String sqlRetrieve = "SELECT Quantity, MedicineID FROM PurchaseOrders WHERE OrderID = ?";
            pstmt = conn.prepareStatement(sqlRetrieve);
            pstmt.setInt(1, orderId);
            rs = pstmt.executeQuery();
            int quantity = 0;
            int medicineID = 0;
            if (rs.next()) {
                quantity = rs.getInt("Quantity");
                medicineID = rs.getInt("MedicineID");
            }
            rs.close();
            pstmt.close();

            double unitPrice = getUnitPriceFromMedicines(medicineID);
            amount = unitPrice * quantity;

        } finally {
            DatabaseUtil.close(conn, pstmt, rs);
        }

        return amount;
    }

    private double getUnitPriceFromMedicines(int medicineID) throws SQLException {
        double unitPrice = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT Price FROM Medicines WHERE MedicineID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, medicineID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                unitPrice = rs.getDouble("Price");
            }
        } finally {
            DatabaseUtil.close(conn, pstmt, rs);
        }

        return unitPrice;
    }
}
