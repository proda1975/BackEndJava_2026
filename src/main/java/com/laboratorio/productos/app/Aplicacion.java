package com.laboratorio.productos.app;

import com.laboratorio.productos.modelo.Producto;
import com.laboratorio.productos.repositorio.ProductoRepositorioMemoria;
import com.laboratorio.productos.servicio.ProductoServicio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Aplicacion {
    private final Scanner scanner = new Scanner(System.in);
    private final ProductoServicio servicio = new ProductoServicio(new ProductoRepositorioMemoria());

    public static void main(String[] args) {
        new Aplicacion().iniciar();
    }

    private void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            try {
                switch (opcion) {
                    case 1 -> crearProducto();
                    case 2 -> listarProductos();
                    case 3 -> buscarProducto();
                    case 4 -> actualizarProducto();
                    case 5 -> eliminarProducto();
                    case 0 -> System.out.println("Programa finalizado.");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("=== GESTIÓN DE PRODUCTOS ===");
        System.out.println("1. Crear producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar producto por ID");
        System.out.println("4. Modificar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("0. Salir");
    }

    private void crearProducto() {
        String nombre = leerTexto("Nombre: ");
        String descripcion = leerTexto("Descripción: ");
        BigDecimal precio = leerDecimal("Precio: ");
        int stock = leerEntero("Stock: ");
        Producto producto = servicio.crear(nombre, descripcion, precio, stock);
        System.out.println("Producto creado: " + producto);
    }

    private void listarProductos() {
        List<Producto> productos = servicio.listar();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        productos.forEach(System.out::println);
    }

    private void buscarProducto() {
        int id = leerEntero("ID del producto: ");
        servicio.buscar(id).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("No se encontró un producto con ese ID.")
        );
    }

    private void actualizarProducto() {
        int id = leerEntero("ID del producto a modificar: ");
        String nombre = leerTexto("Nuevo nombre: ");
        String descripcion = leerTexto("Nueva descripción: ");
        BigDecimal precio = leerDecimal("Nuevo precio: ");
        int stock = leerEntero("Nuevo stock: ");
        Producto producto = servicio.actualizar(id, nombre, descripcion, precio, stock);
        System.out.println("Producto actualizado: " + producto);
    }

    private void eliminarProducto() {
        int id = leerEntero("ID del producto a eliminar: ");
        if (servicio.eliminar(id)) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("No se encontró un producto con ese ID.");
        }
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private BigDecimal leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                String valor = scanner.nextLine().trim().replace(',', '.');
                return new BigDecimal(valor);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido (por ejemplo, 1250.50).");
            }
        }
    }
}
