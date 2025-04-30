package com.sportstore.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sportstore.db.ConexionDB;
import com.sportstore.models.Cliente;

public class ClienteService {

    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getTimestamp("fecha_registro") != null ? rs.getTimestamp("fecha_registro").toLocalDateTime() : null

                );
                clientes.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public boolean addCliente(Cliente c) {
        String sql = "INSERT INTO clientes (nombre, apellido, email, telefono, direccion) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getApellido());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getTelefono());
            stmt.setString(5, c.getDireccion());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateCliente(Cliente c) {
        String sql = "UPDATE clientes SET nombre=?, apellido=?, email=?, telefono=?, direccion=? WHERE id_cliente = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getApellido());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getTelefono());
            stmt.setString(5, c.getDireccion());
            stmt.setInt(6, c.getIdCliente());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
