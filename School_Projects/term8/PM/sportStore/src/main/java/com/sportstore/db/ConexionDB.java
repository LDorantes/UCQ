package com.sportstore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/sport_store_ucq";
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 
    private static Connection conn = null;

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Error en la conexión: " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("✅ Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("❌ Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}


