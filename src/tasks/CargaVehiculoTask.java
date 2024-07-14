// tasks/CargaVehiculoTask.java

package tasks;

import java.util.concurrent.ConcurrentHashMap;
import model.Vehiculo;

public class CargaVehiculoTask implements Runnable {
    private Vehiculo vehiculo;
    private ConcurrentHashMap<String, Vehiculo> vehiculos;

    public CargaVehiculoTask(Vehiculo vehiculo, ConcurrentHashMap<String, Vehiculo> vehiculos) {
        this.vehiculo = vehiculo;
        this.vehiculos = vehiculos;
    }

    @Override
    public void run() {
        // Aquí se implementa la lógica para agregar el vehículo a la colección de manera segura
        vehiculos.put(vehiculo.getPatente(), vehiculo);
    }
}