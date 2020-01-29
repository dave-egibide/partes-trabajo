package com.company.Views.AdminViews;

import com.company.Controllers.HibernateController;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class AdministradorMainView {
    private JPanel panelBase;
    private JPanel panelInicio;
    private JMenuBar menuBar;
    private JMenu menuArchivo, menuTrabajadores, menuTareas, menuMaquinas, menuAyuda;
    private JMenuItem menuItemVolver, menuItemSalir, menuItemListaTrabajadores, menuItemAdminTrabajadores,
            menuItemListaTareas, menuItemAdminTareas, menuItemListaMaquinas, menuItemAdminMaquinas, menuItemAcercaDe;
    private static JFrame frame;
    private static VentanaAcercaDe ventanaAcercaDe;

    public static JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        frame = new JFrame("Gestión Partes Trabajo");
        frame.setContentPane(new AdministradorMainView().panelBase);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        boolean conectado = HibernateController.conectar();

    }

    public AdministradorMainView() {
        initMenu();

        menuItemVolver.addActionListener(e -> {
            frame.setContentPane(panelInicio);
        });

        menuItemSalir.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                    panelBase,
                    "¿Terminar la aplicación?",
                    "Salir",
                    JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        });

        menuItemAdminTrabajadores.addActionListener(e -> {
            AdminTrabajadoresView adminTrabajadoresView = new AdminTrabajadoresView();
            frame.setContentPane(adminTrabajadoresView.getPanelBase());
            frame.pack();
        });

        menuItemListaTrabajadores.addActionListener(e -> {
            AdminTrabajadoresView adminTrabajadoresView = new AdminTrabajadoresView();
            frame.setContentPane(adminTrabajadoresView.getPanelBase());
            adminTrabajadoresView.listadoTrabajadores();
            frame.pack();
        });

        menuItemAdminTareas.addActionListener(e -> {
            AdminTareasView adminTareasView = new AdminTareasView();
            frame.setContentPane(adminTareasView.getPanelBase());
            frame.pack();
        });

        menuItemListaTareas.addActionListener(e -> {
            AdminTareasView adminTareasView = new AdminTareasView();
            frame.setContentPane(adminTareasView.getPanelBase());
            adminTareasView.listadoTareas();
            frame.pack();
        });

        menuItemAdminMaquinas.addActionListener(e -> {
            AdminMaquinasView adminMaquinasView = new AdminMaquinasView();
            frame.setContentPane(adminMaquinasView.getPanelBase());
            frame.pack();
        });

        menuItemListaMaquinas.addActionListener(e -> {
            AdminMaquinasView adminMaquinasView = new AdminMaquinasView();
            frame.setContentPane(adminMaquinasView.getPanelBase());
            adminMaquinasView.listadoMaquinas();
            frame.pack();
        });

        menuItemAcercaDe.addActionListener(e -> {
            boolean ventanaExiste = false;
            if (ventanaAcercaDe != null) {
                ventanaExiste = ventanaAcercaDe.isVisible();
            }
            if (!ventanaExiste) {
                ventanaAcercaDe = new VentanaAcercaDe();
                ventanaAcercaDe.setLocationRelativeTo(frame);
                ventanaAcercaDe.load();
            } else {
                ventanaAcercaDe.requestFocus();
            }
        });
    }

    private void initMenu() {
        menuBar = new JMenuBar();

        menuArchivo = new JMenu("Archivo");
        menuArchivo.setMnemonic(KeyEvent.VK_A);
        menuArchivo.getAccessibleContext().setAccessibleDescription(
                "Archivo");
        menuBar.add(menuArchivo);

        menuItemVolver = new JMenuItem("Volver a Inicio", KeyEvent.VK_V);
        menuItemVolver.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_DOWN_MASK));
        menuItemVolver.getAccessibleContext().setAccessibleDescription("Volver a la pantalla de inicio");
        menuArchivo.add(menuItemVolver);

        menuItemSalir = new JMenuItem("Salir", KeyEvent.VK_S);
        menuItemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        menuItemSalir.getAccessibleContext().setAccessibleDescription("Finalizar la aplicación");
        menuArchivo.add(menuItemSalir);


        menuTrabajadores = new JMenu("Trabajadores");
        menuTrabajadores.setMnemonic(KeyEvent.VK_2);
        menuTrabajadores.getAccessibleContext().setAccessibleDescription("Trabajadores");
        menuBar.add(menuTrabajadores);

        menuItemListaTrabajadores = new JMenuItem("Listar Trabajadores", KeyEvent.VK_L);
        menuItemListaTrabajadores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.ALT_DOWN_MASK));
        menuItemListaTrabajadores.getAccessibleContext().setAccessibleDescription("Listar Trabajadores");
        menuTrabajadores.add(menuItemListaTrabajadores);

        menuItemAdminTrabajadores = new JMenuItem("Gestionar Trabajadores", KeyEvent.VK_G);
        menuItemAdminTrabajadores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.SHIFT_DOWN_MASK));
        menuItemAdminTrabajadores.getAccessibleContext().setAccessibleDescription("Gestionar Trabajadores");
        menuTrabajadores.add(menuItemAdminTrabajadores);

        menuTareas = new JMenu("Tareas");
        menuTareas.setMnemonic(KeyEvent.VK_3);
        menuTareas.getAccessibleContext().setAccessibleDescription("Tareas");
        menuBar.add(menuTareas);

        menuItemListaTareas = new JMenuItem("Listar Tareas", KeyEvent.VK_L);
        menuItemListaTareas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.ALT_DOWN_MASK));
        menuItemListaTareas.getAccessibleContext().setAccessibleDescription("Listar Tareas");
        menuTareas.add(menuItemListaTareas);

        menuItemAdminTareas = new JMenuItem("Gestionar Tareas", KeyEvent.VK_G);
        menuItemAdminTareas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.SHIFT_DOWN_MASK));
        menuItemAdminTareas.getAccessibleContext().setAccessibleDescription("Gestionar Tareas");
        menuTareas.add(menuItemAdminTareas);

        menuMaquinas = new JMenu("Máquinas");
        menuMaquinas.setMnemonic(KeyEvent.VK_4);
        menuMaquinas.getAccessibleContext().setAccessibleDescription("Máquinas");
        menuBar.add(menuMaquinas);

        menuItemListaMaquinas = new JMenuItem("Listar Máquinas", KeyEvent.VK_L);
        menuItemListaMaquinas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.ALT_DOWN_MASK));
        menuItemListaMaquinas.getAccessibleContext().setAccessibleDescription("Listar Máquinas");
        menuMaquinas.add(menuItemListaMaquinas);

        menuItemAdminMaquinas = new JMenuItem("Gestionar Máquinas", KeyEvent.VK_G);
        menuItemAdminMaquinas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.SHIFT_DOWN_MASK));
        menuItemAdminMaquinas.getAccessibleContext().setAccessibleDescription("Gestionar Máquinas");
        menuMaquinas.add(menuItemAdminMaquinas);

        menuAyuda = new JMenu("Ayuda");
        menuAyuda.setMnemonic(KeyEvent.VK_Y);
        menuAyuda.getAccessibleContext().setAccessibleDescription(
                "Ayuda");
        menuBar.add(menuAyuda);

        menuItemAcercaDe = new JMenuItem("Acerca de...", KeyEvent.VK_A);
        menuItemAcercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.ALT_DOWN_MASK));
        menuItemAcercaDe.getAccessibleContext().setAccessibleDescription("Acerca de...");
        menuAyuda.add(menuItemAcercaDe);

        frame.setJMenuBar(menuBar);
    }
}
