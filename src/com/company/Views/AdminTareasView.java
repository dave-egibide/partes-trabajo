package com.company.Views;

import com.company.Controllers.HibernateController;
import com.company.Controllers.TareaController;
import com.company.Models.Tarea;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class AdminTareasView {
    private JPanel panelBase;
    private JTextField textFieldVal1;
    private JButton verButton;
    private JButton listadoTareasButton;
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
    private JComboBox tipoComboBox;
    private JTextField descripcionTextField;
    private static JFrame frame = AdministradorMainView.getFrame();


    public AdminTareasView() {
        verButton.addActionListener(e -> verById(null));
        listadoTareasButton.addActionListener(e -> listadoTareas());
        modificarButton.addActionListener(e -> modificarTarea());
        crearButton.addActionListener(e -> crearTarea());
        mostrarInactivosCheckBox.addActionListener(e -> listarElemento());
        gestionarButton.addActionListener(e -> {
            String id = String.valueOf(listadoTable.getValueAt(listadoTable.getSelectedRow(), 0));
            verById(id);
            ((CardLayout) cardPanel.getLayout()).show(cardPanel, "gestion");
        });
        tipoComboBox.addItem("PROD");
        tipoComboBox.addItem("MANT");
    }

    private void crearTarea() {
        try {
            int id;
            if (textFieldId.getText().trim().isBlank()) id = -1;
            else id = Integer.parseInt(StringUtils.left(textFieldId.getText().trim(), 5));

            Tarea tarea = TareaController.getById(id);
            if (tarea != null) {
                JOptionPane.showMessageDialog(AdministradorMainView.getFrame(),
                        "Id ya existente",
                        "Error Creación",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String error = "";
                if (textFieldVal2.getText().trim().equals("")) error = "El Nombre";

                if (!error.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            error.concat(" de la tarea ha de especificarse"),
                            "Error Creación",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    String nombre = StringUtils.left(StringUtils.capitalize(textFieldVal2.getText().trim().toLowerCase()), 20);
                    String tipo = String.valueOf(tipoComboBox.getSelectedItem());
                    String descripcion = StringUtils.left(StringUtils.capitalize(descripcionTextField.getText().trim().toLowerCase()), 200);
                    if (descripcion.isBlank()) descripcion = null;

                    if (id < 1) tarea = new Tarea(nombre, descripcion, tipo);
                    else tarea = new Tarea(id, nombre, descripcion, tipo);

                    boolean creadoCorrectamente = HibernateController.add(tarea);

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

    private void modificarTarea() {
        String id = textFieldId.getText();
        try {
            Tarea tarea = TareaController.getById(Integer.parseInt(id));
            if (tarea == null) {
                JOptionPane.showMessageDialog(AdministradorMainView.getFrame(),
                        "Id No Encontrado",
                        "Error Lectura",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String error = "";
                if (textFieldVal2.getText().trim().equals("")) error = "El Nombre";


                if (!error.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            error.concat(" de la Tarea ha de especificarse"),
                            "Error Modificación",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    tarea.setNombre(StringUtils.capitalize(textFieldVal2.getText().trim().toLowerCase()));
                    tarea.setDescripcion(StringUtils.capitalize(descripcionTextField.getText().trim().toLowerCase()));
                    tarea.setTipo(String.valueOf(tipoComboBox.getSelectedItem()));

                    boolean modificadoCorrectamente = HibernateController.modify(tarea);

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
        textFieldId.setText("");
        textFieldVal2.setText("");
        descripcionTextField.setText("");
        tipoComboBox.setSelectedIndex(0);
    }

    public void listadoTareas() {
        ((CardLayout) cardPanel.getLayout()).show(cardPanel, "listado");
        listarElemento();
    }

    private void listarElemento() {
        Vector<String> tableHeaders = new Vector<>();
        Vector tableData = new Vector();
        tableHeaders.add("ID");
        tableHeaders.add("Nombre");
        tableHeaders.add("Descripción");
        tableHeaders.add("Tipo");
        tableHeaders.add("Activo");

        List<Tarea> tareas;

        if (mostrarInactivosCheckBox.isSelected()) tareas = TareaController.getAll();
        else tareas = TareaController.getAllActivos();

        for (Object o : tareas) {
            Tarea tarea = (Tarea) o;
            Vector<Object> linea = new Vector<>();
            linea.add(tarea.getId());
            linea.add(tarea.getNombre());
            linea.add(tarea.getDescripcion());
            linea.add(tarea.getTipo());
            String activo;
            if (tarea.getActivo() == 1) activo = "Sí";
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
        listadoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void verById(String id) {
        if (id == null) {
            id = textFieldId.getText();
        }
        try {
            Tarea tarea = TareaController.getById(Integer.parseInt(id));
            if (tarea == null) {
                JOptionPane.showMessageDialog(AdministradorMainView.getFrame(),
                        "Id No Encontrado",
                        "Error Lectura",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                textFieldId.setText(String.valueOf(tarea.getId()));
                textFieldVal2.setText(tarea.getNombre());
                descripcionTextField.setText(tarea.getDescripcion());
                tipoComboBox.setSelectedItem(tarea.getTipo());
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
