/*
 * Clase para mostrar el menú de opciones del sistema de renta de vehículos
 */

package helpers;

import java.util.Scanner;

// Clase para mostrar el menú de opciones del sistema de renta de vehículos
public class MenuHelper {

    private Scanner scanner; // Scanner para entrada de usuario
    private GestorVehiculoHelper gestorVehiculoHelper; // Helper para gestionar vehículos

    // Constructor que inicializa el helper del gestor de vehículos
    public MenuHelper(GestorVehiculoHelper gestorVehiculoHelper) {
        this.gestorVehiculoHelper = gestorVehiculoHelper;
        this.scanner = new Scanner(System.in); // Inicializa el scanner para leer entrada del usuario
    }

    // Método para mostrar el menú y obtener la opción seleccionada por el usuario
    public int mostrarMenu() {
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
        System.out.println("6. Arrendar Vehículo");
        System.out.println("7. Devolver Vehículo");
        System.out.println("0. Salir");

        return scanner.nextInt(); // Lee y devuelve la opción seleccionada por el usuario
    }
}
