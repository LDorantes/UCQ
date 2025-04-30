package com.sportstore.services;

import com.sportstore.db.ConexionDB;
import com.sportstore.models.ReporteVenta;
import com.sportstore.models.ReporteInventario;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReporteService {

    public List<ReporteVenta> getReporteVentas() {
        List<ReporteVenta> lista = new ArrayList<>();

        String sql = "SELECT v.fecha_venta, p.nombre AS producto, dv.cantidad, dv.precio_unitario, " +
                     "(dv.cantidad * dv.precio_unitario) AS total_parcial, v.total AS total_final " +
                     "FROM ventas v " +
                     "JOIN detalle_ventas dv ON v.id_venta = dv.id_venta " +
                     "JOIN productos p ON dv.id_producto = p.id_producto " +
                     "ORDER BY v.fecha_venta DESC";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("fecha_venta");
                LocalDateTime fecha = ts != null ? ts.toLocalDateTime() : null;

                ReporteVenta rv = new ReporteVenta(
                        fecha,
                        rs.getString("producto"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("total_parcial"),
                        rs.getDouble("total_final")
                );
                lista.add(rv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<ReporteInventario> getReporteInventario() {
        List<ReporteInventario> lista = new ArrayList<>();

        String sql = "SELECT p.id_producto, p.nombre, p.descripcion, i.stock_actual, i.fecha_actualizacion " +
                     "FROM productos p " +
                     "JOIN inventario i ON p.id_producto = i.id_producto " +
                     "ORDER BY p.nombre";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("fecha_actualizacion");
                LocalDateTime fecha = ts != null ? ts.toLocalDateTime() : null;

                ReporteInventario ri = new ReporteInventario(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("stock_actual"),
                        fecha
                );
                lista.add(ri);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
