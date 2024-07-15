/*
 * La clase VehiculoPasajeros es una extensión de la clase abstracta Vehiculo y también implementa la interfaz ArriendoInterface. 
 * Está diseñada para representar vehículos de pasajeros dentro de un sistema de renta de vehículos.
 */

package model;

import interfaces.ArriendoInterface;

public class VehiculoPasajeros extends Vehiculo implements ArriendoInterface {
    private final int numeroPasajeros;

    // Constructor de la clase VehiculoPasajeros
    public VehiculoPasajeros(String patente, String marca, String modelo, double precioDiario, int numeroPasajeros, boolean arriendoLargoPlazo, String estado) {
        super(patente, marca, modelo, precioDiario, arriendoLargoPlazo, estado);
        this.numeroPasajeros = numeroPasajeros;
    }

    // Getter para obtener el número de pasajeros del vehículo
    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    // Método para obtener detalles específicos del vehículo de pasajeros
    @Override
    public String getDetalles() {
        return "VehiculoPasajeros{" +
                "patente='" + getPatente() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", precioDiario=" + getPrecioDiario() +
                ", numeroPasajeros=" + getNumeroPasajeros() +
                ", arriendoLargoPlazo=" + getArriendoLargoPlazo() +
                ", estado='" + getEstado() + '\'' +
                '}';
    }

    // Implementación del método de la interfaz ArriendoInterface para calcular y mostrar la boleta
    @Override
    public void calcularYMostrarBoleta() {
        double precioArriendo = getPrecioDiario();
        double iva = precioArriendo * ArriendoInterface.IVA;
        double descuento = precioArriendo * ArriendoInterface.DESCUENTO_PASAJEROS;
        
        double totalPagar = precioArriendo + iva - descuento;

        System.out.println("");
        System.out.println("----------------------------");
        System.out.println("Boleta Vehículo de Pasajeros");
        System.out.println("----------------------------");
        System.out.println("");
        System.out.println("Patente: " + getPatente());
        System.out.println("Marca: " + getMarca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Número de Pasajeros: " + this.numeroPasajeros);
        System.out.println("");
        System.out.println("Precio Diario: $" + getPrecioDiario());
        System.out.println("IVA (19%): $" + iva);
        System.out.println("Subtotal: $" + (precioArriendo + iva));
        System.out.println("Descuento (" + (ArriendoInterface.DESCUENTO_PASAJEROS * 100) + "%): $" + descuento);
        System.out.println("");
        System.out.println("TOTAL A PAGAR: $" + totalPagar);
        System.out.println("");
    }
}
