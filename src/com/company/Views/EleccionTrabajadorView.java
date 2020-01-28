package com.company.Views;

import com.company.Controllers.TrabajadorController;
import com.company.Models.Trabajador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
    private JButton gnButton;
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
    private JPanel panelAlfabeto;
    private JPanel panelTrabajadores;
    private JPanel cardPanel;
    private JScrollPane scrollPane;
    private JPanel panelListaTrabajadores;
    private String[] alfabeto = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private JButton[] alfabetoButtons = {aButton, bButton, cButton, dButton, eButton, fButton, gButton, hButton, iButton,
            jButton, kButton, lButton, mButton, nButton, gnButton, oButton, pButton, qButton, rButton, sButton, tButton, uButton, vButton,
            wButton, xButton, yButton, zButton};
    private List<Trabajador> trabajadores;
    private boolean listandoTrabajadores = false;

    public EleccionTrabajadorView() {
        VOLVERButton.addActionListener(e -> {
                    if (!listandoTrabajadores)
                        TerminalUsuario.iniciar();
                    else {
                        ((CardLayout) cardPanel.getLayout()).show(cardPanel, "alfabetoCard");
                        listandoTrabajadores = false;
                    }
                }
        );

        for (int i = 0; i < alfabetoButtons.length; i++) {
            String inicial = alfabeto[i];
            alfabetoButtons[i].addActionListener(e -> eleccionPorInicial(inicial));
        }

        filtrarAlfabeto();
    }

    private void eleccionPorInicial(String inicial) {
        System.out.println(inicial);
        ((CardLayout) cardPanel.getLayout()).show(cardPanel, "trabajadoresCard");
        listandoTrabajadores = true;

        List<Trabajador> trabajadoresPorInicial = new ArrayList<>();
        for (Trabajador trabajador : trabajadores) {
            if (trabajador.getApellidos().substring(0, 1).toLowerCase().equals(inicial))
                trabajadoresPorInicial.add(trabajador);
        }
        panelListaTrabajadores.removeAll();

        panelListaTrabajadores.setLayout(new java.awt.GridLayout(4, 4));
        for (int i = 0; i < trabajadoresPorInicial.size(); ++i) {
            Trabajador trabajador = trabajadoresPorInicial.get(i);
            JButton b = new JButton(trabajador.getApellidos() + ", " + trabajador.getNombre());
            b.addActionListener(e -> seleccionarTrabajador(trabajador));
            panelListaTrabajadores.add(b);
        }
    }

    private void filtrarAlfabeto() {
        trabajadores = TrabajadorController.getAll();
        HashSet<String> iniciales = new HashSet<>();
        for (Trabajador trabajador : trabajadores) {
            iniciales.add(trabajador.getApellidos().substring(0, 1).toLowerCase());
        }
        for (int i = 0; i < alfabetoButtons.length; i++) {
            if (iniciales.contains(alfabeto[i])) alfabetoButtons[i].setEnabled(true);
        }
    }

    public JPanel getPanelBaseAlfabeto() {
        return panelBaseAlfabeto;
    }

    private void seleccionarTrabajador(Trabajador trabajador) {
        System.out.println(trabajador);
    }

}
