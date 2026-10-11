package com.laboratorio.productos.repositorio;

import com.laboratorio.productos.modelo.Producto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductoRepositorioMemoria implements ProductoRepositorio {
    private final Map<Integer, Producto> productos = new LinkedHashMap<>();
    private int siguienteId = 1;

    @Override
    public Producto guardar(Producto producto) {
        if (producto.getId() == 0) {
            Producto nuevo = new Producto(
                    siguienteId++,
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getStock()
            );
            productos.put(nuevo.getId(), nuevo);
            return nuevo;
        }

        productos.put(producto.getId(), producto);
        return producto;
    }

    @Override
    public List<Producto> listarTodos() {
        return new ArrayList<>(productos.values());
    }

    @Override
    public Optional<Producto> buscarPorId(int id) {
        return Optional.ofNullable(productos.get(id));
    }

    @Override
    public boolean eliminarPorId(int id) {
        return productos.remove(id) != null;
    }
}
