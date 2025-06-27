package com.empresa;

import com.empresa.modelo.sala;
import com.empresa.modelo.Empleado;
import com.empresa.modelo.Reserva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    static SalaDAO salaDAO = new SalaDAO();
    static EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    static ReservaDAO reservaDAO = new ReservaDAO();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n=== Sistema de Reservas de Salas ===");
            System.out.println("1. Gestionar Salas");
            System.out.println("2. Gestionar Empleados");
            System.out.println("3. Gestionar Reservas");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opcion: ");
            opcion = leerOpcion(0, 3);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuSalas();
                    break;
                case 2:
                    menuEmpleados();
                    break;
                case 3:
                    menuReservas();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    private static void menuSalas() {
        System.out.println("\n--- Gestión de Salas ---");
        System.out.println("1. Insertar Sala");
        System.out.println("2. Listar Salas");
        System.out.print("Selecciona una opcion: ");
        int op = op = leerOpcion(1, 2);
        scanner.nextLine();

        if (op == 1) {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Capacidad: ");
            int capacidad = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Recursos: ");
            String recursos = scanner.nextLine();
            sala sala = new sala(0, nombre, capacidad, recursos);
            salaDAO.insertarSala(sala);
            System.out.println("Sala insertada correctamente.");
        } else if (op == 2) {
            List<sala> salas = salaDAO.obtenerSalas();
            salas.forEach(s -> System.out.println(s.getId() + " - " + s.getNombre() + " - " + s.getCapacidad() + " - " + s.getRecursos()));
        } else {
            System.out.println("Opcion invalida.");
        }
    }

    private static void menuEmpleados() {
        System.out.println("\n--- Gestión de Empleados ---");
        System.out.println("1. Insertar Empleado");
        System.out.println("2. Listar Empleados");
        System.out.print("Selecciona una opción: ");
        int op = op = leerOpcion(1, 2);
        scanner.nextLine();

        if (op == 1) {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Departamento: ");
            String departamento = scanner.nextLine();
            Empleado empleado = new Empleado(0, nombre, email, departamento);
            empleadoDAO.insertarEmpleado(empleado);
            System.out.println("Empleado insertado correctamente.");
        } else if (op == 2) {
            List<Empleado> empleados = empleadoDAO.obtenerEmpleados();
            empleados.forEach(e -> System.out.println(e.getId() + " - " + e.getNombre() + " - " + e.getEmail() + " - " + e.getDepartamento()));
        } else {
            System.out.println("Opcion invalida.");
        }
    }

    private static void menuReservas() {
        System.out.println("\n--- Gestión de Reservas ---");
        System.out.println("1. Insertar Reserva");
        System.out.println("2. Listar Reservas");
        System.out.print("Selecciona una opcion: ");
        int op = op = leerOpcion(1, 2);
        scanner.nextLine();

        if (op == 1) {
            System.out.print("ID Empleado: ");
            int idEmpleado = scanner.nextInt();
            System.out.print("ID Sala: ");
            int idSala = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Fecha (AAAA-MM-DD): ");
            String fechaStr = scanner.nextLine();
            System.out.print("Hora Inicio (HH:MM): ");
            String horaInicioStr = scanner.nextLine();
            System.out.print("Hora Fin (HH:MM): ");
            String horaFinStr = scanner.nextLine();

            Reserva reserva = new Reserva(
                    0,
                    idEmpleado,
                    idSala,
                    LocalDate.parse(fechaStr),
                    LocalTime.parse(horaInicioStr),
                    LocalTime.parse(horaFinStr)
            );

            boolean insertado = reservaDAO.insertarReserva(reserva);
            if (insertado) {
                System.out.println("Reserva insertada correctamente.");
            } else {
                System.out.println("No se pudo insertar la reserva por conflicto de horario.");
            }
        } else if (op == 2) {
            List<Reserva> reservas = reservaDAO.obtenerReservas();
            reservas.forEach(r -> System.out.println(r.getId() + " - Emp: " + r.getIdEmpleado() + " - Sala: " + r.getIdSala() +
                    " - " + r.getFecha() + " " + r.getHoraInicio() + " a " + r.getHoraFin()));
        } else {
            System.out.println("Opcion invalida.");
        }
    }

    private static int leerOpcion(int min, int max) {
        int opcion;
        while (true) {
            System.out.print("Selecciona una opcion: ");
            String input = scanner.nextLine();
            try {
                opcion = Integer.parseInt(input);
                if (opcion >= min && opcion <= max) {
                    return opcion;
                } else {
                    System.out.println("La opcion debe estar entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un numero valido.");
            }
        }
    }
}
