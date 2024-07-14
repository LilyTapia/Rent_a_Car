/*
 * La clase PersistenciaVehiculo está diseñada para manejar la persistencia de datos de vehículos, 
 * específicamente para cargar datos de vehículos desde un archivo CSV.
 */

package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Vehiculo;
import model.VehiculoCarga;
import model.VehiculoPasajeros;

public class PersistenciaVehiculo {

    // Ruta del archivo CSV donde se almacenan los datos de los vehículos
    private static final String FILE_PATH = "src/resources/vehiculos.csv";

    // Método para cargar vehículos desde el archivo CSV
    public List<Vehiculo> cargarVehiculos() throws IOException {
        List<Vehiculo> vehiculos = new ArrayList<>();
        List<String[]> datos = LectorCSV.leerCSV(FILE_PATH);

        // Recorrer cada fila de datos del CSV
        for (String[] fila : datos) {
            Vehiculo vehiculo;
            // Determinar el tipo de vehículo (carga o pasajeros) basado en el primer valor de la fila
            if ("carga".equalsIgnoreCase(fila[0])) {
                // Crear instancia de VehiculoCarga si el primer valor es "carga"
                vehiculo = new VehiculoCarga(
                    fila[1], // patente
                    fila[2], // marca
                    fila[3], // modelo
                    Double.parseDouble(fila[4]), // precioDiario
                    Double.parseDouble(fila[5]), // capacidadCarga
                    Boolean.parseBoolean(fila[6]), // arriendoLargoPlazo
                    fila[7] // estado
                );
            } else {
                // Crear instancia de VehiculoPasajeros si el primer valor es "pasajeros"
                vehiculo = new VehiculoPasajeros(
                    fila[1], // patente
                    fila[2], // marca
                    fila[3], // modelo
                    Double.parseDouble(fila[4]), // precioDiario
                    Integer.parseInt(fila[5]), // numeroPasajeros
                    Boolean.parseBoolean(fila[6]), // arriendoLargoPlazo
                    fila[7] // estado
                );
            }
            vehiculos.add(vehiculo);  // Agregar el vehículo a la lista de vehículos
        }
        return vehiculos;  // Devolver la lista de vehículos cargados desde el CSV
    }

    // Método para guardar un vehículo en el archivo CSV
    public void guardarVehiculo(Vehiculo vehiculo) throws IOException {
        List<String[]> datos = LectorCSV.leerCSV(FILE_PATH);
        String[] nuevaFila;

        // Determinar el tipo de vehículo y crear una nueva fila de datos para el CSV
        if (vehiculo instanceof VehiculoCarga) {
            VehiculoCarga vc = (VehiculoCarga) vehiculo;
            nuevaFila = new String[]{
                "carga",
                vc.getPatente(),
                vc.getMarca(),
                vc.getModelo(),
                String.valueOf(vc.getPrecioDiario()),
                String.valueOf(vc.getCapacidadCarga()),
                String.valueOf(vc.isArriendoLargoPlazo()),
                String.valueOf(vc.getEstado())
            };
        } else {
            VehiculoPasajeros vp = (VehiculoPasajeros) vehiculo;
            nuevaFila = new String[]{
                "pasajeros",
                vp.getPatente(),
                vp.getMarca(),
                vp.getModelo(),
                String.valueOf(vp.getPrecioDiario()),
                String.valueOf(vp.getNumeroPasajeros()),
                String.valueOf(vp.isArriendoLargoPlazo()),
                String.valueOf(vp.getEstado())
            };
        }

        datos.add(nuevaFila);  // Agregar la nueva fila al conjunto de datos existentes
        LectorCSV.escribirCSV(FILE_PATH, datos);  // Escribir los datos actualizados en el archivo CSV
    }

    // Método para eliminar un vehículo del archivo CSV por su patente
    public void eliminarVehiculo(String patente) throws IOException {
        List<String[]> datos = LectorCSV.leerCSV(FILE_PATH);

        // Eliminar la fila correspondiente al vehículo con la patente especificada
        datos.removeIf(fila -> fila[1].equals(patente));

        // Escribir los datos actualizados en el archivo CSV
        LectorCSV.escribirCSV(FILE_PATH, datos);
    }

    // Método para actualizar los datos de un vehículo en el archivo CSV
    public boolean actualizarVehiculo(Vehiculo vehiculoActualizado) throws IOException {
        List<String[]> datos = LectorCSV.leerCSV(FILE_PATH);
        boolean encontrado = false;

        // Recorrer cada fila de datos del CSV
        for (int i = 0; i < datos.size(); i++) {
            String[] fila = datos.get(i);
            // Verificar si la fila corresponde al vehículo que se desea actualizar
            if (fila[1].equals(vehiculoActualizado.getPatente())) {
                // Crear una nueva fila con los datos actualizados del vehículo
                String[] nuevaFila;
                if (vehiculoActualizado instanceof VehiculoCarga) {
                    VehiculoCarga vc = (VehiculoCarga) vehiculoActualizado;
                    nuevaFila = new String[]{
                        "carga",
                        vc.getPatente(),
                        vc.getMarca(),
                        vc.getModelo(),
                        String.valueOf(vc.getPrecioDiario()),
                        String.valueOf(vc.getCapacidadCarga()),
                        String.valueOf(vc.isArriendoLargoPlazo()),
                        String.valueOf(vc.getEstado())
                    };
                } else {
                    VehiculoPasajeros vp = (VehiculoPasajeros) vehiculoActualizado;
                    nuevaFila = new String[]{
                        "pasajeros",
                        vp.getPatente(),
                        vp.getMarca(),
                        vp.getModelo(),
                        String.valueOf(vp.getPrecioDiario()),
                        String.valueOf(vp.getNumeroPasajeros()),
                        String.valueOf(vp.isArriendoLargoPlazo()),
                        String.valueOf(vp.getEstado())
                    };
                }
                datos.set(i, nuevaFila);  // Actualizar la fila con los nuevos datos del vehículo
                encontrado = true;  // Marcar como encontrado
                break;  // Salir del ciclo una vez encontrado el vehículo
            }
        }

        if (encontrado) {
            LectorCSV.escribirCSV(FILE_PATH, datos);  // Escribir los datos actualizados en el archivo CSV
        }

        return encontrado;  // Devolver true si se actualizó el vehículo, false si no se encontró
    }
}
