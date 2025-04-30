package com.sportstore.models;

import java.time.LocalDateTime;

public class ReporteVenta {
    private LocalDateTime fechaVenta;
    private String producto;
    private int cantidad;
    private double precioUnitario;
    private double totalParcial;
    private double totalFinal;

    public ReporteVenta(LocalDateTime fechaVenta, String producto, int cantidad, double precioUnitario, double totalParcial, double totalFinal) {
        this.fechaVenta = fechaVenta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.totalParcial = totalParcial;
        this.totalFinal = totalFinal;
    }

    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public String getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getTotalParcial() { return totalParcial; }
    public double getTotalFinal() { return totalFinal; }
}
