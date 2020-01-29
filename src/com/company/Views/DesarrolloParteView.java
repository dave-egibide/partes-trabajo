package com.company.Views;

import com.company.Controllers.HibernateController;
import com.company.Controllers.TareaController;
import com.company.Models.Maquina;
import com.company.Models.Parte;
import com.company.Models.Tarea;
import com.company.Models.Trabajador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DesarrolloParteView {
    private JLabel labelNombreTrab;
    private JPanel panelCard;
    private JButton tareaButton;
    private JButton mantenimientoButton;
    private JPanel panelBase;
    private JButton VOLVERButton;
    private JPanel tipoTareaCard;
    private JPanel seleccionTareaCard;
    private JPanel panelListaTareas;
    private JPanel seleccionMaquinaCard;
    private JPanel panelListaMaquinas;
    private JPanel seleccionTiempoCard;
    private JTextField tiempoTextField;
    private JButton minus10Button;
    private JButton minus30Button;
    private JButton plus10Button;
    private JButton plus30Button;
    private JButton confirmarTiempoButton;
    private JPanel resumenCard;
    private JLabel tipoTareaLabel;
    private JLabel nombreTareaLabel;
    private JLabel nombreMaquinaLabel;
    private JLabel tiempoLabel;
    private JLabel nombreTrabResLabel;
    private JButton confirmarParteButton;
    private Trabajador trabajador;
    private List<Tarea> tareas;
    private int numVentana = 0;

    private enum tipoTareaEnum {
        PROD,
        MANT
    }

    private Tarea tarea;
    private Maquina maquina;
    private tipoTareaEnum tipoTarea;
    private int tiempo;

    public DesarrolloParteView(Trabajador trabajador) {
        this.trabajador = trabajador;
        labelNombreTrab.setText(trabajador.getApellidos() + ", " + trabajador.getNombre());

        TerminalUsuario.getFrame().setContentPane(panelBase);
        TerminalUsuario.getFrame().pack();
        TerminalUsuario.getFrame().setLocationRelativeTo(null);
        TerminalUsuario.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);

        VOLVERButton.addActionListener(e -> {
            if (numVentana < 1) TerminalUsuario.iniciar();
            else {
                ((CardLayout) panelCard.getLayout()).previous(panelCard);
                numVentana--;
            }
        });
        tareaButton.addActionListener(e -> {
            tipoTarea = tipoTareaEnum.PROD;
            ((CardLayout) panelCard.getLayout()).show(panelCard, "seleccionTarea");
            cargarTareas();
            numVentana++;
        });
        mantenimientoButton.addActionListener(e -> {
            tipoTarea = tipoTareaEnum.MANT;
            ((CardLayout) panelCard.getLayout()).show(panelCard, "seleccionTarea");
            cargarTareas();
            numVentana++;
        });
        plus10Button.addActionListener(e -> cambiarTiempo(10));
        plus30Button.addActionListener(e -> cambiarTiempo(30));
        minus10Button.addActionListener(e -> cambiarTiempo(-10));
        minus30Button.addActionListener(e -> cambiarTiempo(-30));
        confirmarTiempoButton.addActionListener(e -> {
            if (tiempo > 0) {
                ((CardLayout) panelCard.getLayout()).show(panelCard, "resumen");
                cargarResumen();
                numVentana++;
            }
        });
        confirmarParteButton.addActionListener(e -> generarParte());
    }

    private void generarParte() {
        Parte parte = new Parte(tiempo, trabajador, tarea, maquina);
        boolean resultado = HibernateController.add(parte);
        new ResultadoParteView(resultado);
    }

    private void cargarResumen() {
        nombreTrabResLabel.setText(trabajador.getApellidos() + ", " + trabajador.getNombre());
        tipoTareaLabel.setText(tipoTarea.toString());
        nombreTareaLabel.setText(tarea.getNombre());
        if (maquina == null) nombreMaquinaLabel.setText("NINGUNA");
        else nombreMaquinaLabel.setText(maquina.getNombre());
        tiempoLabel.setText(String.valueOf(tiempo));
    }

    private void cambiarTiempo(int modifTiempo) {
        if (modifTiempo == 0) tiempo = 0;
        tiempo += modifTiempo;
        if (tiempo < 0) tiempo = 0;
        tiempoTextField.setText(String.valueOf(tiempo));
    }

    private void cargarTareas() {
        tareas = TareaController.getAll();
        tareas.removeIf(tarea -> !tarea.getTipo().equals(tipoTarea.toString()));
        tareas.removeIf(tarea -> tarea.getActivo() == 0);

        panelListaTareas.removeAll();
        panelListaTareas.setLayout(new java.awt.GridLayout(4, 3));
        for (int i = 0; i < tareas.size(); ++i) {
            Tarea tarea = tareas.get(i);
            JButton b = new JButton(tarea.getNombre());
            b.addActionListener(e -> seleccionarTarea(tarea));
            panelListaTareas.add(b);
        }
    }

    private void seleccionarTarea(Tarea tarea) {
        this.tarea = tarea;
        ((CardLayout) panelCard.getLayout()).show(panelCard, "seleccionMaquina");
        cargarMaquinas();
        numVentana++;
    }

    private void cargarMaquinas() {
        List<Maquina> maquinas = TareaController.getMaquinas(tarea);
        maquinas.removeIf(maquina -> maquina.getActivo() == 0);
        panelListaMaquinas.removeAll();
        panelListaMaquinas.setLayout(new java.awt.GridLayout(4, 3));

        JButton ningunaButton = new JButton("Ninguna");
        ningunaButton.addActionListener(e -> seleccionarMaquina(null));
        panelListaMaquinas.add(ningunaButton);

        for (int i = 0; i < maquinas.size(); ++i) {
            Maquina maquina = maquinas.get(i);
            JButton b = new JButton(maquina.getNombre());
            b.addActionListener(e -> seleccionarMaquina(maquina));
            panelListaMaquinas.add(b);
        }
    }

    private void seleccionarMaquina(Maquina maquina) {
        System.out.println(maquina);
        this.maquina = maquina;
        ((CardLayout) panelCard.getLayout()).show(panelCard, "seleccionTiempo");
        cambiarTiempo(0);
        numVentana++;
    }
}
