// Modelo: DetalleVenta.java
package com.sportstore.models;

public class DetalleVenta {
    private int idDetalle;
    private int idVenta;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public DetalleVenta(int idDetalle, int idVenta, int idProducto, int cantidad, double precioUnitario, double subtotal) {
        this.idDetalle = idDetalle;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public DetalleVenta(int idVenta, int idProducto, int cantidad, double precioUnitario) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = cantidad * precioUnitario;
    }

    public int getIdDetalle() { return idDetalle; }
    public int getIdVenta() { return idVenta; }
    public int getIdProducto() { return idProducto; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getSubtotal() { return subtotal; }

    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}

// Servicio: DetalleVentaService.java
package com.sportstore.services;

import com.sportstore.db.ConexionDB;
import com.sportstore.models.DetalleVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DetalleVentaService {
    public boolean agregarDetallesVenta(List<DetalleVenta> detalles) {
        String sqlDetalle = "INSERT INTO detalle_ventas (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        String sqlInventario = "UPDATE inventario SET stock_actual = stock_actual - ? WHERE id_producto = ?";

        try (Connection conn = ConexionDB.getConnection()) {
            conn.setAutoCommit(false);

            try (
                PreparedStatement pstmtDetalle = conn.prepareStatement(sqlDetalle);
                PreparedStatement pstmtInventario = conn.prepareStatement(sqlInventario)
            ) {
                for (DetalleVenta d : detalles) {
                    pstmtDetalle.setInt(1, d.getIdVenta());
                    pstmtDetalle.setInt(2, d.getIdProducto());
                    pstmtDetalle.setInt(3, d.getCantidad());
                    pstmtDetalle.setDouble(4, d.getPrecioUnitario());
                    pstmtDetalle.setDouble(5, d.getSubtotal());
                    pstmtDetalle.executeUpdate();

                    pstmtInventario.setInt(1, d.getCantidad());
                    pstmtInventario.setInt(2, d.getIdProducto());
                    pstmtInventario.executeUpdate();
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
