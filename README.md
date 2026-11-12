# 📦 Gestión de Almacén — CRUD en Java Swing

Aplicación de escritorio para gestionar el inventario de un almacén: alta, baja y modificación de productos, registro de compras y ventas, y aviso de productos bajo stock mínimo.

![Captura de la aplicación](docs/captura-almacen.png) <!-- TODO: añade una captura o GIF de la ventana -->

---

## 🎯 Problema que resuelve

Un pequeño comercio necesita saber qué tiene, cuánto le queda y qué debe reponer. La aplicación centraliza el inventario en una tabla y genera un listado de productos por debajo del stock mínimo.

## ✨ Funcionalidades

- Crear, buscar, modificar y eliminar productos (CRUD)
- Registrar compras (suma stock) y ventas (resta stock)
- Informe de productos bajo stock mínimo
- Interfaz gráfica con `JTable` de solo lectura y panel de acciones

## 🛠️ Stack y arquitectura

**Java 17+ · Swing · Colecciones (`ArrayList`, `HashMap`)**

```
gestionalmacen01/
├── modelo/
│   ├── ModeloAbs.java         # Interfaz: contrato de acceso a datos
│   ├── ModeloArrayList.java   # Implementación con ArrayList
│   ├── ModeloHashMap.java     # Implementación con HashMap (búsqueda O(1) por código)
│   └── Producto.java          # Entidad
└── principal/
    ├── AlmacenGUI.java        # Vista Swing
    └── MiAlmacen.java         # Versión de consola
```

La vista solo conoce la interfaz `ModeloAbs`, nunca la implementación concreta:

```java
private ModeloAbs almacen = new ModeloHashMap(); // se cambia de almacenamiento sin tocar la GUI
```

## 🚀 Ejecutar en local

```bash
git clone https://github.com/3r4yn/gestion-almacen-java.git
cd gestion-almacen-java
javac -d bin $(find gestionalmacen01 -name "*.java")
java -cp bin gestionalmacen01.principal.AlmacenGUI
```

También se puede importar directamente como proyecto en Eclipse.

## 🧠 Decisiones técnicas

- **Programar contra interfaces:** `ModeloAbs` separa la lógica de datos de la interfaz gráfica. Es el mismo principio que usa el patrón Repository/DAO, y permite sustituir la memoria por una base de datos (JDBC) creando solo otra clase.
- **Dos implementaciones comparables:** `ArrayList` (recorrido secuencial) frente a `HashMap` (acceso directo por código), para medir la diferencia de rendimiento en las búsquedas.
- **Tabla no editable:** todas las modificaciones pasan por los botones, que validan los datos antes de tocar el modelo.

## 🔜 Próximas mejoras

- [ ] Persistencia en MySQL con JDBC (`ModeloJDBC implements ModeloAbs`)
- [ ] Tests unitarios con JUnit 5
- [ ] Migrar a Maven

## 📝 Créditos

<!-- Si la interfaz ModeloAbs vino dada por el profesor, indícalo aquí: es honesto y bien valorado -->
Estructura base proporcionada en el módulo de Programación (DAW). Implementaciones y GUI: **Brayan**.

## 👤 Autor

**Brayan** — Estudiante de 2º DAW · [GitHub](https://github.com/3r4yn)
