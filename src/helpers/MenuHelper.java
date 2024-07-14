// src/helpers/MenuHelper.java

package helpers;

import java.util.Scanner;

public class MenuHelper {

    private Scanner scanner;
    private GestorVehiculoHelper gestorVehiculoHelper;

    public MenuHelper(GestorVehiculoHelper gestorVehiculoHelper) {
        this.gestorVehiculoHelper = gestorVehiculoHelper;
        this.scanner = new Scanner(System.in);
    }

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

        return scanner.nextInt();
    }
}
