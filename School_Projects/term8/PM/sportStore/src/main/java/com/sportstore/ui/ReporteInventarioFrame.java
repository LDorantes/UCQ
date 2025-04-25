package com.sportstore.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ReporteInventarioFrame extends JFrame {
    private JTable inventarioTable;
    private DefaultTableModel tableModel;

    public ReporteInventarioFrame() {
        setTitle("Reporte de Inventario");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnas = {"Producto", "Cantidad Disponible"};

        tableModel = new DefaultTableModel(columnas, 0);
        inventarioTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(inventarioTable);

        add(scrollPane, BorderLayout.CENTER);

        // Datos de ejemplo
        agregarProducto("Balón de fútbol", 10);
        agregarProducto("Raqueta de tenis", 5);
        agregarProducto("Guantes de boxeo", 12);
    }

    private void agregarProducto(String producto, int cantidad) {
        tableModel.addRow(new Object[]{producto, cantidad});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReporteInventarioFrame().setVisible(true));
    }
}
