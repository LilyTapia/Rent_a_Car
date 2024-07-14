/*
* El archivo Main.java es el punto de entrada de una aplicación Java para gestionar vehículos. 
* Utiliza clases auxiliares (GestorVehiculo, GestorVehiculoHelper, MenuHelper) para realizar operaciones como agregar, buscar, eliminar y listar vehículos, además de manejar el arriendo. 
* El programa corre en un ciclo infinito, mostrando un menú para que el usuario elija operaciones, y maneja excepciones de entrada/salida con un bloque try-catch.
*/

package ui;

import data.GestorVehiculo;
import helpers.GestorVehiculoHelper;
import helpers.MenuHelper;
import java.io.IOException;

public class Main {

    // Instancias de gestores y helpers necesarios para la aplicación
    private static final GestorVehiculo gestorVehiculo = new GestorVehiculo();  // Instancia del gestor de vehículos
    private static final GestorVehiculoHelper gestorVehiculoHelper = new GestorVehiculoHelper(gestorVehiculo);  // Helper para operaciones con vehículos
    private static final MenuHelper menuHelper = new MenuHelper(gestorVehiculoHelper);  // Helper para mostrar y procesar el menú

    public static void main(String[] args) throws IOException {
        // Ciclo principal de la aplicación que se ejecuta continuamente
        while (true) {
            // Mostrar el menú y obtener la opción seleccionada por el usuario
            int opcion = menuHelper.mostrarMenu();

            try {
                // Según la opción seleccionada por el usuario, ejecutar la acción correspondiente
                switch (opcion) {
                    case 1:
                        gestorVehiculoHelper.agregarVehiculo();  // Opción para agregar un nuevo vehículo
                        break;
                    case 2:
                        gestorVehiculoHelper.buscarVehiculo();  // Opción para buscar un vehículo por criterio
                        break;
                    case 3:
                        gestorVehiculoHelper.eliminarVehiculo();  // Opción para eliminar un vehículo existente
                        break;
                    case 4:
                        gestorVehiculoHelper.listarVehiculos();  // Opción para listar todos los vehículos registrados
                        break;
                    case 5:
                        gestorVehiculoHelper.mostrarBoleta();  // Opción para mostrar la boleta de arriendo
                        break;
                    case 6:
                        gestorVehiculoHelper.arrendarVehiculoPorPatente();  // Opción para arrendar un vehículo por patente
                        break;
                    case 7:
                        gestorVehiculoHelper.devolverVehiculoPorPatente();  // Opción para devolver un vehículo arrendado por patente
                        break;
                    case 0:
                        // Salir de la aplicación si se selecciona la opción 0
                        System.exit(0);
                    default:
                        // Mostrar mensaje de opción no válida si la opción seleccionada no existe
                        System.out.println("Opción no válida.");
                }
            } catch (IOException e) {
                // Capturar y mostrar errores de entrada/salida
                System.out.println("Ocurrió un error de entrada/salida: " + e.getMessage());
            }
        }
    }
}
