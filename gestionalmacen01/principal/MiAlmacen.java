/**
 * MiAlmacen
 * Programa principal de gestión de un almacén de productos
 * implementa un CRUD ( Creat, Read, Update, Delete) ofreciendo varios
 * informes.
 */
package gestionalmacen01.principal;

import java.util.List;
import java.util.Scanner;

import gestionalmacen01.modelo.ModeloAbs;
import gestionalmacen01.modelo.ModeloArrayList;
import gestionalmacen01.modelo.Producto;

public class MiAlmacen {
    static private ModeloAbs almacen;
    static private Scanner sc;

    public static void main(String[] args) {
        almacen = new ModeloArrayList();
        sc = new Scanner(System.in);

        cargarDatosPrueba();

        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerOpcion(1, 9);
            switch (opcion) {
                case 1: crear(); break;
                case 2: consultar(); break;
                case 3: borrar(); break;
                case 4: modificarPrecio(); break;
                case 5: comprar(); break;
                case 6: vender(); break;
                case 7: listar(); break;
                case 8: listarPocoStock(); break;
            }
            System.out.println("\n---------------------------- ");
            System.out.print("Pulse enter para continuar");
            sc.nextLine();
        } while (opcion != 9);
        System.out.println("\n Fin del programa.");
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n\n    MENU");
        System.out.println("1. Nuevo producto ");
        System.out.println("2. Consulta producto ");
        System.out.println("3. Borrar producto ");
        System.out.println("4. Modificar precio ");
        System.out.println("5. Compra de productos ");
        System.out.println("6. Venta de productos ");
        System.out.println("7. Listado completo de productos ");
        System.out.println("8. Listado de productos con stock inferior al mínimo");
        System.out.println("9. Terminar ");
        System.out.print("Elige una opción (1-9)");
    }

    private static int leerOpcion(int primero, int ultimo) {
        int valor = leerEntero();
        while (valor < primero || valor > ultimo) {
            valor = leerEntero();
        }
        return valor;
    }

    static private int leerEntero() {
        boolean error = false;
        int valor = 0;
        String cadena;
        do {
            error = false;
            try {
                cadena = sc.nextLine();
                valor = Integer.parseInt(cadena);
            } catch (NumberFormatException e) {
                System.out.println("Error en formato.");
                error = true;
            }
        } while (error);
        return valor;
    }

    static private float leerFloat() {
        boolean error = false;
        float valor = 0;
        String cadena;
        do {
            error = false;
            try {
                cadena = sc.nextLine();
                valor = Float.parseFloat(cadena);
            } catch (NumberFormatException e) {
                System.out.println("Error en formato.");
                error = true;
            }
        } while (error);
        return valor;
    }

    private static void consultar() {
        System.out.println("<CONSULTA>");
        System.out.print("Introduzca codigo:");
        int codigo = leerEntero();
        Producto p = almacen.buscarProducto(codigo);
        if (p == null) {
            System.out.println("El producto no se encuentra en almacen");
        } else {
            System.out.println("PRODUCTO " + p);
        }
    }

    private static void borrar() {
        System.out.println("<ELIMINAR>");
        System.out.println("Introduzca el codigo del producto a borrar: ");
        int cod = leerEntero();
        Producto p = almacen.buscarProducto(cod);
        if (p != null) {
            System.out.println("Producto encontrado: " + p);
            System.out.println("¿Seguro que quieres eliminarlo (S/N): ?");
            String confirmacion = sc.nextLine();
            if (confirmacion.equalsIgnoreCase("S")) {
                almacen.borrarProducto(cod);
                System.out.println("Producto eliminado");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void modificarPrecio() {
        System.out.println("<MODIFICAR PRECIO>");
        System.out.println("Codigo: ");
        int cod = leerEntero();
        Producto p = almacen.buscarProducto(cod);

        if (p != null) {
            System.out.println("Precio actual: " + p.getPrecio());
            float nuevoPrecio;
            do {
                System.out.println("Nuevo precio: ");
                nuevoPrecio = leerFloat();
            } while (nuevoPrecio <= 0);
            p.setPrecio(nuevoPrecio);
            almacen.modificarProducto(p);
            System.out.println("Precio actualizado.");
        } else {
            System.out.println("No existe el producto.");
        }
    }

    private static void comprar() {
        System.out.println("<COMPRAR>");
        System.out.println("Ingrese código: ");
        int cod = leerEntero();
        Producto p = almacen.buscarProducto(cod);

        if (p != null) {
            System.out.println("Cantidad a comprar: ");
            int cant = leerEntero();
            if (cant > 0) {
                p.setStock(p.getStock() + cant);
                System.out.println("Stock actualizado: " + p.getStock());
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void vender() {
        System.out.println("<VENDER>");
        System.out.println("Ingresar codigo: ");
        int cod = leerEntero();
        Producto p = almacen.buscarProducto(cod);

        if (p != null) {
            System.out.println("Cantidad vendida: ");
            int cant = leerEntero();
            if (cant > 0 && cant <= p.getStock()) {
                p.setStock(p.getStock() - cant);
                System.out.println("Venta realizada. Stock restante: " + p.getStock());
            } else {
                System.out.println("Error: No hay suficiente stock o cantidad invalida.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void listar() {
        System.out.println("<LISTAR>");
        almacen.imprimirProductosTodos();
    }

    private static void listarPocoStock() {
        System.out.println("<LISTAR STOCK BAJO MINIMOS>");
        List<Producto> bajos = almacen.obtenerProductosStockMin();
        if (bajos.isEmpty()) {
            System.out.println("Todo el stock está correcto.");
        } else {
            for (Producto p : bajos) System.out.println(p);
        }
    }

    private static void crear() {
        System.out.println("<NUEVO PRODUCTO>");
        System.out.println("Insertar codigo: ");
        int cod = leerEntero();

        if (almacen.buscarProducto(cod) != null) {
            System.out.println("Error: el codigo ya existe");
            return;
        }
        System.out.println("Nombre: ");
        String nom = sc.nextLine();

        float precio;
        do {
            System.out.println("Precio (>0): ");
            precio = leerFloat();
        } while (precio <= 0);

        int stock;
        do {
            System.out.println("Stock inicial (>0): ");
            stock = leerEntero();
        } while (stock <= 0);

        System.out.println("Stock mínimo: ");
        int min = leerEntero();

        Producto p = new Producto(cod, nom);
        p.setPrecio(precio);
        p.setStock(stock);
        p.setStock_min(min);

        almacen.insertarProducto(p);
        System.out.println("Producto creado con éxito");
    }

    private static void cargarDatosPrueba() {
        Producto p1 = new Producto(101, "Monitor 24 Pulgadas");
        p1.setStock(15); p1.setStock_min(5); p1.setPrecio(159.99f);

        Producto p2 = new Producto(102, "Teclado Mecánico");
        p2.setStock(4); p2.setStock_min(10); p2.setPrecio(45.50f);

        Producto p3 = new Producto(103, "Ratón Inalámbrico");
        p3.setStock(20); p3.setStock_min(8); p3.setPrecio(22.00f);

        Producto p4 = new Producto(104, "Disco SSD 1TB");
        p4.setStock(2); p4.setStock_min(5); p4.setPrecio(89.90f);

        almacen.insertarProducto(p1);
        almacen.insertarProducto(p2);
        almacen.insertarProducto(p3);
        almacen.insertarProducto(p4);
    }
}
