package com.sportstore.services;

import com.sportstore.db.ConexionDB;
import com.sportstore.models.CarritoItem;
import com.sportstore.models.Venta;

import java.sql.*;
import java.util.List;

public class VentaService {

    public int registrarVenta(Venta venta, List<CarritoItem> carrito) {
        String sqlVenta = "INSERT INTO ventas (id_cliente, id_empleado, total) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_ventas (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        String sqlUpdateInventario = "UPDATE inventario SET stock_actual = stock_actual - ? WHERE id_producto = ?";

        try (Connection conn = ConexionDB.getConnection()) {
            conn.setAutoCommit(false);

            // Insertar venta
            PreparedStatement psVenta = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            psVenta.setInt(1, venta.getIdCliente());
            psVenta.setInt(2, venta.getIdEmpleado());
            psVenta.setDouble(3, venta.getTotal());
            psVenta.executeUpdate();

            ResultSet rs = psVenta.getGeneratedKeys();
            if (rs.next()) {
                int idVenta = rs.getInt(1);

                // Insertar detalle
                for (CarritoItem item : carrito) {
                    PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle);
                    psDetalle.setInt(1, idVenta);
                    psDetalle.setInt(2, item.getProducto().getId());
                    psDetalle.setInt(3, item.getCantidad());
                    psDetalle.setDouble(4, item.getProducto().getPrecio());
                    psDetalle.setDouble(5, item.getSubtotal());
                    psDetalle.executeUpdate();

                    // Actualizar inventario
                    PreparedStatement psStock = conn.prepareStatement(sqlUpdateInventario);
                    psStock.setInt(1, item.getCantidad());
                    psStock.setInt(2, item.getProducto().getId());
                    psStock.executeUpdate();
                }

                conn.commit();
                return idVenta;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
