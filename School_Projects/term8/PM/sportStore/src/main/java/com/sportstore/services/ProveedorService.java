package com.sportstore.services;

import com.sportstore.db.ConexionDB;
import com.sportstore.models.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorService {

    public List<Proveedor> getAllProveedores() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Proveedor p = new Proveedor(
                    rs.getInt("id_proveedor"),
                    rs.getString("nombre"),
                    rs.getString("contacto"),
                    rs.getString("telefono")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean addProveedor(Proveedor proveedor) {
        String sql = "INSERT INTO proveedores (nombre, contacto, telefono) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getContacto());
            stmt.setString(3, proveedor.getTelefono());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
