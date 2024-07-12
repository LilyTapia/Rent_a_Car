package model;

public class VehiculoCarga extends Vehiculo {

    private double capacidadCarga;

    public VehiculoCarga(String patente, String marca, String modelo, double precioDiario, double capacidadCarga) {
        super(patente, marca, modelo, precioDiario);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    @Override
    public String getDetalles() {
        return "VehiculoCarga [patente=" + patente + ", marca=" + marca + ", modelo=" + modelo + ", precioDiario=" + precioDiario + ", capacidadCarga=" + capacidadCarga + "]";
    }

    public String getMarca() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getModelo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getPrecioDiario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
