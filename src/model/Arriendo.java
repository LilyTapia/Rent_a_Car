// model/Arriendo.java

package model;

public class Arriendo {

    private String fecha;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private int dias;
    private double total;

    private static final double IVA = 0.19;
    private static final double DESCUENTO_CARGA = 0.03;
    private static final double DESCUENTO_PASAJEROS = 0.07;

    public Arriendo(String fecha, Cliente cliente, Vehiculo vehiculo, int dias, double total) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.dias = dias;
        this.total = total;
    }

    private double calcularTotal() {
        double costoBaseDiario = vehiculo.getPrecioDiario();
        double costoArriendo = costoBaseDiario * dias;
        double totalConIVA = costoArriendo * (1 + IVA);

        if (vehiculo instanceof VehiculoCarga) {
            totalConIVA -= costoArriendo * DESCUENTO_CARGA;
        } else if (vehiculo instanceof VehiculoPasajeros) {
            totalConIVA -= costoArriendo * DESCUENTO_PASAJEROS;
        }

        return totalConIVA;
    }

    // Getters y Setters
    @Override
    public String toString() {
        return "Arriendo [fecha=" + fecha + ", cliente=" + cliente + ", vehiculo=" + vehiculo + ", dias=" + dias + ", total=" + total + "]";
    }
}
