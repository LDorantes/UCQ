package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentasFrame extends JFrame {

    private JTable table;

    public VentasFrame() {
        setTitle("Ventas - SportStore");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Descripción", "Imagen", "Disponibles"};

        Object[][] data = {
                {"Balón de fútbol", escalarImagen(new ImageIcon("src/images/balon.png"), 80, 80), 10},
                {"Raqueta de tenis", escalarImagen(new ImageIcon("src/images/raqueta.png"), 80, 80), 5},
                {"Guantes de boxeo", escalarImagen(new ImageIcon("src/images/guantes.png"), 80, 80), 12}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 1) ? Icon.class : Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(90); // Altura de fila para mostrar la imagen correctamente

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private ImageIcon escalarImagen(ImageIcon iconoOriginal, int ancho, int alto) {
        Image imagen = iconoOriginal.getImage();
        Image imagenEscalada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentasFrame().setVisible(true));
    }
}
