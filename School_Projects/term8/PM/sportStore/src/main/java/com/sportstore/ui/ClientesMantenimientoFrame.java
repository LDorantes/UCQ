package com.sportstore.ui;

import com.sportstore.models.Cliente;
import com.sportstore.services.ClienteService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class ClientesMantenimientoFrame extends JFrame {
    private JTable clientesTable;
    private DefaultTableModel tableModel;
    private JTextField nombreField, apellidoField, emailField, telefonoField, direccionField, buscarField;
    private ClienteService clienteService;

    public ClientesMantenimientoFrame() {
        setTitle("Gestión de Clientes");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        clienteService = new ClienteService();

        String[] columnas = {"ID", "Nombre", "Apellido", "Email", "Teléfono", "Dirección"};
        tableModel = new DefaultTableModel(columnas, 0);
        clientesTable = new JTable(tableModel);
        clientesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(clientesTable);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        nombreField = new JTextField();
        apellidoField = new JTextField();
        emailField = new JTextField();
        telefonoField = new JTextField();
        direccionField = new JTextField();

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Apellido:"));
        formPanel.add(apellidoField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Teléfono:"));
        formPanel.add(telefonoField);
        formPanel.add(new JLabel("Dirección:"));
        formPanel.add(direccionField);

        JButton agregarBtn = new JButton("Agregar Cliente");
        JButton eliminarBtn = new JButton("Eliminar Cliente");

        formPanel.add(agregarBtn);
        formPanel.add(eliminarBtn);

        buscarField = new JTextField();
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Buscar:"), BorderLayout.WEST);
        topPanel.add(buscarField, BorderLayout.CENTER);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        clientesTable.setRowSorter(sorter);
        buscarField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }
            private void filtrar() {
                String texto = buscarField.getText();
                if (texto.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
                }
            }
        });

        agregarBtn.addActionListener(e -> {
            Cliente nuevo = new Cliente();
            nuevo.setNombre(nombreField.getText());
            nuevo.setApellido(apellidoField.getText());
            nuevo.setEmail(emailField.getText());
            nuevo.setTelefono(telefonoField.getText());
            nuevo.setDireccion(direccionField.getText());
            if (clienteService.addCliente(nuevo)) {
                JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente");
                cargarClientes();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar cliente");
            }
        });

        eliminarBtn.addActionListener(e -> {
            int selectedRow = clientesTable.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                if (clienteService.deleteCliente(id)) {
                    JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente");
                    cargarClientes();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar cliente");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un cliente para eliminar");
            }
        });

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.EAST);

        cargarClientes();
    }

    private void cargarClientes() {
        tableModel.setRowCount(0);
        List<Cliente> clientes = clienteService.getAllClientes();
        for (Cliente c : clientes) {
            tableModel.addRow(new Object[]{
                c.getId(), c.getNombre(), c.getApellido(), c.getEmail(), c.getTelefono(), c.getDireccion()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientesMantenimientoFrame().setVisible(true));
    }
}