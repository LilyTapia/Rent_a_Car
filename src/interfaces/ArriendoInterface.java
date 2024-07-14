/*
 * Interfaz para objetos que pueden ser arrendados
 * 
 * Esta interfaz define un método para calcular y mostrar la boleta de arriendo
 * de un objeto que implemente esta interfaz.
 * 
 * La interfaz define constantes para cálculos relacionados con el arriendo.    
 */

package interfaces;

// Interfaz para objetos que pueden ser arrendados
public interface ArriendoInterface {
    
    // Constantes para cálculos relacionados con el arriendo
    double IVA = 0.19;
    double DESCUENTO_CARGA = 0.03;
    double DESCUENTO_PASAJEROS = 0.07;
    
    // Método para calcular y mostrar la boleta de arriendo
    void calcularYMostrarBoleta();
    
}
