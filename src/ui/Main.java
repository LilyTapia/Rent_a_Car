package ui;

import data.GestorVehiculo;
import data.VehiculoNoEncontradoException;
import data.VehiculoYaExistenteException;
import java.io.IOException;
import java.util.Scanner;
import model.Vehiculo;
import model.VehiculoCarga;
import model.VehiculoPasajeros;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static GestorVehiculo gestorVehiculo = new GestorVehiculo();

    public static void main(String[] args) throws IOException {
        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    agregarVehiculo();
                    break;
                case 2:
                    buscarVehiculo();
                    break;
                case 3:
                    eliminarVehiculo();
                    break;
                case 4:
                    listarVehiculos();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarMenu() {

        System.out.println("======================================================================================");
        System.out.println("                    BIENVENIDOS AL SISTEMA RENT A CAR  BRIEFDRIVE                    ");
        System.out.println("======================================================================================");

        System.out.println("                                                                                      ");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Agregar Vehículo");
        System.out.println("2. Buscar Vehículo");
        System.out.println("3. Eliminar Vehículo");
        System.out.println("4. Listar Vehículos");
        System.out.println("5. Mostrar boleta");
        System.out.println("0. Salir");

    }

    private static void agregarVehiculo() throws IOException {
        System.out.println("Ingrese tipo de vehículo (carga/pasajeros):");
        String tipo = scanner.nextLine();
        System.out.println("Ingrese patente:");
        String patente = scanner.nextLine();
        System.out.println("Ingrese marca:");
        String marca = scanner.nextLine();
        System.out.println("Ingrese modelo:");
        String modelo = scanner.nextLine();
        System.out.println("Ingrese precio diario:");
        double precioDiario = scanner.nextDouble();
        scanner.nextLine();  // Consumir el salto de línea

        Vehiculo vehiculo;
        if ("carga".equalsIgnoreCase(tipo)) {
            System.out.println("Ingrese capacidad de carga:");
            double capacidadCarga = scanner.nextDouble();
            scanner.nextLine();  // Consumir el salto de línea
            vehiculo = new VehiculoCarga(patente, marca, modelo, precioDiario, capacidadCarga);
        } else {
            System.out.println("Ingrese número de pasajeros:");
            int numeroPasajeros = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea
            vehiculo = new VehiculoPasajeros(patente, marca, modelo, precioDiario, numeroPasajeros);
        }

        try {
            gestorVehiculo.agregarVehiculo(vehiculo);
            System.out.println("Vehículo agregado exitosamente.");
        } catch (VehiculoYaExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarVehiculo() {
        System.out.println("Ingrese patente del vehículo a buscar:");
        String patente = scanner.nextLine();
        try {
            Vehiculo vehiculo = (Vehiculo) gestorVehiculo.buscarVehiculo(patente);
            System.out.println("Vehículo encontrado: " + vehiculo.getDetalles());
        } catch (VehiculoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void eliminarVehiculo() throws IOException {
        System.out.println("Ingrese patente del vehículo a eliminar:");
        String patente = scanner.nextLine();
        try {
            gestorVehiculo.eliminarVehiculo(patente);
            System.out.println("Vehículo eliminado exitosamente.");
        } catch (VehiculoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarVehiculos() {
        gestorVehiculo.listarVehiculos();
    }
}
