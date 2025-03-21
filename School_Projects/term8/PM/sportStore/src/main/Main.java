package main; // Asegúrate de que coincide con la estructura de carpetas

import db.ConexionDB;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = ConexionDB.getConnection();
        if (conn != null) {
            System.out.println("🎉 Conexión realizada correctamente.");
        } else {
            System.out.println("⚠️ No se pudo conectar.");
        }
    }
}
