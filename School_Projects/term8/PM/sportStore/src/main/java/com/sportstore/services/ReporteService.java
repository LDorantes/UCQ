public List<ReporteVenta> getReporteVentas() {
    List<ReporteVenta> lista = new ArrayList<>();
    String sql = """
        SELECT v.fecha_venta, p.nombre AS producto, dv.cantidad, dv.precio_unitario,
               (dv.cantidad * dv.precio_unitario) AS total_parcial, v.total AS total_final
        FROM ventas v
        JOIN detalle_ventas dv ON v.id_venta = dv.id_venta
        JOIN productos p ON dv.id_producto = p.id_producto
        ORDER BY v.fecha_venta DESC
        """;

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
