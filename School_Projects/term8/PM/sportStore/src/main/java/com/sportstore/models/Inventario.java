package com.sportstore.models;

import java.sql.Timestamp;

public class Inventario {
    private int idInventario;
    private int idProducto;
    private int stockInicial;
    private int stockActual;
    private Timestamp fechaActualizacion;

    public Inventario(int idInventario, int idProducto, int stockInicial, int stockActual, Timestamp fechaActualizacion) {
        this.idInventario = idInventario;
        this.idProducto = idProducto;
        this.stockInicial = stockInicial;
        this.stockActual = stockActual;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Inventario(int idProducto, int stockInicial, int stockActual) {
        this.idProducto = idProducto;
        this.stockInicial = stockInicial;
        this.stockActual = stockActual;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getStockInicial() {
        return stockInicial;
    }

    public void setStockInicial(int stockInicial) {
        this.stockInicial = stockInicial;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
