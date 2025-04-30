package com.sportstore.ui;

import com.sportstore.models.Inventario;
import com.sportstore.services.InventarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class InventarioMantenimientoFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField buscarField;
    private InventarioService service;

    public InventarioMantenimientoFrame() {
        setTitle("Inventario - Mantenimiento");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        service = new InventarioService();

        String[] columnas = {"ID Inventario", "ID Producto", "Stock Inicial", "Stock Actual", "Fecha"};
        model = new DefaultTableModel(columnas, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        // --- Panel superior con búsqueda ---
        JPanel topPanel = new JPanel(new BorderLayout());
        buscarField = new JTextField();
        topPanel.add(new JLabel("Buscar ID Producto:"), BorderLayout.WEST);
        topPanel.add(buscarField, BorderLayout.CENTER);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        buscarField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }

            private void filtrar() {
                String texto = buscarField.getText();
                sorter.setRowFilter(texto.trim().isEmpty() ? null : RowFilter.regexFilter("(?i)" + texto, 1)); // columna 1 = ID Producto
            }
        });

        // --- Panel inferior con botones ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton agregarBtn = new JButton("Agregar Inventario");
        JButton eliminarBtn = new JButton("Eliminar Seleccionado");
        bottomPanel.add(agregarBtn);
        bottomPanel.add(eliminarBtn);

        agregarBtn.addActionListener(e -> {
            try {
                String idProductoStr = JOptionPane.showInputDialog(this, "ID Producto:");
                String stockInicialStr = JOptionPane.showInputDialog(this, "Stock Inicial:");
                int idProducto = Integer.parseInt(idProductoStr);
                int stockInicial = Integer.parseInt(stockInicialStr);

                boolean exito = service.addInventario(idProducto, stockInicial);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Inventario agregado exitosamente.");
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar inventario.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos.");
            }
        });

        eliminarBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int idInventario = (int) model.getValueAt(selectedRow, 0);
                if (service.deleteInventario(idInventario)) {
                    JOptionPane.showMessageDialog(this, "Inventario eliminado exitosamente.");
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar inventario.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un inventario para eliminar.");
            }
        });

        // Layout
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        cargarDatos();
    }

    private void cargarDatos() {
        List<Inventario> inventarios = service.getAllInventario();
        model.setRowCount(0);
        for (Inventario inv : inventarios) {
            model.addRow(new Object[]{
                    inv.getIdInventario(),
                    inv.getIdProducto(),
                    inv.getStockInicial(),
                    inv.getStockActual(),
                    inv.getFechaActualizacion()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventarioMantenimientoFrame().setVisible(true));
    }
}
