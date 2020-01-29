package com.company.Views.AdminViews;

import javax.swing.*;
import java.awt.*;

/**
 * @author Dave
 */
public class VentanaAcercaDe extends JFrame {

    public VentanaAcercaDe() {
        initComponents();
    }

    public void load() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {

        JLabel pollo = new JLabel();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Acerca de...");

        pollo.setIcon(new ImageIcon("resources/icon/pollo.gif")); // NOI18N
        pollo.setText("jLabel1");

        jLabel1.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("PARTES TRABAJO");

        jLabel2.setText("<html>Realizado por Dave Roig<br><br>Todos los gráficos libres de derechos.");

        jLabel4.setText("Con urgencia");

        jLabel5.setText("Finalizado el 1/29/2020");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(pollo, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pollo, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jLabel1)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addContainerGap(113, Short.MAX_VALUE))
        );
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/icon/mainLogo.png"));

        pack();
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAcercaDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAcercaDe().setVisible(true);
            }
        });
    }

}
