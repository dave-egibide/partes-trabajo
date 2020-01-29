package com.company.Views.AdminViews;

import com.company.Controllers.HibernateController;
import com.company.Controllers.TrabajadorController;
import com.company.Models.Trabajador;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class AdminTrabajadoresView {
    private JPanel panelBase;
    private JTextField textFieldVal1;
    private JButton verButton;
    private JButton listadoTrabajadoresButton;
    private JTextField textFieldVal2;
    private JTextField textFieldVal3;
    private JTextField textFieldVal4;
    private JButton crearButton;
    private JButton modificarButton;
    private JTextField textFieldId;
    private JPanel cardPanel;
    private JTable listadoTable;
    private JScrollPane listadoScrollPane;
    private JCheckBox mostrarInactivosCheckBox;
    private JButton gestionarButton;
    private static JFrame frame = AdministradorMainView.getFrame();


    public AdminTrabajadoresView() {
        verButton.addActionListener(e -> verById(null));
        listadoTrabajadoresButton.addActionListener(e -> listadoTrabajadores());
        modificarButton.addActionListener(e -> modificarTrabajador());
        crearButton.addActionListener(e -> crearTrabajador());
        mostrarInactivosCheckBox.addActionListener(e -> listarElemento());
        gestionarButton.addActionListener(e -> {
            String id = String.valueOf(listadoTable.getValueAt(listadoTable.getSelectedRow(), 0));
            verById(id);
            ((CardLayout) cardPanel.getLayout()).show(cardPanel, "gestion");
        });
    }

    private void crearTrabajador() {
        try {
            int id;
            if (textFieldId.getText().trim().isBlank()) id = -1;
            else id = Integer.parseInt(StringUtils.left(textFieldId.getText().trim(), 5));

            Trabajador trabajador = TrabajadorController.getById(id);
            if (trabajador != null) {
                JOptionPane.showMessageDialog(AdministradorMainView.getFrame(),
                        "Id ya existente",
                        "Error Creación",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String error = "";
                if (textFieldVal1.getText().trim().equals("")) {
                    error = "El DNI";
                } else if (textFieldVal2.getText().trim().equals("")) {
                    error = "El Nombre";
                } else if (textFieldVal3.getText().trim().equals("")) {
                    error = "El Apellido";
                }

                if (!error.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            error.concat(" del Trabajador ha de especificarse"),
                            "Error Creación",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    String dni = StringUtils.left(textFieldVal1.getText().trim().toUpperCase(), 9);
                    String nombre = StringUtils.left(StringUtils.capitalize(textFieldVal2.getText().trim().toLowerCase()), 20);
                    String apellidos = StringUtils.left(StringUtils.capitalize(textFieldVal3.getText().trim().toLowerCase()), 50);
                    String puesto = StringUtils.left(StringUtils.capitalize(textFieldVal4.getText().trim().toLowerCase()), 20);
                    if (puesto.isBlank()) puesto = null;

                    if (id < 1) trabajador = new Trabajador(dni, nombre, apellidos, puesto);
                    else trabajador = new Trabajador(id, dni, nombre, apellidos, puesto);

                    boolean creadoCorrectamente = HibernateController.add(trabajador);

                    if (creadoCorrectamente) {
                        JOptionPane.showMessageDialog(frame,
                                "Creado correctamente.",
                                "Creación Correcta",
                                JOptionPane.PLAIN_MESSAGE);
                        limpiarCampos();

                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Creación no efectuada.",
                                "Error Creación",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (
                NumberFormatException e) {
            JOptionPane.showMessageDialog(AdministradorMainView.getFrame(),
                    "Formato Incorrecto de Id",
                    "Error Lectura",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void modificarTrabajador() {
        String id = textFieldId.getText();
        try {
            Trabajador trabajador = TrabajadorController.getById(Integer.parseInt(id));
            if (trabajador == null) {
                JOptionPane.showMessageDialog(AdministradorMainView.getFrame(),
                        "Id No Encontrado",
                        "Error Lectura",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String error = "";
                if (textFieldVal1.getText().trim().equals("")) {
                    error = "El DNI";
                } else if (textFieldVal2.getText().trim().equals("")) {
                    error = "El Nombre";
                } else if (textFieldVal3.getText().trim().equals("")) {
                    error = "El Apellido";
                }

                if (!error.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            error.concat(" del Trabajador ha de especificarse"),
                            "Error Modificación",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    trabajador.setDni(StringUtils.left(textFieldVal1.getText().trim().toUpperCase(), 9));
                    trabajador.setNombre(StringUtils.left(StringUtils.capitalize(textFieldVal2.getText().trim().toLowerCase()), 20));
                    trabajador.setApellidos(StringUtils.left(StringUtils.capitalize(textFieldVal3.getText().trim().toLowerCase()), 50));
                    String puesto = StringUtils.left(StringUtils.capitalize(textFieldVal4.getText().trim().toLowerCase()), 20);
                    if (puesto.isBlank()) puesto = null;
                    trabajador.setPuesto(puesto);

                    boolean modificadoCorrectamente = HibernateController.modify(trabajador);

                    if (modificadoCorrectamente) {
                        JOptionPane.showMessageDialog(frame,
                                "Modificado correctamente.",
                                "Modificación Correcta",
                                JOptionPane.PLAIN_MESSAGE);
                        limpiarCampos();
                        verById(id);

                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Modificación no efectuada.",
                                "Error Modificación",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (
                NumberFormatException e) {
            JOptionPane.showMessageDialog(AdministradorMainView.getFrame(),
                    "Formato Incorrecto de Id",
                    "Error Lectura",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void limpiarCampos() {
        textFieldVal1.setText("");
        textFieldVal2.setText("");
        textFieldVal3.setText("");
        textFieldVal4.setText("");
    }

    public void listadoTrabajadores() {
        ((CardLayout) cardPanel.getLayout()).show(cardPanel, "listado");
        listarElemento();
    }

    private void listarElemento() {
        Vector<String> tableHeaders = new Vector<>();
        Vector tableData = new Vector();
        tableHeaders.add("ID");
        tableHeaders.add("DNI");
        tableHeaders.add("Nombre");
        tableHeaders.add("Apellidos");
        tableHeaders.add("Puesto");
        tableHeaders.add("Activo");

        List<Trabajador> trabajadores;

        if (mostrarInactivosCheckBox.isSelected()) trabajadores = TrabajadorController.getAll();
        else trabajadores = TrabajadorController.getAllActivos();

        for (Object o : trabajadores) {
            Trabajador trabajador = (Trabajador) o;
            Vector<Object> linea = new Vector<>();
            linea.add(trabajador.getId());
            linea.add(trabajador.getDni());
            linea.add(trabajador.getNombre());
            linea.add(trabajador.getApellidos());
            linea.add(trabajador.getPuesto());
            String activo;
            if (trabajador.getActivo() == 1) activo = "Sí";
            else activo = "No";
            linea.add(activo);
            tableData.add(linea);
        }
        DefaultTableModel tableModel = new DefaultTableModel(tableData, tableHeaders) {


            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }


            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        listadoTable.setModel(tableModel);
        listadoTable.setRowHeight(50);
        listadoTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        listadoTable.getTableHeader().setReorderingAllowed(false);
        listadoTable.getColumnModel().setColumnSelectionAllowed(false);
        listadoTable.getColumnModel().getColumn(0).setResizable(false);
        //listadoTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        listadoTable.getColumnModel().getColumn(1).setResizable(false);
        //listadoTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        listadoTable.getColumnModel().getColumn(2).setResizable(false);
        listadoTable.getColumnModel().getColumn(3).setResizable(false);
        listadoTable.getColumnModel().getColumn(4).setResizable(false);
        listadoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void verById(String id) {
        if (id == null) {
            id = textFieldId.getText();
        }
        try {
            Trabajador trabajador = TrabajadorController.getById(Integer.parseInt(id));
            if (trabajador == null) {
                JOptionPane.showMessageDialog(AdministradorMainView.getFrame(),
                        "Id No Encontrado",
                        "Error Lectura",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                textFieldId.setText(String.valueOf(trabajador.getId()));
                textFieldVal1.setText(trabajador.getDni());
                textFieldVal2.setText(trabajador.getNombre());
                textFieldVal3.setText(trabajador.getApellidos());
                textFieldVal4.setText(trabajador.getPuesto());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(AdministradorMainView.getFrame(),
                    "Formato Incorrecto de Id",
                    "Error Lectura",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public JPanel getPanelBase() {
        return panelBase;
    }
}
