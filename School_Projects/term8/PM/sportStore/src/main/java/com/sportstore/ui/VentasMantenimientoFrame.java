package com.sportstore.ui;

import com.sportstore.models.Venta;
import com.sportstore.services.VentaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentasMantenimientoFrame extends JFrame {
    private JTable ventasTable;
    private DefaultTableModel tableModel;

    public VentasMantenimientoFrame() {
        setTitle("Gestión de Ventas");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnas = {"ID Venta", "ID Cliente", "ID Empleado", "Fecha Venta", "Total"};
        tableModel = new DefaultTableModel(columnas, 0);
        ventasTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(ventasTable);
        add(scrollPane, BorderLayout.CENTER);

        cargarVentas();
    }

    private void cargarVentas() {
        VentaService ventaService = new VentaService();
        List<Venta> ventas = ventaService.getAllVentas();
        for (Venta v : ventas) {
            tableModel.addRow(new Object[]{
                    v.getIdVenta(),
                    v.getIdCliente(),
                    v.getIdEmpleado(),
                    v.getFechaVenta(),
                    v.getTotal()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentasMantenimientoFrame().setVisible(true));
    }
}
