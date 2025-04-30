package com.sportstore.ui;

import com.sportstore.models.Categoria;
import com.sportstore.services.CategoriaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class CategoriasMantenimientoFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nombreField, descripcionField, buscarField;
    private CategoriaService categoriaService;

    public CategoriasMantenimientoFrame() {
        setTitle("Gestión de Categorías");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        categoriaService = new CategoriaService();

        String[] columnas = {"ID", "Nombre", "Descripción"};
        tableModel = new DefaultTableModel(columnas, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        nombreField = new JTextField();
        descripcionField = new JTextField();
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Descripción:"));
        formPanel.add(descripcionField);

        JButton agregarBtn = new JButton("Agregar");
        JButton eliminarBtn = new JButton("Eliminar");
        formPanel.add(agregarBtn);
        formPanel.add(eliminarBtn);

        buscarField = new JTextField(20);
        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buscarPanel.add(new JLabel("Buscar:"));
        buscarPanel.add(buscarField);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        buscarField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
            private void filtrar() {
                String texto = buscarField.getText();
                sorter.setRowFilter(texto.trim().isEmpty() ? null : RowFilter.regexFilter("(?i)" + texto, 1));
            }
        });

        agregarBtn.addActionListener(e -> {
            String nombre = nombreField.getText();
            String descripcion = descripcionField.getText();
            if (!nombre.isEmpty()) {
                Categoria c = new Categoria(0, nombre, descripcion);
                if (categoriaService.addCategoria(c)) {
                    JOptionPane.showMessageDialog(this, "Categoría agregada correctamente.");
                    cargarDatos();
                    nombreField.setText("");
                    descripcionField.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        eliminarBtn.addActionListener(e -> {
            int fila = table.getSelectedRow();
            if (fila != -1) {
                int modelRow = table.convertRowIndexToModel(fila);
                int id = (int) tableModel.getValueAt(modelRow, 0);
                if (categoriaService.deleteCategoria(id)) {
                    JOptionPane.showMessageDialog(this, "Categoría eliminada correctamente.");
                    cargarDatos();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una fila para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        setLayout(new BorderLayout());
        add(buscarPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);

        cargarDatos();
    }

    private void cargarDatos() {
        tableModel.setRowCount(0);
        List<Categoria> categorias = categoriaService.getAllCategorias();
        for (Categoria c : categorias) {
            tableModel.addRow(new Object[]{c.getIdCategoria(), c.getNombre(), c.getDescripcion()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CategoriasMantenimientoFrame().setVisible(true));
    }
}
