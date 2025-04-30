// Modelo: Venta.java
package com.sportstore.models;

import java.time.LocalDateTime;

public class Venta {
    private int idVenta;
    private int idCliente;
    private int idEmpleado;
    private LocalDateTime fechaVenta;
    private double total;

    public Venta(int idVenta, int idCliente, int idEmpleado, LocalDateTime fechaVenta, double total) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.fechaVenta = fechaVenta;
        this.total = total;
    }

    public Venta(int idCliente, int idEmpleado, double total) {
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.total = total;
        this.fechaVenta = LocalDateTime.now();
    }

    // Getters y setters
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }

    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta) { this.fechaVenta = fechaVenta; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
