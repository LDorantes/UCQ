package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CarritoFrame extends JFrame {
    private JTable carritoTable;
    private DefaultTableModel tableModel;
    private JButton agregarButton, eliminarButton, pagarButton;

    public CarritoFrame() {
        setTitle("Carrito de Compras");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnas = {"Producto", "Cantidad", "Precio Unitario", "Subtotal"};
        tableModel = new DefaultTableModel(columnas, 0);
        carritoTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(carritoTable);

        agregarButton = new JButton("Agregar producto");
        eliminarButton = new JButton("Eliminar producto");
        pagarButton = new JButton("Solicitar Pago");

        JPanel botonesPanel = new JPanel();
        botonesPanel.add(agregarButton);
        botonesPanel.add(eliminarButton);
        botonesPanel.add(pagarButton);

        add(scrollPane, BorderLayout.CENTER);
        add(botonesPanel, BorderLayout.SOUTH);

        // Acción: Agregar producto (simulado)
        agregarButton.addActionListener((ActionEvent e) -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del producto:");
            if (nombre == null || nombre.isEmpty()) return;

            String cantidadStr = JOptionPane.showInputDialog(this, "Cantidad:");
            String precioStr = JOptionPane.showInputDialog(this, "Precio unitario:");

            try {
                int cantidad = Integer.parseInt(cantidadStr);
                double precio = Double.parseDouble(precioStr);
                double subtotal = cantidad * precio;

                tableModel.addRow(new Object[]{nombre, cantidad, precio, subtotal});
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad o precio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción: Eliminar producto seleccionado
        eliminarButton.addActionListener((ActionEvent e) -> {
            int selectedRow = carritoTable.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Acción: Calcular total y mostrar
        pagarButton.addActionListener((ActionEvent e) -> {
            double total = 0;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                total += (double) tableModel.getValueAt(i, 3); // Subtotal
            }
            JOptionPane.showMessageDialog(this, "Total a pagar: $" + total, "Pago", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CarritoFrame().setVisible(true));
    }
}
