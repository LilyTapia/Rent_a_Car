// model/VehiculoCarga.java

package model;

import interfaces.ArriendoInterface;

public class VehiculoCarga extends Vehiculo implements ArriendoInterface {
    private double capacidadCarga;

    public VehiculoCarga(String patente, String marca, String modelo, double precioDiario, double capacidadCarga, boolean arriendoLargoPlazo, String estado) {
        super(patente, marca, modelo, precioDiario, arriendoLargoPlazo, estado);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }
    
    

    @Override
    public String getDetalles() {
        return "VehiculoCarga{" +
                "patente='" + patente + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precioDiario=" + precioDiario +
                ", capacidadCarga=" + capacidadCarga +
                ", arriendoLargoPlazo=" + arriendoLargoPlazo +
                ", estado=" + estado +
                '}';
    }
    
    @Override
    public void calcularYMostrarBoleta() {
        double precioArriendo = getPrecioDiario();
        double iva = precioArriendo * ArriendoInterface.IVA;
        double descuento = precioArriendo * ArriendoInterface.DESCUENTO_CARGA;
        
        double totalPagar = precioArriendo + iva - descuento;

        System.out.println("");
        System.out.println("------------------------");
        System.out.println("Boleta Vehiculo de Carga");
        System.out.println("------------------------");
        System.out.println("");
        System.out.println("Patente: " + this.patente);
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Cap. Carga: " + getCapacidadCarga());
        System.out.println("");
        System.out.println("Precio Diario: $" + getPrecioDiario());
        System.out.println("IVA (19%): $" + iva);
        System.out.println("Subtotal: $" + (precioArriendo + iva));
        System.out.println("Descuento (" + (ArriendoInterface.DESCUENTO_CARGA * 100) + "%): $" + descuento);
        System.out.println("");
        System.out.println("TOTAL: $" + totalPagar);
        System.out.println("");
    }
}
