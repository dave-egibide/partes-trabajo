package com.company.Views;

import com.company.Models.Trabajador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DesarrolloParteView {
    private JLabel labelNombreTrab;
    private JPanel panelCard;
    private JButton tareaButton;
    private JButton mantenimientoButton;
    private JPanel panelBase;
    private JButton VOLVERButton;
    private JPanel tipoTareaCard;
    private JPanel seleccionTareaCard;
    private Trabajador trabajador;
    private boolean enPrimeraPantalla = true;

    private boolean mantenimiento;

    public DesarrolloParteView(Trabajador trabajador) {
        this.trabajador = trabajador;
        labelNombreTrab.setText(trabajador.getApellidos() + ", " + trabajador.getNombre());

        TerminalUsuario.getFrame().setContentPane(panelBase);
        TerminalUsuario.getFrame().pack();
        TerminalUsuario.getFrame().setLocationRelativeTo(null);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        VOLVERButton.addActionListener(e -> {
            if (enPrimeraPantalla) TerminalUsuario.iniciar();
            else ((CardLayout) panelCard.getLayout()).previous(panelCard);
        });
        tareaButton.addActionListener(e -> {
            mantenimiento = false;
            ((CardLayout) panelCard.getLayout()).show(panelCard, "seleccionTarea");
            cargarTareas();
        });
        mantenimientoButton.addActionListener(e -> {
            mantenimiento = true;
            ((CardLayout) panelCard.getLayout()).show(panelCard, "seleccionTarea");
            cargarTareas();
        });
    }

    private void cargarTareas() {

    }
}
