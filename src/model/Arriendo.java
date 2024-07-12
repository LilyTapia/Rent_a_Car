package model;

public class Arriendo {

    private String fecha;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private int dias;
    private double total;

    public Arriendo(String fecha, Cliente cliente, Vehiculo vehiculo, int dias, double total) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.dias = dias;
        this.total = total;
    }

    // Getters y Setters
    @Override
    public String toString() {
        return "Arriendo [fecha=" + fecha + ", cliente=" + cliente + ", vehiculo=" + vehiculo + ", dias=" + dias + ", total=" + total + "]";
    }
}
