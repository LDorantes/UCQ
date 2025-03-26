package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import services.ProductoService;
import models.Producto;

public class ProductosFrame extends JFrame {

    public ProductosFrame() {
        setTitle("Productos - SportStore");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el servicio de productos
        ProductoService productoService = new ProductoService();

        // Obtener la lista de productos desde la base de datos
        List<Producto> productos = productoService.getAllProductos();

        // Crear un panel con GridLayout para mostrar los productos
        JPanel productosPanel = new JPanel(new GridLayout(productos.size(), 1, 10, 10));
        productosPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (Producto producto : productos) {
            // Crear un panel para cada producto
            JPanel productoPanel = new JPanel();
            productoPanel.setLayout(new BoxLayout(productoPanel, BoxLayout.Y_AXIS));

            // Imagen del producto
            JLabel imageLabel = new JLabel(producto.getImagen());
            productoPanel.add(imageLabel);

            // Nombre del producto
            JLabel nameLabel = new JLabel(producto.getNombre(), SwingConstants.CENTER);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            productoPanel.add(nameLabel);

            // Precio del producto
            JLabel priceLabel = new JLabel("$" + producto.getPrecio(), SwingConstants.CENTER);
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            productoPanel.add(priceLabel);

            productosPanel.add(productoPanel);
        }

        // Agregar el panel de productos a la ventana
        add(new JScrollPane(productosPanel), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProductosFrame().setVisible(true);
            }
        });
    }
    
    
}
