package com.laboratorio.productos.servicio;

import com.laboratorio.productos.modelo.Producto;
import com.laboratorio.productos.repositorio.ProductoRepositorio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductoServicio {
    private final ProductoRepositorio repositorio;

    public ProductoServicio(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Producto crear(String nombre, String descripcion, BigDecimal precio, int stock) {
        validarDatos(nombre, precio, stock);
        Producto producto = new Producto(0, nombre.trim(), descripcion.trim(), precio, stock);
        return repositorio.guardar(producto);
    }

    public List<Producto> listar() {
        return repositorio.listarTodos();
    }

    public Optional<Producto> buscar(int id) {
        return repositorio.buscarPorId(id);
    }

    public Producto actualizar(int id, String nombre, String descripcion, BigDecimal precio, int stock) {
        validarDatos(nombre, precio, stock);
        Producto producto = repositorio.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un producto con ese ID."));
        producto.setNombre(nombre.trim());
        producto.setDescripcion(descripcion.trim());
        producto.setPrecio(precio);
        producto.setStock(stock);
        return repositorio.guardar(producto);
    }

    public boolean eliminar(int id) {
        return repositorio.eliminarPorId(id);
    }

    private void validarDatos(String nombre, BigDecimal precio, int stock) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (precio == null || precio.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor o igual a cero.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock debe ser mayor o igual a cero.");
        }
    }
}
