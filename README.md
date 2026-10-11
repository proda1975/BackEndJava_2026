# CRUD de productos en Java

Aplicación de consola para crear, consultar, modificar y eliminar productos. Los registros se guardan en memoria y se pierden al cerrar el programa.

## Requisitos

- JDK 17 o superior
- Maven (opcional, para compilar desde el proyecto)

## Estructura

- `app`: menú y entrada/salida por consola.
- `modelo`: entidad `Producto`.
- `servicio`: reglas de negocio y validaciones.
- `repositorio`: operaciones de almacenamiento en memoria.

## Ejecución con Maven

Desde la carpeta del proyecto:

```sh
mvn compile
java -cp target/classes com.laboratorio.productos.app.Aplicacion
```

También se puede compilar y ejecutar directamente con el JDK desde PowerShell:

```powershell
$archivos = Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $archivos
java -cp out com.laboratorio.productos.app.Aplicacion
```
