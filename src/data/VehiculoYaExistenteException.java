// data/VehiculoYaExistenteException.java

package data;

// Excepción personalizada para indicar que ya existe un vehículo con la misma patente
public class VehiculoYaExistenteException extends Exception {

    // Constructor que acepta un mensaje de error para describir la excepción
    public VehiculoYaExistenteException(String mensaje) {
        super(mensaje);  // Llama al constructor de la clase base (Exception) con el mensaje proporcionado
    }
}
