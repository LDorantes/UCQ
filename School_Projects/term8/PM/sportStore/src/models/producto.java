package models;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private String imagenUrl;

    public Producto(int id_producto, String nombre, double precio, String imagen_url) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen_url = imagen_url;
    }

    public int getId() {
        return id;
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

    // Puedes agregar setters si es necesario
}
