// model/Vehiculo.java

package model;

public abstract class Vehiculo {
    protected String patente;
    protected String marca;
    protected String modelo;
    protected double precioDiario;
    protected boolean arriendoLargoPlazo;
    protected String estado;

    public Vehiculo(String patente, String marca, String modelo, double precioDiario, boolean arriendoLargoPlazo, String estado) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.precioDiario = precioDiario;
        this.arriendoLargoPlazo = arriendoLargoPlazo;
        this.estado = estado;
    }

    public String getPatente() {
        return this.patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecioDiario() {
        return this.precioDiario;
    }

    public void setPrecioDiario(double precioDiario) {
        this.precioDiario = precioDiario;
    }

    public boolean isArriendoLargoPlazo() {
        return this.arriendoLargoPlazo;
    }

    public void setArriendoLargoPlazo(boolean arriendoLargoPlazo) {
        this.arriendoLargoPlazo = arriendoLargoPlazo;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
