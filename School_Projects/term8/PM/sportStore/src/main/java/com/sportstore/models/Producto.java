package com.sportstore.models;

public class Producto {
    private int id_producto;
    private String nombre;
    private double precio;
    private String imagen_url;

    public Producto(int id_producto, String nombre, double precio, String imagen_url) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen_url = imagen_url;
    }

    public int getIdProducto() {
        return id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getImagenUrl() {
        return imagen_url;
    }
}
