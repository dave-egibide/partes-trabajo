package com.company.Views;

import com.company.Controllers.HibernateController;

import javax.swing.*;


public class TerminalUsuario {

    private JPanel panelBase;
    private JPanel panelBotonInicio;
    private JButton buttonInicio;
    private static JFrame frame;

    public TerminalUsuario() {
        buttonInicio.addActionListener(e -> {
            EleccionTrabajadorView eleccionTrabajadorView = new EleccionTrabajadorView();
            frame.setContentPane(eleccionTrabajadorView.getPanelBaseAlfabeto());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        });
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        frame = new JFrame("TerminalUsuario");
        frame.setContentPane(new TerminalUsuario().panelBase);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setTitle("Partes Trabajo - David Roig");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setVisible(true);
        boolean conectado = HibernateController.conectar();
    }

    public static void iniciar () {
        frame.setContentPane(new TerminalUsuario().panelBase);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
