package com.sportstore.models;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int idCategoria;
    private int idProveedor;
    private String imagenUrl;

    public Producto(int idProducto, String nombre, String descripcion, double precio, int stock, int idCategoria, int idProveedor, String imagenUrl) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
        this.imagenUrl = imagenUrl;
    }

    public Producto(String nombre, String descripcion, double precio, int stock, int idCategoria, int idProveedor, String imagenUrl) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
        this.imagenUrl = imagenUrl;
    }

    public int getIdProducto() {
        return idProducto;
    }
    public int getId() {
        return idProducto; // asegúrate de que la variable se llame `id` o ajusta según corresponda
    }
    

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}    
