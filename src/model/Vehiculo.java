package model;


public abstract class Vehiculo {
    protected String patente;
    protected String marca;
    protected String modelo;
    protected double precioDiario;

    public Vehiculo(String patente, String marca, String modelo, double precioDiario) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.precioDiario = precioDiario;
    }

    public String getPatente() {
        return patente;
    }

    public abstract String getDetalles();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) obj;
        return patente.equals(vehiculo.patente);
    }

    @Override
    public int hashCode() {
        return patente.hashCode();
    }
}
