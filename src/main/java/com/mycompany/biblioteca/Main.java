package com.mycompany.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Aquí irá el menú (Fase 8)
    }
}

public static void crearCliente() {
    System.out.print("ID: ");
    String id = sc.nextLine();
    System.out.print("Nombre: ");
    String nombre = sc.nextLine();
    System.out.print("Teléfono: ");
    String telefono = sc.nextLine();
    System.out.print("Email: ");
    String email = sc.nextLine();

    Cliente c = new Cliente(id, nombre, telefono, email);
    clientes.add(c);
    System.out.println("Cliente creado con éxito.");
}

public static void listarClientes() {
    if (clientes.isEmpty()) {
        System.out.println("No hay clientes registrados.");
        return;
    }
    for (Cliente c : clientes) {
        System.out.println(c);
    }
}

public static Cliente buscarCliente(String id) {
    for (Cliente c : clientes) {
        if (c.getId().equals(id)) {
            return c;
        }
    }
    return null;
}

public static void actualizarCliente() {
    System.out.print("ID del cliente a actualizar: ");
    String id = sc.nextLine();
    Cliente c = buscarCliente(id);

    if (c == null) {
        System.out.println("Cliente no encontrado.");
        return;
    }

    System.out.print("Nuevo nombre: ");
    c.setNombre(sc.nextLine());
    System.out.print("Nuevo teléfono: ");
    c.setTelefono(sc.nextLine());
    System.out.print("Nuevo email: ");
    c.setEmail(sc.nextLine());

    System.out.println("Cliente actualizado.");
}

public static void eliminarCliente() {
    System.out.print("ID del cliente a eliminar: ");
    String id = sc.nextLine();
    Cliente c = buscarCliente(id);

    if (c == null) {
        System.out.println("Cliente no encontrado.");
        return;
    }

    clientes.remove(c);
    System.out.println("Cliente eliminado.");
}

static ArrayList<Libro> libros = new ArrayList<>();

public static void crearLibro() {
    System.out.print("Código: ");
    String codigo = sc.nextLine();
    System.out.print("Título: ");
    String titulo = sc.nextLine();
    System.out.print("Año de publicación: ");
    int anio = Integer.parseInt(sc.nextLine());
    System.out.print("Autor: ");
    String autor = sc.nextLine();

    Libro l = new Libro(codigo, titulo, anio, autor);
    libros.add(l);
    System.out.println("Libro creado con éxito.");
}

public static void listarLibros() {
    if (libros.isEmpty()) {
        System.out.println("No hay libros registrados.");
        return;
    }
    for (Libro l : libros) {
        System.out.println(l);
    }
}

public static Libro buscarLibro(String codigo) {
    for (Libro l : libros) {
        if (l.getCodigo().equals(codigo)) {
            return l;
        }
    }
    return null;
}

public static void actualizarLibro() {
    System.out.print("Código del libro a actualizar: ");
    String codigo = sc.nextLine();
    Libro l = buscarLibro(codigo);

    if (l == null) {
        System.out.println("Libro no encontrado.");
        return;
    }

    System.out.print("Nuevo título: ");
    l.setTitulo(sc.nextLine());
    System.out.print("Nuevo año: ");
    l.setAnioPublicacion(Integer.parseInt(sc.nextLine()));
    System.out.print("Nuevo autor: ");
    l.setAutor(sc.nextLine());

    System.out.println("Libro actualizado.");
}

public static void eliminarLibro() {
    System.out.print("Código del libro a eliminar: ");
    String codigo = sc.nextLine();
    Libro l = buscarLibro(codigo);

    if (l == null) {
        System.out.println("Libro no encontrado.");
        return;
    }

    libros.remove(l);
    System.out.println("Libro eliminado.");
}

package com.mycompany.biblioteca;

import java.time.LocalDate;

public class Prestamo {
    private String idPrestamo;
    private Cliente cliente;
    private Libro libro;
    private LocalDate fecha;
    private String estado; // "ACTIVO" o "DEVUELTO"

    public Prestamo(String idPrestamo, Cliente cliente, Libro libro) {
        this.idPrestamo = idPrestamo;
        this.cliente = cliente;
        this.libro = libro;
        this.fecha = LocalDate.now();
        this.estado = "ACTIVO";
    }

    public String getIdPrestamo() { return idPrestamo; }
    public Cliente getCliente() { return cliente; }
    public Libro getLibro() { return libro; }
    public LocalDate getFecha() { return fecha; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Préstamo: " + idPrestamo
                + " | Cliente: " + cliente.getNombre()
                + " | Libro: " + libro.getTitulo()
                + " | Fecha: " + fecha
                + " | Estado: " + estado;
    }
}

static ArrayList<Prestamo> prestamos = new ArrayList<>();

public static void crearPrestamo() {
    System.out.print("ID del préstamo: ");
    String idPrestamo = sc.nextLine();

    System.out.print("ID del cliente: ");
    String idCliente = sc.nextLine();
    Cliente c = buscarCliente(idCliente);
    if (c == null) {
        System.out.println("Cliente no encontrado.");
        return;
    }

    System.out.print("Código del libro: ");
    String codigoLibro = sc.nextLine();
    Libro l = buscarLibro(codigoLibro);
    if (l == null) {
        System.out.println("Libro no encontrado.");
        return;
    }

    if (!l.isDisponible()) {
        System.out.println("El libro no está disponible actualmente.");
        return;
    }

    Prestamo p = new Prestamo(idPrestamo, c, l);
    prestamos.add(p);
    l.setDisponible(false); // el libro deja de estar disponible

    System.out.println("Préstamo registrado con éxito.");
}

public static void devolucionPrestamo() {
    System.out.print("ID del préstamo a devolver: ");
    String idPrestamo = sc.nextLine();

    for (Prestamo p : prestamos) {
        if (p.getIdPrestamo().equals(idPrestamo)) {
            if (p.getEstado().equals("DEVUELTO")) {
                System.out.println("Este préstamo ya fue devuelto.");
                return;
            }
            p.setEstado("DEVUELTO");
            p.getLibro().setDisponible(true); // el libro vuelve a estar disponible
            System.out.println("Devolución registrada con éxito.");
            return;
        }
    }
    System.out.println("Préstamo no encontrado.");
}

public static void listarPrestamos() {
    boolean hayActivos = false;
    for (Prestamo p : prestamos) {
        if (p.getEstado().equals("ACTIVO")) {
            System.out.println(p);
            hayActivos = true;
        }
    }
    if (!hayActivos) {
        System.out.println("No hay préstamos activos.");
    }
}

public static void main(String[] args) {
    int opcion;

    do {
        System.out.println("\n===== SISTEMA DE GESTIÓN DE BIBLIOTECA =====");
        System.out.println("--- Clientes ---");
        System.out.println("1. Crear cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Buscar cliente");
        System.out.println("4. Actualizar cliente");
        System.out.println("5. Eliminar cliente");
        System.out.println("--- Libros ---");
        System.out.println("6. Crear libro");
        System.out.println("7. Listar libros");
        System.out.println("8. Buscar libro");
        System.out.println("9. Actualizar libro");
        System.out.println("10. Eliminar libro");
        System.out.println("--- Préstamos ---");
        System.out.println("11. Registrar préstamo");
        System.out.println("12. Registrar devolución");
        System.out.println("13. Listar préstamos activos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");

        opcion = Integer.parseInt(sc.nextLine());

        switch (opcion) {
            case 1 -> crearCliente();
            case 2 -> listarClientes();
            case 3 -> {
                System.out.print("ID a buscar: ");
                Cliente c = buscarCliente(sc.nextLine());
                System.out.println(c != null ? c : "Cliente no encontrado.");
            }
            case 4 -> actualizarCliente();
            case 5 -> eliminarCliente();

            case 6 -> crearLibro();
            case 7 -> listarLibros();
            case 8 -> {
                System.out.print("Código a buscar: ");
                Libro l = buscarLibro(sc.nextLine());
                System.out.println(l != null ? l : "Libro no encontrado.");
            }
            case 9 -> actualizarLibro();
            case 10 -> eliminarLibro();

            case 11 -> crearPrestamo();
            case 12 -> devolucionPrestamo();
            case 13 -> listarPrestamos();

            case 0 -> System.out.println("Saliendo del sistema...");
            default -> System.out.println("Opción no válida.");
        }

    } while (opcion != 0);
}
