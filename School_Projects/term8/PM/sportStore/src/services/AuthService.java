package services;

import db.ConexionDB;
import models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthService {
    public static boolean validarLogin(Usuario usuario) {
        try (Connection conn = ConexionDB.conectar()) {
            if (conn == null) return false;

            String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());

            ResultSet rs = stmt.executeQuery();
            boolean loginExitoso = rs.next();

            rs.close();
            stmt.close();
            return loginExitoso;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
