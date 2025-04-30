package com.sportstore.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class MainMenuFrame extends JFrame {
    public MainMenuFrame() {
        setTitle("SportStore - Menú Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Menú Principal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(6, 2, 15, 15)); // 6 filas, 2 columnas
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear botones
        JButton categoriasButton = createStyledButton("Categorías");
        JButton productosButton = createStyledButton("Productos");
        JButton ventasButton = createStyledButton("Ventas");
        JButton clientesButton = createStyledButton("Clientes");
        JButton empleadosButton = createStyledButton("Empleados");
        JButton proveedoresButton = createStyledButton("Proveedores");
        JButton inventarioButton = createStyledButton("Inventario");
        JButton reportesButton = createStyledButton("Reportes");
        JButton mantenimientosButton = createStyledButton("Mantenimientos");
        JButton carritoButton = createStyledButton("Carrito");
        JButton cerrarSesionButton = createStyledButton("Cerrar Sesión");

        // Agregar botones al panel
        buttonPanel.add(categoriasButton);
        buttonPanel.add(productosButton);
        buttonPanel.add(ventasButton);
        buttonPanel.add(clientesButton);
        buttonPanel.add(empleadosButton);
        buttonPanel.add(proveedoresButton);
        buttonPanel.add(inventarioButton);
        buttonPanel.add(reportesButton);
        buttonPanel.add(mantenimientosButton);
        buttonPanel.add(carritoButton);
        buttonPanel.add(cerrarSesionButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);

        // Acciones de los botones
        categoriasButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Accediendo a Categorías");
            new CategoriasMantenimientoFrame().setVisible(true);
        });

        productosButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Accediendo a Productos");
            new ProductosMantenimientoFrame().setVisible(true); // ← Ahora abre ProductosMantenimientoFrame
        });

        ventasButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Accediendo a Ventas");
            new VentasMantenimientoFrame().setVisible(true);
        });

        clientesButton.addActionListener(e -> {JOptionPane.showMessageDialog(this, "Accediendo a Clientes");
        new ClientesMantenimientoFrame().setVisible(true);
        });

        empleadosButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Accediendo a Empleados"));

        proveedoresButton.addActionListener(e ->{
            JOptionPane.showMessageDialog(this, "Accediendo a Proveedores");
            new ProveedoresFrame().setVisible(true);
        } 
        );

        inventarioButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Accediendo a Inventario");
            new InventarioMantenimientoFrame().setVisible(true);
        });

        reportesButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Accediendo a Reportes");
            new ReporteVentasFrame().setVisible(true);
        });

        mantenimientosButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Accediendo a Mantenimientos");
            new UsuariosMantenimientoFrame().setVisible(true);
        });

        carritoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Accediendo al Carrito");
            new CarritoFrame().setVisible(true);
        });

        cerrarSesionButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Cerrando Sesión");
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenuFrame frame = new MainMenuFrame();
            frame.setVisible(true);
        });
    }
}
