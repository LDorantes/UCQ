package com.sportstore.services;

import com.sportstore.db.ConexionDB;
import com.sportstore.models.Carrito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoService {

    public List<Carrito> obtenerCarritoPorUsuario(int idUsuario) {
        List<Carrito> lista = new ArrayList<>();
        String sql = "SELECT * FROM carrito WHERE id_usuario = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Carrito c = new Carrito(
                        rs.getInt("id_carrito"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_producto"),
                        rs.getInt("cantidad")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean agregarAlCarrito(Carrito carrito) {
        String sql = "INSERT INTO carrito (id_usuario, id_producto, cantidad) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, carrito.getIdUsuario());
            stmt.setInt(2, carrito.getIdProducto());
            stmt.setInt(3, carrito.getCantidad());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminarDelCarrito(int idCarrito) {
        String sql = "DELETE FROM carrito WHERE id_carrito = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCarrito);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
