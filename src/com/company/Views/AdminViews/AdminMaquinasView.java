package com.company.Views.AdminViews;

import com.company.Controllers.HibernateController;
import com.company.Controllers.MaquinaController;
import com.company.Models.Maquina;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class AdminMaquinasView {
    private JPanel panelBase;
    private JTextField textFieldVal1;
    private JButton verButton;
    private JButton listadoMaquinasButton;
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


    public AdminMaquinasView() {
        verButton.addActionListener(e -> verById(null));
        listadoMaquinasButton.addActionListener(e -> listadoMaquinas());
        modificarButton.addActionListener(e -> modificarMaquina());
        crearButton.addActionListener(e -> crearMaquina());
        mostrarInactivosCheckBox.addActionListener(e -> listarElemento());
        gestionarButton.addActionListener(e -> {
            String id = String.valueOf(listadoTable.getValueAt(listadoTable.getSelectedRow(), 0));
            verById(id);
            ((CardLayout) cardPanel.getLayout()).show(cardPanel, "gestion");
        });
    }

    private void crearMaquina() {
        try {
            int id;
            if (textFieldId.getText().trim().isBlank()) id = -1;
            else id = Integer.parseInt(StringUtils.left(textFieldId.getText().trim(), 5));

            Maquina maquina = MaquinaController.getById(id);
            if (maquina != null) {
                JOptionPane.showMessageDialog(AdministradorMainView.getFrame(),
                        "Id ya existente",
                        "Error Creación",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String error = "";
                if (textFieldVal1.getText().trim().equals("")) error = "El Nombre";
                if (!error.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            error.concat(" de la Máquina ha de especificarse"),
                            "Error Creación",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    String nombre = StringUtils.left(StringUtils.capitalize(textFieldVal2.getText().trim().toLowerCase()), 10);
                    String matricula = StringUtils.left(StringUtils.capitalize(textFieldVal4.getText().trim().toLowerCase()), 20);
                    if (matricula.isBlank()) matricula = null;

                    if (id < 1) maquina = new Maquina(nombre, matricula);
                    else maquina = new Maquina(id, nombre, matricula);

                    boolean creadoCorrectamente = HibernateController.add(maquina);

                    if (creadoCorrectamente) {
                        JOptionPane.showMessageDialog(frame,
                                "Creada correctamente.",
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

    private void modificarMaquina() {
        String id = textFieldId.getText();
        try {
            Maquina maquina = MaquinaController.getById(Integer.parseInt(id));
            if (maquina == null) {
                JOptionPane.showMessageDialog(AdministradorMainView.getFrame(),
                        "Id No Encontrado",
                        "Error Lectura",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String error = "";
                if (textFieldVal2.getText().trim().equals("")) error = "El Nombre";
                if (!error.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            error.concat(" de la Máquina ha de especificarse"),
                            "Error Modificación",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    maquina.setNombre(StringUtils.left(StringUtils.capitalize(textFieldVal2.getText().trim().toLowerCase()), 10));
                    String matricula = StringUtils.left(StringUtils.capitalize(textFieldVal4.getText().trim().toLowerCase()), 20);
                    if (matricula.isBlank()) matricula = null;
                    maquina.setMatricula(matricula);

                    boolean modificadoCorrectamente = HibernateController.modify(maquina);

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
        textFieldVal4.setText("");
    }

    public void listadoMaquinas() {
        ((CardLayout) cardPanel.getLayout()).show(cardPanel, "listado");
        listarElemento();
    }

    private void listarElemento() {
        Vector<String> tableHeaders = new Vector<>();
        Vector tableData = new Vector();
        tableHeaders.add("ID");
        tableHeaders.add("Nombre");
        tableHeaders.add("Matrícula");
        tableHeaders.add("Activo");

        List<Maquina> maquinas;

        if (mostrarInactivosCheckBox.isSelected()) maquinas = MaquinaController.getAll();
        else maquinas = MaquinaController.getAllActivos();

        for (Object o : maquinas) {
            Maquina maquina = (Maquina) o;
            Vector<Object> linea = new Vector<>();
            linea.add(maquina.getId());
            linea.add(maquina.getNombre());
            linea.add(maquina.getMatricula());
            String activo;
            if (maquina.getActivo() == 1) activo = "Sí";
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
            Maquina maquina = MaquinaController.getById(Integer.parseInt(id));
            if (maquina == null) {
                JOptionPane.showMessageDialog(AdministradorMainView.getFrame(),
                        "Id No Encontrado",
                        "Error Lectura",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                textFieldId.setText(String.valueOf(maquina.getId()));
                textFieldVal2.setText(maquina.getNombre());
                textFieldVal4.setText(maquina.getMatricula());
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
