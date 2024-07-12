package model;

public class VehiculoPasajeros extends Vehiculo {

    private int numeroPasajeros;

    public VehiculoPasajeros(String patente, String marca, String modelo, double precioDiario, int numeroPasajeros) {
        super(patente, marca, modelo, precioDiario);
        this.numeroPasajeros = numeroPasajeros;
    }

    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    @Override
    public String getDetalles() {
        return "VehiculoPasajeros [patente=" + patente + ", marca=" + marca + ", modelo=" + modelo + ", precioDiario=" + precioDiario + ", numeroPasajeros=" + numeroPasajeros + "]";
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
