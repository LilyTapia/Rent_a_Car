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
                vehiculo = new VehiculoCarga(fila[1], fila[2], fila[3], Double.parseDouble(fila[4]), Double.parseDouble(fila[5]));
            } else {
                vehiculo = new VehiculoPasajeros(fila[1], fila[2], fila[3], Double.parseDouble(fila[4]), Integer.parseInt(fila[5]));
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
            nuevaFila = new String[]{"carga", vc.getPatente(), vc.getMarca(), vc.getModelo(), String.valueOf(vc.getPrecioDiario()), String.valueOf(vc.getCapacidadCarga())};
        } else {
            VehiculoPasajeros vp = (VehiculoPasajeros) vehiculo;
            nuevaFila = new String[]{"pasajeros", vp.getPatente(), vp.getMarca(), vp.getModelo(), String.valueOf(vp.getPrecioDiario()), String.valueOf(vp.getNumeroPasajeros())};
        }
        datos.add(nuevaFila);
        LectorCSV.escribirCSV(FILE_PATH, datos);
    }

    public void eliminarVehiculo(String patente) throws IOException {
        List<String[]> datos = LectorCSV.leerCSV(FILE_PATH);
        datos.removeIf(fila -> fila[1].equals(patente));
        LectorCSV.escribirCSV(FILE_PATH, datos);
    }
}
