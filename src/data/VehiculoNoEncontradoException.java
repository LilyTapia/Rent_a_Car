// data/VehiculoNoEncontradoException.java

package data;

// Excepción personalizada para indicar que un vehículo no ha sido encontrado
public class VehiculoNoEncontradoException extends Exception {

    // Constructor que acepta un mensaje de error para describir la excepción
    public VehiculoNoEncontradoException(String mensaje) {
        super(mensaje);  // Llama al constructor de la clase base (Exception) con el mensaje proporcionado
    }
}
