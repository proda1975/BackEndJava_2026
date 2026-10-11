package com.laboratorio.productos.modelo;

import java.math.BigDecimal;

public class Producto {
    private final int id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int stock;

    public Producto(int id, String nombre, String descripcion, BigDecimal precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Nombre: " + nombre
                + " | Descripción: " + descripcion
                + " | Precio: $" + precio.toPlainString()
                + " | Stock: " + stock;
    }
}
