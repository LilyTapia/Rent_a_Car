// ui/Main.java

package ui;

import data.GestorVehiculo;
import helpers.GestorVehiculoHelper;
import helpers.MenuHelper;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static GestorVehiculo gestorVehiculo = new GestorVehiculo();
    private static GestorVehiculoHelper gestorVehiculoHelper = new GestorVehiculoHelper(gestorVehiculo);
    private static MenuHelper menuHelper = new MenuHelper(gestorVehiculoHelper);

    public static void main(String[] args) throws IOException {
        while (true) {
            int opcion = menuHelper.mostrarMenu();

            try {
                switch (opcion) {
                    case 1:
                        gestorVehiculoHelper.agregarVehiculo();
                        break;
                    case 2:
                        gestorVehiculoHelper.buscarVehiculo();
                        break;
                    case 3:
                        gestorVehiculoHelper.eliminarVehiculo();
                        break;
                    case 4:
                        gestorVehiculoHelper.listarVehiculos();
                        break;
                    case 5:
                        gestorVehiculoHelper.mostrarBoleta();
                        break;
                    case 6:
                        gestorVehiculoHelper.arrendarVehiculoPorPatente();
                        break;
                    case 7:
                        gestorVehiculoHelper.devolverVehiculoPorPatente();
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (IOException e) {
                System.out.println("Ocurrió un error de entrada/salida: " + e.getMessage());
            }
        }
    }
}
