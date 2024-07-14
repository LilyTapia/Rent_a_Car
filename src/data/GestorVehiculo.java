// data/GestorVehiculo.java

package data;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import model.Vehiculo;
import tasks.CargaVehiculoTask;


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

    public void actualizarVehiculo(Vehiculo vehiculoActualizado) throws VehiculoNoEncontradoException, IOException {
        Vehiculo vehiculo = vehiculos.get(vehiculoActualizado.getPatente());
        if (vehiculo == null) {
            throw new VehiculoNoEncontradoException("Vehículo con la patente " + vehiculoActualizado.getPatente() + " no encontrado.");
        }
        // Actualizar los datos del vehículo
        vehiculo.setMarca(vehiculoActualizado.getMarca());
        vehiculo.setModelo(vehiculoActualizado.getModelo());
        vehiculo.setPrecioDiario(vehiculoActualizado.getPrecioDiario());
        vehiculo.setArriendoLargoPlazo(vehiculoActualizado.isArriendoLargoPlazo());
        vehiculo.setEstado(vehiculoActualizado.getEstado());
        
        // Persistir los cambios
        persistenciaVehiculo.actualizarVehiculo(vehiculo);
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
    
    public Map<String, Vehiculo> getVehiculos() {
        return vehiculos; // Devuelve la colección de vehículos
    }

    private void cargarVehiculos() {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            for (Vehiculo vehiculo : persistenciaVehiculo.cargarVehiculos()) {
                CargaVehiculoTask task = new CargaVehiculoTask(vehiculo, vehiculos);
                executor.execute(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException ex) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}
