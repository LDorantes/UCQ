package com.sportstore.ui;

import com.sportstore.models.Carrito;
import com.sportstore.services.CarritoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CarritoMantenimientoFrame extends JFrame {
    private JTable carritoTable;
    private DefaultTableModel tableModel;
    private CarritoService carritoService;

    public CarritoMantenimientoFrame(int idUsuario) {
        setTitle("Carrito de Compras");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        carritoService = new CarritoService();
        String[] columnas = {"ID Carrito", "ID Producto", "Cantidad"};
        tableModel = new DefaultTableModel(columnas, 0);
        carritoTable = new JTable(tableModel);
        add(new JScrollPane(carritoTable), BorderLayout.CENTER);

        cargarCarrito(idUsuario);
    }

    private void cargarCarrito(int idUsuario) {
        tableModel.setRowCount(0);
        List<Carrito> items = carritoService.obtenerCarritoPorUsuario(idUsuario);
        for (Carrito c : items) {
            tableModel.addRow(new Object[]{
                c.getIdCarrito(),
                c.getIdProducto(),
                c.getCantidad()
            });
        }
    }
}
