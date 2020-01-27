package com.company.Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EleccionTrabajadorView {
    private JPanel panelFila1;
    private JPanel panelFila2;
    private JPanel panelFila3;
    private JPanel panelFila4;
    private JButton aButton;
    private JButton bButton;
    private JButton cButton;
    private JButton dButton;
    private JButton eButton;
    private JButton fButton;
    private JButton gButton;
    private JButton hButton;
    private JButton iButton;
    private JButton jButton;
    private JButton kButton;
    private JButton lButton;
    private JButton mButton;
    private JButton nButton;
    private JButton ñButton;
    private JButton oButton;
    private JButton pButton;
    private JButton qButton;
    private JButton rButton;
    private JButton sButton;
    private JButton tButton;
    private JButton uButton;
    private JButton vButton;
    private JButton wButton;
    private JButton xButton;
    private JButton yButton;
    private JButton zButton;
    private JButton VOLVERButton;
    private JPanel panelBaseAlfabeto;

    public EleccionTrabajadorView() {
        VOLVERButton.addActionListener(e -> {
            TerminalUsuario.iniciar();
        });
    }

    public JPanel getPanelBaseAlfabeto() {
        return panelBaseAlfabeto;
    }
}
