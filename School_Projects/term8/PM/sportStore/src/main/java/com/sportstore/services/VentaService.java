package com.sportstore.services;

import com.sportstore.db.ConexionDB;
import com.sportstore.models.Venta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaService {

    public List<Venta> getAllVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Venta venta = new Venta(
                        rs.getInt("id_venta"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_empleado"),
                        rs.getTimestamp("fecha_venta"),
                        rs.getDouble("total")
                );
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    public boolean addVenta(Venta venta) {
        String sql = "INSERT INTO ventas (id_cliente, id_empleado, total) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, venta.getIdCliente());
            pstmt.setInt(2, venta.getIdEmpleado());
            pstmt.setDouble(3, venta.getTotal());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteVenta(int idVenta) {
        String sql = "DELETE FROM ventas WHERE id_venta = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idVenta);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
