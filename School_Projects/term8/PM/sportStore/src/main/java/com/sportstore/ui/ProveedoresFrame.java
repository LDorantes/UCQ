package com.sportstore.ui;

import com.sportstore.models.Proveedor;
import com.sportstore.services.ProveedorService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class ProveedoresFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nombreField, contactoField, telefonoField, direccionField;

    public ProveedoresFrame() {
        setTitle("Gestión de Proveedores");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ProveedorService service = new ProveedorService();

        String[] columnas = {"ID", "Nombre", "Contacto", "Teléfono", "Dirección"};
        tableModel = new DefaultTableModel(columnas, 0);
        table = new JTable(tableModel);
        cargarProveedores(service);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nombreField = new JTextField();
        contactoField = new JTextField();
        telefonoField = new JTextField();
        direccionField = new JTextField();

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Contacto:"));
        formPanel.add(contactoField);
        formPanel.add(new JLabel("Teléfono:"));
        formPanel.add(telefonoField);
        formPanel.add(new JLabel("Dirección:"));
        formPanel.add(direccionField);

        JButton agregarBtn = new JButton("Agregar");
        JButton eliminarBtn = new JButton("Eliminar");
        JButton editarBtn = new JButton("Editar");

        agregarBtn.addActionListener(e -> {
            Proveedor p = new Proveedor(
                nombreField.getText(),
                contactoField.getText(),
                telefonoField.getText(),
                direccionField.getText()
            );
            if (service.addProveedor(p)) {
                cargarProveedores(service);
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Proveedor agregado.");
            }
        });

        eliminarBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                if (service.deleteProveedor(id)) {
                    cargarProveedores(service);
                    limpiarCampos();
                    JOptionPane.showMessageDialog(this, "Proveedor eliminado.");
                }
            }
        });

        editarBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                Proveedor p = new Proveedor(id,
                        nombreField.getText(),
                        contactoField.getText(),
                        telefonoField.getText(),
                        direccionField.getText()
                );
                if (service.updateProveedor(p)) {
                    cargarProveedores(service);
                    limpiarCampos();
                    JOptionPane.showMessageDialog(this, "Proveedor actualizado.");
                }
            }
        });

        JPanel botones = new JPanel();
        botones.add(agregarBtn);
        botones.add(editarBtn);
        botones.add(eliminarBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.EAST);
        add(botones, BorderLayout.SOUTH);

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                nombreField.setText(tableModel.getValueAt(row, 1).toString());
                contactoField.setText(tableModel.getValueAt(row, 2).toString());
                telefonoField.setText(tableModel.getValueAt(row, 3).toString());
                direccionField.setText(tableModel.getValueAt(row, 4).toString());
            }
        });
    }

    private void cargarProveedores(ProveedorService service) {
        tableModel.setRowCount(0);
        List<Proveedor> lista = service.getAllProveedores();
        for (Proveedor p : lista) {
            tableModel.addRow(new Object[]{
                p.getId(), p.getNombre(), p.getContacto(), p.getTelefono(), p.getDireccion()
            });
        }
    }

    private void limpiarCampos() {
        nombreField.setText("");
        contactoField.setText("");
        telefonoField.setText("");
        direccionField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProveedoresFrame().setVisible(true));
    }
}