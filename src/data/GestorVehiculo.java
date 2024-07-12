package data;


import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import model.Vehiculo;

public class GestorVehiculo {

    private ConcurrentHashMap<String, Vehiculo> vehiculos;
    private PersistenciaVehiculo persistenciaVehiculo;

    public GestorVehiculo() {
        vehiculos = new ConcurrentHashMap<>();
        persistenciaVehiculo = new PersistenciaVehiculo();
        cargarVehiculos();
    }

    public void agregarVehiculo(Vehiculo vehiculo) throws VehiculoYaExistenteException, IOException {
        if (vehiculos.putIfAbsent(vehiculo.getPatente(), vehiculo) != null) {
            throw new VehiculoYaExistenteException("Vehículo con la patente " + vehiculo.getPatente() + " ya existe.");
        }
        persistenciaVehiculo.guardarVehiculo(vehiculo);
    }

    public Vehiculo buscarVehiculo(String patente) throws VehiculoNoEncontradoException {
        Vehiculo vehiculo = vehiculos.get(patente);
        if (vehiculo == null) {
            throw new VehiculoNoEncontradoException("Vehículo con la patente " + patente + " no encontrado.");
        }
        return vehiculo;
    }

    public void eliminarVehiculo(String patente) throws VehiculoNoEncontradoException, IOException {
        if (vehiculos.remove(patente) == null) {
            throw new VehiculoNoEncontradoException("Vehículo con la patente " + patente + " no encontrado.");
        }
        persistenciaVehiculo.eliminarVehiculo(patente);
    }

    public void listarVehiculos() {
        vehiculos.values().forEach(vehiculo -> System.out.println(vehiculo.getDetalles()));
    }

    private void cargarVehiculos() {
        try {
            for (Vehiculo vehiculo : persistenciaVehiculo.cargarVehiculos()) {
                vehiculos.put(vehiculo.getPatente(), vehiculo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
