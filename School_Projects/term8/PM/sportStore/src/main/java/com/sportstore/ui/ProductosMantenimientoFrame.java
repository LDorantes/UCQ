package com.sportstore.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ProductosMantenimientoFrame extends JFrame {
    private JTable productosTable;
    private DefaultTableModel tableModel;
    private JTextField nombreField, precioField, stockField, buscarField;
    private JButton agregarButton, editarButton, eliminarButton;
    private TableRowSorter<DefaultTableModel> rowSorter;

    public ProductosMantenimientoFrame() {
        setTitle("Gestión de Productos");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnas = {"Nombre", "Precio", "Stock"};
        tableModel = new DefaultTableModel(columnas, 0);
        productosTable = new JTable(tableModel);
        rowSorter = new TableRowSorter<>(tableModel);
        productosTable.setRowSorter(rowSorter);

        JScrollPane scrollPane = new JScrollPane(productosTable);

        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buscarField = new JTextField(20);
        searchPanel.add(new JLabel("Buscar producto:"));
        searchPanel.add(buscarField);

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nombreField = new JTextField();
        precioField = new JTextField();
        stockField = new JTextField();

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Precio:"));
        formPanel.add(precioField);
        formPanel.add(new JLabel("Stock:"));
        formPanel.add(stockField);

        agregarButton = new JButton("Agregar Producto");
        editarButton = new JButton("Editar Producto");
        eliminarButton = new JButton("Eliminar Producto");

        formPanel.add(agregarButton);
        formPanel.add(editarButton);

        // Panel de botones
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(eliminarButton);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // Acción: Buscar
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
                if (texto.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 0)); // columna 0: nombre
                }
            }
        });

        // Acción: Agregar producto
        agregarButton.addActionListener(e -> {
            String nombre = nombreField.getText();
            String precioStr = precioField.getText();
            String stockStr = stockField.getText();

            if (nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor llena todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                double precio = Double.parseDouble(precioStr);
                int stock = Integer.parseInt(stockStr);

                tableModel.addRow(new Object[]{nombre, precio, stock});
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio o stock inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción: Editar producto
        editarButton.addActionListener(e -> {
            int selectedRow = productosTable.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = productosTable.convertRowIndexToModel(selectedRow);
                String nombre = nombreField.getText();
                String precioStr = precioField.getText();
                String stockStr = stockField.getText();

                if (nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor llena todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    double precio = Double.parseDouble(precioStr);
                    int stock = Integer.parseInt(stockStr);

                    tableModel.setValueAt(nombre, modelRow, 0);
                    tableModel.setValueAt(precio, modelRow, 1);
                    tableModel.setValueAt(stock, modelRow, 2);
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Precio o stock inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un producto para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Acción: Eliminar producto
        eliminarButton.addActionListener(e -> {
            int selectedRow = productosTable.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = productosTable.convertRowIndexToModel(selectedRow);
                tableModel.removeRow(modelRow);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Acción: Llenar campos al seleccionar fila
        productosTable.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = productosTable.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = productosTable.convertRowIndexToModel(selectedRow);
                nombreField.setText(String.valueOf(tableModel.getValueAt(modelRow, 0)));
                precioField.setText(String.valueOf(tableModel.getValueAt(modelRow, 1)));
                stockField.setText(String.valueOf(tableModel.getValueAt(modelRow, 2)));
            }
        });
    }

    private void limpiarCampos() {
        nombreField.setText("");
        precioField.setText("");
        stockField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductosMantenimientoFrame().setVisible(true));
    }
}
