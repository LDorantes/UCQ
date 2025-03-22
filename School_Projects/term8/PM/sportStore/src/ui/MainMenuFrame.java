package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame {
    public MainMenuFrame() {
        setTitle("SportStore - Menú Principal");
        setSize(800, 600); // Tamaño más grande para mejor visualización
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen exterior

        // Título del menú
        JLabel titleLabel = new JLabel("Menú Principal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Fuente más grande y en negrita
        titleLabel.setForeground(new Color(0, 102, 204)); // Color azul
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 15, 15)); // 4 filas, 2 columnas, con espacio entre botones
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen interior

        // Crear botones con estilo
        JButton categoriasButton = createStyledButton("Categorías");
        JButton productosButton = createStyledButton("Productos");
        JButton ventasButton = createStyledButton("Ventas");
        JButton clientesButton = createStyledButton("Clientes");
        JButton empleadosButton = createStyledButton("Empleados");
        JButton proveedoresButton = createStyledButton("Proveedores");
        JButton inventarioButton = createStyledButton("Inventario");
        JButton cerrarSesionButton = createStyledButton("Cerrar Sesión");

        // Agregar botones al panel
        buttonPanel.add(categoriasButton);
        buttonPanel.add(productosButton);
        buttonPanel.add(ventasButton);
        buttonPanel.add(clientesButton);
        buttonPanel.add(empleadosButton);
        buttonPanel.add(proveedoresButton);
        buttonPanel.add(inventarioButton);
        buttonPanel.add(cerrarSesionButton);

        // Agregar el panel de botones al panel principal
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Agregar el panel principal a la ventana
        add(mainPanel);

        // Acciones para los botones
        categoriasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainMenuFrame.this, "Accediendo a Categorías");
                // Aquí puedes abrir la interfaz de categorías
            }
        });

        productosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainMenuFrame.this, "Accediendo a Productos");
                // Aquí puedes abrir la interfaz de productos
            }
        });

        ventasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainMenuFrame.this, "Accediendo a Ventas");
                // Aquí puedes abrir la interfaz de ventas
            }
        });

        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainMenuFrame.this, "Accediendo a Clientes");
                // Aquí puedes abrir la interfaz de clientes
            }
        });

        empleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainMenuFrame.this, "Accediendo a Empleados");
                // Aquí puedes abrir la interfaz de empleados
            }
        });

        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainMenuFrame.this, "Accediendo a Proveedores");
                // Aquí puedes abrir la interfaz de proveedores
            }
        });

        inventarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainMenuFrame.this, "Accediendo a Inventario");
                // Aquí puedes abrir la interfaz de inventario
            }
        });

        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainMenuFrame.this, "Cerrando Sesión");
                dispose(); // Cierra la ventana del menú principal
                new LoginFrame().setVisible(true); // Muestra la ventana de login
            }
        });
    }

    // Método para crear botones con estilo
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente más grande y en negrita
        button.setBackground(new Color(0, 102, 204)); // Fondo azul
        button.setForeground(Color.WHITE); // Texto blanco
        button.setFocusPainted(false); // Eliminar el borde de enfoque
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Margen interior
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar el cursor al pasar sobre el botón
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenuFrame().setVisible(true);
            }
        });
    }
}