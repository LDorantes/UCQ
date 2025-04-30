package com.sportstore.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.sportstore.services.ProductoService;
import com.sportstore.models.Producto;

public class ProductosFrame extends JFrame {
    public ProductosFrame() {
        setTitle("Productos - SportStore");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ProductoService productoService = new ProductoService();
        List<Producto> productos = productoService.getAllProductos();

        JPanel panel = new JPanel(new GridLayout(productos.size(), 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (Producto p : productos) {
            JPanel productoPanel = new JPanel();
            productoPanel.setLayout(new GridLayout(2, 4));

            productoPanel.add(new JLabel("Nombre: " + p.getNombre()));
            productoPanel.add(new JLabel("Precio: $" + p.getPrecio()));
            productoPanel.add(new JLabel("Stock: " + p.getStock()));
            productoPanel.add(new JLabel("Categoría: " + p.getIdCategoria()));
            productoPanel.add(new JLabel("Descripción: " + p.getDescripcion()));
            productoPanel.add(new JLabel("Proveedor: " + p.getIdProveedor()));
            productoPanel.add(new JLabel("Imagen: " + p.getImagenUrl()));

            panel.add(productoPanel);
        }

        add(new JScrollPane(panel), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductosFrame().setVisible(true));
    }
}
