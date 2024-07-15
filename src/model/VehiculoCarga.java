/*
 * La clase VehiculoCarga es una extensión de la clase abstracta Vehiculo y también implementa la interfaz ArriendoInterface. 
 * Está diseñada para representar vehículos de carga dentro de un sistema de renta de vehículos.
 */

package model;

import interfaces.ArriendoInterface;

public class VehiculoCarga extends Vehiculo implements ArriendoInterface {
    private final double capacidadCarga;

    // Constructor de la clase VehiculoCarga
    public VehiculoCarga(String patente, String marca, String modelo, double precioDiario, double capacidadCarga, boolean arriendoLargoPlazo, String estado) {
        super(patente, marca, modelo, precioDiario, arriendoLargoPlazo, estado);
        this.capacidadCarga = capacidadCarga;
    }

    // Getter para obtener la capacidad de carga del vehículo
    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    // Método para obtener detalles específicos del vehículo de carga
    @Override
    public String getDetalles() {
        return "VehiculoCarga{" +
                "patente='" + getPatente() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", precioDiario=" + getPrecioDiario() +
                ", capacidadCarga=" + getCapacidadCarga() +
                ", arriendoLargoPlazo=" + getArriendoLargoPlazo() +
                ", estado='" + getEstado() + '\'' +
                '}';
    }
    
    // Implementación del método de la interfaz ArriendoInterface para calcular y mostrar la boleta
    @Override
    public void calcularYMostrarBoleta() {
        double precioArriendo = getPrecioDiario();
        double iva = precioArriendo * ArriendoInterface.IVA;
        double descuento = precioArriendo * ArriendoInterface.DESCUENTO_CARGA;
        
        double totalPagar = precioArriendo + iva - descuento;

        System.out.println("");
        System.out.println("------------------------");
        System.out.println("Boleta Vehículo de Carga");
        System.out.println("------------------------");
        System.out.println("");
        System.out.println("Patente: " + getPatente());
        System.out.println("Marca: " + getMarca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Capacidad de Carga: " + getCapacidadCarga() + " kg");
        System.out.println("");
        System.out.println("Precio Diario: $" + getPrecioDiario());
        System.out.println("IVA (19%): $" + iva);
        System.out.println("Subtotal: $" + (precioArriendo + iva));
        System.out.println("Descuento (" + (ArriendoInterface.DESCUENTO_CARGA * 100) + "%): $" + descuento);
        System.out.println("");
        System.out.println("TOTAL A PAGAR: $" + totalPagar);
        System.out.println("");
    }
}
