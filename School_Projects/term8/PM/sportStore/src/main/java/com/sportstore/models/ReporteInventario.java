package com.sportstore.models;

import java.time.LocalDateTime;

public class ReporteInventario {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private int stockActual;
    private LocalDateTime fechaActualizacion;

    public ReporteInventario(int idProducto, String nombre, String descripcion, int stockActual, LocalDateTime fechaActualizacion) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stockActual = stockActual;
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getIdProducto() { return idProducto; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getStockActual() { return stockActual; }
    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
}
