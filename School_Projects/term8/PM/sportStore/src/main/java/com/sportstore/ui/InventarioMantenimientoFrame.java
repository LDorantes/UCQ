package com.sportstore.ui;

import com.sportstore.models.Inventario;
import com.sportstore.services.InventarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InventarioMantenimientoFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public InventarioMantenimientoFrame() {
        setTitle("Inventario - Mantenimiento");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] columnas = {"ID Inventario", "ID Producto", "Stock Inicial", "Stock Actual", "Fecha"};
        model = new DefaultTableModel(columnas, 0);
        table = new JTable(model);

        cargarDatos();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void cargarDatos() {
        InventarioService service = new InventarioService();
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
