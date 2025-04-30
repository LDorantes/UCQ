package com.sportstore.services;

import com.sportstore.db.ConexionDB;
import com.sportstore.models.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    public List<Producto> getAllProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getInt("id_categoria"),
                    rs.getInt("id_proveedor"),
                    rs.getString("imagen_url")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public boolean addProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";
    
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecio());
            pstmt.setInt(3, producto.getStock());
    
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Producto getProductoById(int id) {
        String sql = "SELECT * FROM productos WHERE id_producto = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getInt("id_categoria"),
                    rs.getInt("id_proveedor"),
                    rs.getString("imagen_url")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=?, id_categoria=?, id_proveedor=?, imagen_url=? WHERE id_producto=?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setDouble(3, producto.getPrecio());
            pstmt.setInt(4, producto.getStock());
            pstmt.setInt(5, producto.getIdCategoria());
            pstmt.setInt(6, producto.getIdProveedor());
            pstmt.setString(7, producto.getImagenUrl());
            pstmt.setInt(8, producto.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProducto(int id) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
