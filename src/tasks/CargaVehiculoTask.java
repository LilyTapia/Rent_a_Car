/*
 * 1. La clase CargaVehiculoTask implementa la interfaz Runnable. 
 * 2. Está diseñada para cargar un objeto Vehiculo en una colección concurrente de tipo ConcurrentHashMap<String, Vehiculo>. 
 * 3. La clase tiene un constructor que inicializa el vehículo y la colección. 
 * 4. El método run de la clase agrega el vehículo a la colección de manera segura, 
 * 5. utilizando el método put de ConcurrentHashMap para evitar condiciones de carrera en entornos multihilo.
 */

package tasks;

import java.util.concurrent.ConcurrentHashMap;
import model.Vehiculo;

public class CargaVehiculoTask implements Runnable {
    private final Vehiculo vehiculo; // Vehículo que se va a cargar en la colección
    private final ConcurrentHashMap<String, Vehiculo> vehiculos; // Colección concurrente de vehículos

    // Constructor que inicializa la tarea de carga con el vehículo y la colección
    public CargaVehiculoTask(Vehiculo vehiculo, ConcurrentHashMap<String, Vehiculo> vehiculos) {
        this.vehiculo = vehiculo;
        this.vehiculos = vehiculos;
    }

    @Override
    public void run() {
        // Lógica para agregar el vehículo a la colección de manera segura
        vehiculos.put(vehiculo.getPatente(), vehiculo);
        // Se utiliza ConcurrentHashMap.put() que asegura la operación atomica
        // y segura en entornos multihilo, evitando problemas como race conditions.
    }
}