package main;

import dao.*;
import model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static DepartamentoDAO depDAO = new DepartamentoDAO();
    static EmpleadoDAO empDAO = new EmpleadoDAO();
    static DispositivoDAO dispDAO = new DispositivoDAO();
    static IncidenciaDAO incDAO = new IncidenciaDAO();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n===== SISTEMA DE GESTIÓN CORPORATIVA =====");
            System.out.println("1. Gestión de Departamentos");
            System.out.println("2. Gestión de Empleados");
            System.out.println("3. Gestión de Dispositivos");
            System.out.println("4. Gestión de Incidencias");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> menuDepartamentos();
                case 2 -> menuEmpleados();
                case 3 -> menuDispositivos();
                case 4 -> menuIncidencias();
                case 0 -> System.out.println("Hasta luego.");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    static void menuDepartamentos() {
        System.out.println("\n-- Departamentos --");
        System.out.println("1. Listar todos");
        System.out.println("2. Buscar por código");
        System.out.println("3. Insertar");
        System.out.println("4. Actualizar");
        System.out.println("5. Borrar");
        System.out.print("Opción: ");
        int op = Integer.parseInt(sc.nextLine());

        try {
            switch (op) {
                case 1 -> depDAO.listarTodos().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Código: ");
                    Departamento d = depDAO.buscarPorCodigo(sc.nextLine());
                    System.out.println(d != null ? d : "No encontrado.");
                }
                case 3 -> {
                    System.out.print("Código: ");       String cod = sc.nextLine();
                    System.out.print("Nombre: ");       String nom = sc.nextLine();
                    System.out.print("Presupuesto: ");  double pres = Double.parseDouble(sc.nextLine());
                    System.out.print("Cód director (o vacío): "); String dir = sc.nextLine();
                    boolean ok = depDAO.insertar(new Departamento(cod, nom, pres, dir.isEmpty() ? null : dir));
                    System.out.println(ok ? "Insertado correctamente." : "Error al insertar.");
                }
                case 4 -> {
                    System.out.print("Código a actualizar: "); String cod = sc.nextLine();
                    System.out.print("Nuevo nombre: ");        String nom = sc.nextLine();
                    System.out.print("Nuevo presupuesto: ");   double pres = Double.parseDouble(sc.nextLine());
                    System.out.print("Nuevo director (o vacío): "); String dir = sc.nextLine();
                    boolean ok = depDAO.actualizar(new Departamento(cod, nom, pres, dir.isEmpty() ? null : dir));
                    System.out.println(ok ? "Actualizado." : "No encontrado.");
                }
                case 5 -> {
                    System.out.print("Código a borrar: ");
                    boolean ok = depDAO.borrar(sc.nextLine());
                    System.out.println(ok ? "Borrado." : "No encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }

    static void menuEmpleados() {
        System.out.println("\n-- Empleados --");
        System.out.println("1. Listar todos");
        System.out.println("2. Buscar por código");
        System.out.println("3. Listar por departamento");
        System.out.println("4. Insertar");
        System.out.println("5. Actualizar");
        System.out.println("6. Borrar");
        System.out.print("Opción: ");
        int op = Integer.parseInt(sc.nextLine());

        try {
            switch (op) {
                case 1 -> empDAO.listarTodos().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Código empleado: ");
                    Empleado e = empDAO.buscarPorCodigo(sc.nextLine());
                    System.out.println(e != null ? e : "No encontrado.");
                }
                case 3 -> {
                    System.out.print("Código departamento: ");
                    empDAO.listarPorDepartamento(sc.nextLine()).forEach(System.out::println);
                }
                case 4 -> {
                    System.out.print("Código: ");        String cod = sc.nextLine();
                    System.out.print("DNI: ");           String dni = sc.nextLine();
                    System.out.print("Nombre: ");        String nom = sc.nextLine();
                    System.out.print("Apellido 1: ");    String ap1 = sc.nextLine();
                    System.out.print("Apellido 2 (o vacío): "); String ap2 = sc.nextLine();
                    System.out.print("Rol: ");           String rol = sc.nextLine();
                    System.out.print("Fecha contratación (YYYY-MM-DD): "); LocalDate fecha = LocalDate.parse(sc.nextLine());
                    System.out.print("Salario: ");       double sal = Double.parseDouble(sc.nextLine());
                    System.out.print("Código dep: ");    String dep = sc.nextLine();
                    System.out.print("Mail: ");          String mail = sc.nextLine();
                    boolean ok = empDAO.insertar(new Empleado(cod, dni, nom, ap1, ap2.isEmpty() ? null : ap2, rol, fecha, sal, dep, mail));
                    System.out.println(ok ? "Insertado." : "Error.");
                }
                case 5 -> {
                    System.out.print("Código a actualizar: "); String cod = sc.nextLine();
                    System.out.print("DNI: ");           String dni = sc.nextLine();
                    System.out.print("Nombre: ");        String nom = sc.nextLine();
                    System.out.print("Apellido 1: ");    String ap1 = sc.nextLine();
                    System.out.print("Apellido 2 (o vacío): "); String ap2 = sc.nextLine();
                    System.out.print("Rol: ");           String rol = sc.nextLine();
                    System.out.print("Fecha contratación (YYYY-MM-DD): "); LocalDate fecha = LocalDate.parse(sc.nextLine());
                    System.out.print("Salario: ");       double sal = Double.parseDouble(sc.nextLine());
                    System.out.print("Código dep: ");    String dep = sc.nextLine();
                    System.out.print("Mail: ");          String mail = sc.nextLine();
                    boolean ok = empDAO.actualizar(new Empleado(cod, dni, nom, ap1, ap2.isEmpty() ? null : ap2, rol, fecha, sal, dep, mail));
                    System.out.println(ok ? "Actualizado." : "No encontrado.");
                }
                case 6 -> {
                    System.out.print("Código a borrar: ");
                    boolean ok = empDAO.borrar(sc.nextLine());
                    System.out.println(ok ? "Borrado." : "No encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }

    static void menuDispositivos() {
        System.out.println("\n-- Dispositivos --");
        System.out.println("1. Listar todos");
        System.out.println("2. Buscar por código");
        System.out.println("3. Insertar");
        System.out.println("4. Actualizar");
        System.out.println("5. Borrar");
        System.out.print("Opción: ");
        int op = Integer.parseInt(sc.nextLine());

        try {
            switch (op) {
                case 1 -> dispDAO.listarTodos().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Código dispositivo: ");
                    Dispositivo d = dispDAO.buscarPorCodigo(sc.nextLine());
                    System.out.println(d != null ? d : "No encontrado.");
                }
                case 3 -> {
                    System.out.print("Código: ");       String cod = sc.nextLine();
                    System.out.print("MAC: ");          String mac = sc.nextLine();
                    System.out.print("Num serie: ");    String ns = sc.nextLine();
                    System.out.print("SO: ");           String so = sc.nextLine();
                    System.out.print("IP: ");           String ip = sc.nextLine();
                    System.out.print("Tipo: ");         String tipo = sc.nextLine();
                    System.out.print("Cód empleado (o vacío): "); String emp = sc.nextLine();
                    boolean ok = dispDAO.insertar(new Dispositivo(cod, mac, ns, so, ip, tipo, emp.isEmpty() ? null : emp));
                    System.out.println(ok ? "Insertado." : "Error.");
                }
                case 4 -> {
                    System.out.print("Código a actualizar: "); String cod = sc.nextLine();
                    System.out.print("MAC: ");          String mac = sc.nextLine();
                    System.out.print("Num serie: ");    String ns = sc.nextLine();
                    System.out.print("SO: ");           String so = sc.nextLine();
                    System.out.print("IP: ");           String ip = sc.nextLine();
                    System.out.print("Tipo: ");         String tipo = sc.nextLine();
                    System.out.print("Cód empleado (o vacío): "); String emp = sc.nextLine();
                    boolean ok = dispDAO.actualizar(new Dispositivo(cod, mac, ns, so, ip, tipo, emp.isEmpty() ? null : emp));
                    System.out.println(ok ? "Actualizado." : "No encontrado.");
                }
                case 5 -> {
                    System.out.print("Código a borrar: ");
                    boolean ok = dispDAO.borrar(sc.nextLine());
                    System.out.println(ok ? "Borrado." : "No encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }

    static void menuIncidencias() {
        System.out.println("\n-- Incidencias --");
        System.out.println("1. Listar todas");
        System.out.println("2. Listar por estado");
        System.out.println("3. Insertar");
        System.out.println("4. Cerrar incidencia");
        System.out.println("5. Borrar");
        System.out.print("Opción: ");
        int op = Integer.parseInt(sc.nextLine());

        try {
            switch (op) {
                case 1 -> incDAO.listarTodos().forEach(System.out::println);
                case 2 -> {
                    System.out.println("Estados posibles: Pendiente / En Progreso / Cerrada");
                    System.out.print("Estado: ");
                    incDAO.listarPorEstado(sc.nextLine()).forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Código: ");           String cod = sc.nextLine();
                    System.out.print("Observaciones: ");    String obs = sc.nextLine();
                    System.out.print("Cód dispositivo: ");  String disp = sc.nextLine();
                    System.out.print("Cód responsable: ");  String resp = sc.nextLine();
                    System.out.print("Cód creador: ");      String crea = sc.nextLine();
                    boolean ok = incDAO.insertar(new Incidencia(cod, obs, "Pendiente", LocalDate.now(), null, disp, resp, crea));
                    System.out.println(ok ? "Incidencia creada." : "Error.");
                }
                case 4 -> {
                    System.out.print("Código incidencia a cerrar: "); String cod = sc.nextLine();
                    Incidencia i = incDAO.listarTodos().stream()
                            .filter(x -> x.getCodIncidencia().equals(cod))
                            .findFirst().orElse(null);
                    if (i != null) {
                        i.setEstado("Cerrada");
                        i.setFechaCierre(LocalDate.now());
                        System.out.println(incDAO.actualizar(i) ? "Cerrada correctamente." : "Error.");
                    } else {
                        System.out.println("No encontrada.");
                    }
                }
                case 5 -> {
                    System.out.print("Código a borrar: ");
                    boolean ok = incDAO.borrar(sc.nextLine());
                    System.out.println(ok ? "Borrado." : "No encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }
}