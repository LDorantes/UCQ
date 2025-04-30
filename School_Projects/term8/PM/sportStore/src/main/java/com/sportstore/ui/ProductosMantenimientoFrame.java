package com.sportstore.ui;

import com.sportstore.models.Producto;
import com.sportstore.services.ProductoService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class ProductosMantenimientoFrame extends JFrame {
    private JTable productosTable;
    private DefaultTableModel tableModel;
    private JTextField nombreField, precioField, stockField, buscarField;
    private JButton agregarButton, editarButton, eliminarButton;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private ProductoService productoService = new ProductoService();

    public ProductosMantenimientoFrame() {
        setTitle("Gestión de Productos");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnas = {"ID", "Nombre", "Precio", "Stock"};
        tableModel = new DefaultTableModel(columnas, 0);
        productosTable = new JTable(tableModel);
        rowSorter = new TableRowSorter<>(tableModel);
        productosTable.setRowSorter(rowSorter);

        JScrollPane scrollPane = new JScrollPane(productosTable);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buscarField = new JTextField(20);
        searchPanel.add(new JLabel("Buscar producto:"));
        searchPanel.add(buscarField);

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

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(eliminarButton);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        buscarField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrar(); }
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            public void changedUpdate(DocumentEvent e) { filtrar(); }

            private void filtrar() {
                String texto = buscarField.getText();
                rowSorter.setRowFilter(texto.trim().isEmpty() ? null : RowFilter.regexFilter("(?i)" + texto, 1));
            }
        });

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

                Producto producto = new Producto(nombre, "", precio, stock, 1, 1, "");
                if (productoService.addProducto(producto)) {
                    JOptionPane.showMessageDialog(this, "Producto agregado correctamente.");
                    dispose(); // Cierra esta ventana
                    new MainMenuFrame().setVisible(true); // Regresa al menú
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar producto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio o stock inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        editarButton.addActionListener(e -> {
            int selectedRow = productosTable.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = productosTable.convertRowIndexToModel(selectedRow);
                int id = (int) tableModel.getValueAt(modelRow, 0);

                try {
                    String nombre = nombreField.getText();
                    double precio = Double.parseDouble(precioField.getText());
                    int stock = Integer.parseInt(stockField.getText());

                    Producto producto = new Producto(id, nombre, "", precio, stock, 1, 1, "");
                    if (productoService.updateProducto(producto)) {
                        JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
                        cargarProductos();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al actualizar producto.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Datos inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        eliminarButton.addActionListener(e -> {
            int selectedRow = productosTable.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = productosTable.convertRowIndexToModel(selectedRow);
                int id = (int) tableModel.getValueAt(modelRow, 0);

                if (productoService.deleteProducto(id)) {
                    JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
                    cargarProductos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar producto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        productosTable.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = productosTable.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = productosTable.convertRowIndexToModel(selectedRow);
                nombreField.setText(tableModel.getValueAt(modelRow, 1).toString());
                precioField.setText(tableModel.getValueAt(modelRow, 2).toString());
                stockField.setText(tableModel.getValueAt(modelRow, 3).toString());
            }
        });

        cargarProductos();
    }

    private void cargarProductos() {
        tableModel.setRowCount(0);
        for (Producto p : productoService.getAllProductos()) {
            tableModel.addRow(new Object[]{p.getId(), p.getNombre(), p.getPrecio(), p.getStock()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductosMantenimientoFrame().setVisible(true));
    }
}
