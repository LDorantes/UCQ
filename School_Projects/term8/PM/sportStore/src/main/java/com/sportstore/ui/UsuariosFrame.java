package com.sportstore.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class UsuariosFrame extends JFrame {
    private JTable usuariosTable;
    private DefaultTableModel tableModel;
    private JTextField nombreField, contraseñaField, rolField;
    private JButton agregarButton, editarButton, eliminarButton;

    public UsuariosFrame() {
        setTitle("Gestión de Usuarios");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnas = {"Nombre", "Contraseña", "Rol"};
        tableModel = new DefaultTableModel(columnas, 0);
        usuariosTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(usuariosTable);

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nombreField = new JTextField();
        contraseñaField = new JTextField();
        rolField = new JTextField();

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Contraseña:"));
        formPanel.add(contraseñaField);
        formPanel.add(new JLabel("Rol:"));
        formPanel.add(rolField);

        agregarButton = new JButton("Agregar Usuario");
        editarButton = new JButton("Editar Usuario");
        eliminarButton = new JButton("Eliminar Usuario");

        formPanel.add(agregarButton);
        formPanel.add(editarButton);

        // Panel de botones
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(eliminarButton);

        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Acción: Agregar usuario
        agregarButton.addActionListener(e -> {
            String nombre = nombreField.getText();
            String contraseña = contraseñaField.getText();
            String rol = rolField.getText();

            if (nombre.isEmpty() || contraseña.isEmpty() || rol.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor llena todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            tableModel.addRow(new Object[]{nombre, contraseña, rol});
            limpiarCampos();
        });

        // Acción: Editar usuario
        editarButton.addActionListener(e -> {
            int selectedRow = usuariosTable.getSelectedRow();
            if (selectedRow != -1) {
                String nombre = nombreField.getText();
                String contraseña = contraseñaField.getText();
                String rol = rolField.getText();

                if (nombre.isEmpty() || contraseña.isEmpty() || rol.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor llena todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                tableModel.setValueAt(nombre, selectedRow, 0);
                tableModel.setValueAt(contraseña, selectedRow, 1);
                tableModel.setValueAt(rol, selectedRow, 2);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un usuario para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Acción: Eliminar usuario
        eliminarButton.addActionListener(e -> {
            int selectedRow = usuariosTable.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un usuario para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Acción: Llenar formulario al seleccionar una fila
        usuariosTable.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = usuariosTable.getSelectedRow();
            if (selectedRow != -1) {
                nombreField.setText((String) tableModel.getValueAt(selectedRow, 0));
                contraseñaField.setText((String) tableModel.getValueAt(selectedRow, 1));
                rolField.setText((String) tableModel.getValueAt(selectedRow, 2));
            }
        });
    }

    private void limpiarCampos() {
        nombreField.setText("");
        contraseñaField.setText("");
        rolField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UsuariosFrame().setVisible(true));
    }
}
