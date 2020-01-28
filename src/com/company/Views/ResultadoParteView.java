package com.company.Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultadoParteView {
    private JPanel panelBase;
    private JLabel textoSuperiorLabel;
    private JLabel textoInferiorLabel;
    private JButton volverButton;

    public ResultadoParteView(boolean resultado) {
        TerminalUsuario.getFrame().setContentPane(panelBase);
        TerminalUsuario.getFrame().pack();
        TerminalUsuario.getFrame().setLocationRelativeTo(null);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        if (resultado) {
            textoSuperiorLabel.setText("Parte generado correctamente.");
            textoInferiorLabel.setText("Muchas gracias.");
        } else {
            textoSuperiorLabel.setText("Error generando parte.");
            textoInferiorLabel.setText("Por favor vuelva a intentarlo o contacte a su administrador.");
        }

        volverButton.addActionListener(e -> TerminalUsuario.iniciar());
    }
}
