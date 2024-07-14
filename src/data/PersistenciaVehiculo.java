// data/PersistenciaVehiculo.java

package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Vehiculo;
import model.VehiculoCarga;
import model.VehiculoPasajeros;

public class PersistenciaVehiculo {

    private static final String FILE_PATH = "src/resources/vehiculos.csv";

    public List<Vehiculo> cargarVehiculos() throws IOException {
        List<Vehiculo> vehiculos = new ArrayList<>();
        List<String[]> datos = LectorCSV.leerCSV(FILE_PATH);
        for (String[] fila : datos) {
            Vehiculo vehiculo;
            if ("carga".equalsIgnoreCase(fila[0])) {
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
            vehiculos.add(vehiculo);
        }
        return vehiculos;
    }

    public void guardarVehiculo(Vehiculo vehiculo) throws IOException {
        List<String[]> datos = LectorCSV.leerCSV(FILE_PATH);
        String[] nuevaFila;
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
        datos.add(nuevaFila);
        LectorCSV.escribirCSV(FILE_PATH, datos);
    }
    

    public void eliminarVehiculo(String patente) throws IOException {
        List<String[]> datos = LectorCSV.leerCSV(FILE_PATH);
        datos.removeIf(fila -> fila[1].equals(patente));
        LectorCSV.escribirCSV(FILE_PATH, datos);
    }

    public boolean actualizarVehiculo(Vehiculo vehiculoActualizado) throws IOException {
        List<String[]> datos = LectorCSV.leerCSV(FILE_PATH);
        boolean encontrado = false;

        for (int i = 0; i < datos.size(); i++) {
            String[] fila = datos.get(i);
            if (fila[1].equals(vehiculoActualizado.getPatente())) {
                // Actualizar el vehículo existente con los nuevos datos
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
                datos.set(i, nuevaFila);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            LectorCSV.escribirCSV(FILE_PATH, datos);
        }

        return encontrado;
    }
}
