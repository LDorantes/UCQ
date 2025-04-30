package com.sportstore.models;

public class Carrito {
    private int idCarrito;
    private int idUsuario;
    private int idProducto;
    private int cantidad;

    public Carrito(int idCarrito, int idUsuario, int idProducto, int cantidad) {
        this.idCarrito = idCarrito;
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public int getIdCarrito() { return idCarrito; }
    public int getIdUsuario() { return idUsuario; }
    public int getIdProducto() { return idProducto; }
    public int getCantidad() { return cantidad; }

    public void setIdCarrito(int idCarrito) { this.idCarrito = idCarrito; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
