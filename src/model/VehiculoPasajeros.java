// model/VehiculoPasajeros.java

package model;

import interfaces.ArriendoInterface;

public class VehiculoPasajeros extends Vehiculo implements ArriendoInterface {
    private int numeroPasajeros;

    public VehiculoPasajeros(String patente, String marca, String modelo, double precioDiario, int numeroPasajeros, boolean arriendoLargoPlazo, String estado) {
        super(patente, marca, modelo, precioDiario, arriendoLargoPlazo, estado);
        this.numeroPasajeros = numeroPasajeros;
    }

    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    @Override
    public String getDetalles() {
        return "VehiculoPasajeros{" +
                "patente='" + patente + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precioDiario=" + precioDiario +
                ", numeroPasajeros=" + numeroPasajeros +
                ", arriendoLargoPlazo=" + arriendoLargoPlazo +
                ", estado=" + estado +
                '}';
    }

    @Override
    public void calcularYMostrarBoleta() {
        double precioArriendo = getPrecioDiario();
        double iva = precioArriendo * ArriendoInterface.IVA;
        double descuento = precioArriendo * ArriendoInterface.DESCUENTO_PASAJEROS;
        
        double totalPagar = precioArriendo + iva - descuento;

        System.out.println("");
        System.out.println("----------------------------");
        System.out.println("Boleta Vehiculo de Pasajeros");
        System.out.println("----------------------------");
        System.out.println("");
        System.out.println("Patente: " + this.patente);
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Cap. Pasajeros: " + this.numeroPasajeros);
        System.out.println("");
        System.out.println("Precio Diario: $" + getPrecioDiario());
        System.out.println("IVA (19%): $" + iva);
        System.out.println("Subtotal: $" + (precioArriendo + iva));
        System.out.println("Descuento (" + (ArriendoInterface.DESCUENTO_PASAJEROS * 100) + "%): $" + descuento);
        System.out.println("");
        System.out.println("TOTAL: $" + totalPagar);
        System.out.println("");
    }
}
