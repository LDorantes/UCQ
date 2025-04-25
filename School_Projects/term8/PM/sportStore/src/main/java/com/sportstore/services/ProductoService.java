package com.sportstore.services;

import com.sportstore.db.ConexionDB;
import com.sportstore.models.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductoService {

    // Método para obtener todos los productos
    public List<Producto> getAllProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos"; // Ajusta el nombre de la tabla y las columnas

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id"),  // Asumiendo que 'id' es un entero
                        rs.getString("nombre"),  // Asumiendo que 'nombre' es un String
                        rs.getDouble("precio"),  // Asumiendo que 'precio' es un decimal
                        rs.getString("imagen_url")  // Asumiendo que 'imagen_url' es un String con la URL de la imagen
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // Método para obtener un producto por su ID
    public Producto getProductoById(int id) {
        Producto producto = null;
        String sql = "SELECT * FROM productos WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                producto = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getString("imagen_url")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    // Método para agregar un producto
    public boolean addProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, precio, imagen_url) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecio());
            pstmt.setString(3, producto.getImagenUrl());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para eliminar un producto por ID
    public boolean deleteProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
