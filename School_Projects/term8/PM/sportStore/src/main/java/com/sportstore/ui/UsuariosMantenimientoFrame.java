package com.sportstore.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.sportstore.models.Usuario;
import com.sportstore.services.UsuarioService;

public class UsuariosMantenimientoFrame extends JFrame {
    private JTable usuariosTable;
    private DefaultTableModel tableModel;
    private JTextField usernameField, passwordField, buscarField;
    private UsuarioService usuarioService;

    public UsuariosMantenimientoFrame() {
        setTitle("Gestión de Usuarios");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        usuarioService = new UsuarioService();

        String[] columnas = {"ID", "Username", "Password"};
        tableModel = new DefaultTableModel(columnas, 0);
        usuariosTable = new JTable(tableModel);
        usuariosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(usuariosTable);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        usernameField = new JTextField();
        passwordField = new JTextField();

        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        JButton agregarBtn = new JButton("Agregar Usuario");
        JButton eliminarBtn = new JButton("Eliminar Usuario");

        formPanel.add(agregarBtn);
        formPanel.add(eliminarBtn);

        buscarField = new JTextField();
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Buscar:"), BorderLayout.WEST);
        topPanel.add(buscarField, BorderLayout.CENTER);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        usuariosTable.setRowSorter(sorter);
        buscarField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

            private void filtrar() {
                String texto = buscarField.getText();
                sorter.setRowFilter(texto.trim().isEmpty() ? null : RowFilter.regexFilter("(?i)" + texto));
            }
        });

        agregarBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (!username.isEmpty() && !password.isEmpty()) {
                Usuario nuevo = new Usuario(0, username, password);
                if (usuarioService.addUsuario(nuevo)) {
                    JOptionPane.showMessageDialog(this, "Usuario agregado exitosamente");
                    cargarUsuarios();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar usuario");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            }
        });

        eliminarBtn.addActionListener(e -> {
            int selectedRow = usuariosTable.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                if (usuarioService.deleteUsuario(id)) {
                    JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente");
                    cargarUsuarios();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar usuario");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un usuario para eliminar");
            }
        });

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.EAST);

        cargarUsuarios();
    }

    private void cargarUsuarios() {
        tableModel.setRowCount(0);
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        for (Usuario u : usuarios) {
            tableModel.addRow(new Object[]{u.getId(), u.getUsername(), u.getPassword()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UsuariosMantenimientoFrame().setVisible(true));
    }
}
