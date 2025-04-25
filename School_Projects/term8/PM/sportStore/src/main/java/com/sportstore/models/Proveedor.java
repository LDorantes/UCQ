package com.sportstore.models;

public class Proveedor {
    private int id;
    private String nombre;
    private String contacto;
    private String telefono;

    public Proveedor(int id, String nombre, String contacto, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
    }

    public Proveedor(String nombre, String contacto, String telefono) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getContacto() { return contacto; }
    public String getTelefono() { return telefono; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setContacto(String contacto) { this.contacto = contacto; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
