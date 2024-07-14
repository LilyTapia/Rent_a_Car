/*
 * La clase GestorVehiculo gestiona los vehículos en una aplicación de alquiler de coches, con las siguientes características:
 * 1. Utiliza un ConcurrentHashMap para almacenar vehículos por patente, permitiendo operaciones seguras en entornos concurrentes.
 * 2. Incluye métodos para agregar y actualizar vehículos, asegurándose de que no haya duplicados y persistiendo los cambios.
 * 3. Al inicializarse, carga los vehículos existentes desde una fuente de persistencia mediante PersistenciaVehiculo.
 * 4. Los métodos agregarVehiculo y actualizarVehiculo lanzan excepciones específicas si el vehículo ya existe o no se encuentra, respectivamente.
 */

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

    // Colección concurrente para almacenar vehículos por patente
    private final ConcurrentHashMap<String, Vehiculo> vehiculos;

    // Instancia de PersistenciaVehiculo para manejar la persistencia de vehículos
    private final PersistenciaVehiculo persistenciaVehiculo;

    // Constructor que inicializa la colección y carga los vehículos desde la persistencia
    public GestorVehiculo() {
        vehiculos = new ConcurrentHashMap<>();
        persistenciaVehiculo = new PersistenciaVehiculo();
        cargarVehiculos();
    }

    // Método para agregar un vehículo a la colección y persistirlo
    public void agregarVehiculo(Vehiculo vehiculo) throws VehiculoYaExistenteException, IOException {
        if (vehiculos.putIfAbsent(vehiculo.getPatente(), vehiculo) != null) {
            throw new VehiculoYaExistenteException("Vehículo con la patente " + vehiculo.getPatente() + " ya existe.");
        }
        persistenciaVehiculo.guardarVehiculo(vehiculo);
    }

    // Método para actualizar los datos de un vehículo existente y persistir los cambios
    public void actualizarVehiculo(Vehiculo vehiculoActualizado) throws VehiculoNoEncontradoException, IOException {
        Vehiculo vehiculo = vehiculos.get(vehiculoActualizado.getPatente());
        if (vehiculo == null) {
            throw new VehiculoNoEncontradoException("Vehículo con la patente " + vehiculoActualizado.getPatente() + " no encontrado.");
        }
        // Actualizar los datos del vehículo con los nuevos valores
        vehiculo.setMarca(vehiculoActualizado.getMarca());
        vehiculo.setModelo(vehiculoActualizado.getModelo());
        vehiculo.setPrecioDiario(vehiculoActualizado.getPrecioDiario());
        vehiculo.setArriendoLargoPlazo(vehiculoActualizado.isArriendoLargoPlazo());
        vehiculo.setEstado(vehiculoActualizado.getEstado());
        
        // Persistir los cambios realizados en el vehículo
        persistenciaVehiculo.actualizarVehiculo(vehiculo);
    }

    // Método para buscar un vehículo por su patente en la colección
    public Vehiculo buscarVehiculo(String patente) throws VehiculoNoEncontradoException {
        Vehiculo vehiculo = vehiculos.get(patente);
        if (vehiculo == null) {
            throw new VehiculoNoEncontradoException("Vehículo con la patente " + patente + " no encontrado.");
        }
        return vehiculo;
    }

    // Método para eliminar un vehículo de la colección y de la persistencia
    public void eliminarVehiculo(String patente) throws VehiculoNoEncontradoException, IOException {
        if (vehiculos.remove(patente) == null) {
            throw new VehiculoNoEncontradoException("Vehículo con la patente " + patente + " no encontrado.");
        }
        persistenciaVehiculo.eliminarVehiculo(patente);
    }

    // Método para listar todos los vehículos almacenados en la colección
    public void listarVehiculos() {
        vehiculos.values().forEach(vehiculo -> System.out.println(vehiculo.getDetalles()));
    }
    
    // Método para obtener la colección completa de vehículos
    public Map<String, Vehiculo> getVehiculos() {
        return vehiculos;
    }

    // Método privado para cargar vehículos desde la persistencia usando hilos
    private void cargarVehiculos() {
        // Crear un pool de hilos para cargar los vehículos en paralelo
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            // Cargar vehículos desde la persistencia y ejecutar en paralelo
            for (Vehiculo vehiculo : persistenciaVehiculo.cargarVehiculos()) {
                CargaVehiculoTask task = new CargaVehiculoTask(vehiculo, vehiculos);
                executor.execute(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Apagar el executor y manejar la interrupción si es necesario
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
