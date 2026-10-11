package com.laboratorio.productos.repositorio;

import com.laboratorio.productos.modelo.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepositorio {
    Producto guardar(Producto producto);

    List<Producto> listarTodos();

    Optional<Producto> buscarPorId(int id);

    boolean eliminarPorId(int id);
}
