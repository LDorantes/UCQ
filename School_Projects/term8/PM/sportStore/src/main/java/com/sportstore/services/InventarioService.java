package com.sportstore.services;

import com.sportstore.db.ConexionDB;
import com.sportstore.models.Inventario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioService {

    public List<Inventario> getAllInventario() {
        List<Inventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Inventario inv = new Inventario(
                    rs.getInt("id_inventario"),
                    rs.getInt("id_producto"),
                    rs.getInt("stock_inicial"),
                    rs.getInt("stock_actual"),
                    rs.getTimestamp("fecha_actualizacion")
                );
                lista.add(inv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean addInventario(Inventario inv) {
        String sql = "INSERT INTO inventario (id_producto, stock_inicial, stock_actual) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, inv.getIdProducto());
            stmt.setInt(2, inv.getStockInicial());
            stmt.setInt(3, inv.getStockActual());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateInventario(Inventario inv) {
        String sql = "UPDATE inventario SET stock_inicial = ?, stock_actual = ? WHERE id_inventario = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, inv.getStockInicial());
            stmt.setInt(2, inv.getStockActual());
            stmt.setInt(3, inv.getIdInventario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteInventario(int idInventario) {
        String sql = "DELETE FROM inventario WHERE id_inventario = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idInventario);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
