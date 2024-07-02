
public class Purchase_Orders_Frame extends javax.swing.JFrame {

    private static int distributorID;
    public Purchase_Orders_Frame(int distributorID) {
        this.distributorID = distributorID;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_Back = new javax.swing.JButton();
        btn_Add_Purchase_Orders_ = new javax.swing.JButton();
        btn_View_Purchase_Orders_ = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_Back.setText("Back");
        btn_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BackActionPerformed(evt);
            }
        });

        btn_Add_Purchase_Orders_.setText("Add Purchase Orders");
        btn_Add_Purchase_Orders_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Add_Purchase_Orders_ActionPerformed(evt);
            }
        });

        btn_View_Purchase_Orders_.setText("View Purchase Orders");
        btn_View_Purchase_Orders_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_View_Purchase_Orders_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btn_Back))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_View_Purchase_Orders_)
                            .addComponent(btn_Add_Purchase_Orders_))))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(btn_Back)
                .addGap(46, 46, 46)
                .addComponent(btn_Add_Purchase_Orders_)
                .addGap(31, 31, 31)
                .addComponent(btn_View_Purchase_Orders_)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_Add_Purchase_Orders_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Add_Purchase_Orders_ActionPerformed
        Add_Purchase_Orders_Frame add_purchase_orders = new Add_Purchase_Orders_Frame(distributorID);
        add_purchase_orders.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_Add_Purchase_Orders_ActionPerformed

    private void btn_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BackActionPerformed
        Dashboard_Distributor_Frame dashboard_distributor = new Dashboard_Distributor_Frame(distributorID);
        dashboard_distributor.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_BackActionPerformed

    private void btn_View_Purchase_Orders_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_View_Purchase_Orders_ActionPerformed
        View_Purchase_Orders_Frame view_prchase_orders = new View_Purchase_Orders_Frame();
        view_prchase_orders.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_View_Purchase_Orders_ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Purchase_Orders_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Purchase_Orders_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Purchase_Orders_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Purchase_Orders_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Purchase_Orders_Frame(distributorID).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Add_Purchase_Orders_;
    private javax.swing.JButton btn_Back;
    private javax.swing.JButton btn_View_Purchase_Orders_;
    // End of variables declaration//GEN-END:variables
}