/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfaces.GameMethods;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author M
 */
public class Mole extends javax.swing.JFrame {

    String username;
    /**
     * Creates new form Mole
     */
    public Mole() {
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

        jLabel1 = new javax.swing.JLabel();
        mole1 = new javax.swing.JButton();
        mole2 = new javax.swing.JButton();
        mole3 = new javax.swing.JButton();
        mole4 = new javax.swing.JButton();
        mole5 = new javax.swing.JButton();
        mole6 = new javax.swing.JButton();
        mole7 = new javax.swing.JButton();
        mole8 = new javax.swing.JButton();
        mole9 = new javax.swing.JButton();
        btmExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 153, 0));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel1.setText("Whack a mole");

        mole1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/hole.png"))); // NOI18N

        mole2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/hole.png"))); // NOI18N

        mole3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/hole.png"))); // NOI18N

        mole4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/hole.png"))); // NOI18N

        mole5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/hole.png"))); // NOI18N

        mole6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/hole.png"))); // NOI18N

        mole7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/hole.png"))); // NOI18N

        mole8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/hole.png"))); // NOI18N

        mole9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/hole.png"))); // NOI18N

        btmExit.setText("Exit");
        btmExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(58, 58, 58))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btmExit)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mole1)
                            .addComponent(mole4)
                            .addComponent(mole7))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mole5)
                            .addComponent(mole2)
                            .addComponent(mole8))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mole3)
                            .addComponent(mole6)
                            .addComponent(mole9))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(mole1)
                                    .addComponent(mole2)
                                    .addComponent(mole3))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(mole5)
                                    .addComponent(mole6)))
                            .addComponent(mole4))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mole8)
                            .addComponent(mole9)))
                    .addComponent(mole7))
                .addGap(30, 30, 30)
                .addComponent(btmExit)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btmExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmExitActionPerformed
        System.setProperty("java.security.policy","file:\\C:\\Users\\M\\Documents\\NetBeansProjects\\ProyectoAlfa\\src\\client\\client.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        String name = "GameServer";

        Registry registry;
        try {
            registry = LocateRegistry.getRegistry("localhost"); // server's ip address
            GameMethods gameManager = (GameMethods) registry.lookup(name);
            gameManager.Exit(username);
            Login ventana = new Login();
            ventana.setVisible(true);
            super.dispose();
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ComputeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btmExitActionPerformed

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
            java.util.logging.Logger.getLogger(Mole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mole().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btmExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton mole1;
    private javax.swing.JButton mole2;
    private javax.swing.JButton mole3;
    private javax.swing.JButton mole4;
    private javax.swing.JButton mole5;
    private javax.swing.JButton mole6;
    private javax.swing.JButton mole7;
    private javax.swing.JButton mole8;
    private javax.swing.JButton mole9;
    // End of variables declaration//GEN-END:variables
}
