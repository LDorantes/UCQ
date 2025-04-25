package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReporteVentasFrame extends JFrame {
    private JTable ventasTable;
    private DefaultTableModel tableModel;
    private JLabel totalFinalLabel;

    public ReporteVentasFrame() {
        setTitle("Reporte de Ventas");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnas = {"Fecha", "Producto", "Total"};

        tableModel = new DefaultTableModel(columnas, 0);
        ventasTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(ventasTable);

        totalFinalLabel = new JLabel("Total Final: $0.00");
        totalFinalLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton calcularTotalButton = new JButton("Calcular Total Final");
        calcularTotalButton.addActionListener(e -> calcularTotalFinal());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(totalFinalLabel, BorderLayout.WEST);
        bottomPanel.add(calcularTotalButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Datos de ejemplo
        agregarVenta("2025-04-25", "Balón de fútbol", 500.0);
        agregarVenta("2025-04-25", "Raqueta de tenis", 800.0);
        agregarVenta("2025-04-25", "Guantes de boxeo", 350.0);
    }

    private void agregarVenta(String fecha, String producto, double total) {
        tableModel.addRow(new Object[]{fecha, producto, total});
    }

    private void calcularTotalFinal() {
        double totalFinal = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            totalFinal += (double) tableModel.getValueAt(i, 2);
        }
        totalFinalLabel.setText("Total Final: $" + String.format("%.2f", totalFinal));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReporteVentasFrame().setVisible(true));
    }
}
