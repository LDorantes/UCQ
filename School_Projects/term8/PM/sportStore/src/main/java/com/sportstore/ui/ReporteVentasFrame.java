package com.sportstore.ui;

import com.sportstore.models.ReporteVenta;
import com.sportstore.services.ReporteService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReporteVentasFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public ReporteVentasFrame() {
        setTitle("Reporte de Ventas");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] columnas = {"Fecha", "Producto", "Cantidad", "Precio Unitario", "Total Parcial", "Total Final"};
        model = new DefaultTableModel(columnas, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        cargarDatos();
    }

    private void cargarDatos() {
        ReporteService service = new ReporteService();
        List<ReporteVenta> ventas = service.getReporteVentas();
        model.setRowCount(0);
        for (ReporteVenta v : ventas) {
            model.addRow(new Object[]{
                v.getFechaVenta(),
                v.getProducto(),
                v.getCantidad(),
                v.getPrecioUnitario(),
                v.getTotalParcial(),
                v.getTotalFinal()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReporteVentasFrame().setVisible(true));
    }
}
