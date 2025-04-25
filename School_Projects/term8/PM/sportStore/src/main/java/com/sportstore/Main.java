package com.sportstore;

import com.sportstore.models.Producto;
import com.sportstore.services.ProductoService;
import com.sportstore.ui.LoginFrame;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // Iniciar la interfaz gráfica de login
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Mostrar la ventana de login
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);

                // Esperar a que el usuario inicie sesión y luego cargar los productos
                // Este paso debería ocurrir después de la autenticación exitosa
                // Puedes poner una lógica para verificar la autenticación si es necesario

                // Obtener productos una vez que el login esté listo
                ProductoService productoService = new ProductoService();

                // Obtener todos los productos
                List<Producto> productos = productoService.getAllProductos();
                for (Producto producto : productos) {
                    System.out.println("Nombre: " + producto.getNombre());
                    System.out.println("Precio: " + producto.getPrecio());
                    System.out.println("Imagen URL: " + producto.getImagenUrl());
                }

                // Obtener un producto por su ID
                Producto producto = productoService.getProductoById(1);
                if (producto != null) {
                    System.out.println("Producto encontrado: " + producto.getNombre());
                }
            }
        });
    }
}
