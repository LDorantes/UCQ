package com.sportstore.ui;

import com.sportstore.models.*;
import com.sportstore.services.VentaService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoFrame extends JFrame {
    private List<CarritoItem> carrito = new ArrayList<>();
    private JTable carritoTable;
    private JLabel totalLabel;
    private DefaultTableModel model;

    public CarritoFrame() {
        setTitle("Carrito");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] columnas = {"Producto", "Cantidad", "Precio Unitario", "Subtotal"};
        model = new DefaultTableModel(columnas, 0);
        carritoTable = new JTable(model);
        add(new JScrollPane(carritoTable), BorderLayout.CENTER);

        totalLabel = new JLabel("Total: $0.00");
        JButton finalizarBtn = new JButton("Finalizar Compra");

        finalizarBtn.addActionListener(e -> finalizarCompra());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(totalLabel, BorderLayout.WEST);
        bottomPanel.add(finalizarBtn, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        actualizarTabla();
    }

    public void agregarProducto(Producto p, int cantidad) {
        carrito.add(new CarritoItem(p, cantidad));
        actualizarTabla();
    }

    private void actualizarTabla() {
        model.setRowCount(0);
        double total = 0;
        for (CarritoItem item : carrito) {
            model.addRow(new Object[]{
                item.getProducto().getNombre(),
                item.getCantidad(),
                item.getProducto().getPrecio(),
                item.getSubtotal()
            });
            total += item.getSubtotal();
        }
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }

    private void finalizarCompra() {
        Venta venta = new Venta();
        venta.setIdCliente(1); // Simulado
        venta.setIdEmpleado(1); // Simulado
        venta.setTotal(carrito.stream().mapToDouble(CarritoItem::getSubtotal).sum());

        VentaService service = new VentaService();
        int idVenta = service.registrarVenta(venta, carrito);

        if (idVenta != -1) {
            JOptionPane.showMessageDialog(this, "Venta realizada con éxito. ID: " + idVenta);
            carrito.clear();
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Error al realizar la venta.");
        }
    }
}
