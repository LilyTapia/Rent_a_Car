/*
 * La clase Vehiculo es una clase abstracta que representa un vehículo con atributos como patente, marca, modelo, precio diario, si es para arriendo de largo plazo y estado. 
 * Incluye un constructor para inicializar estos atributos y métodos getter y setter para acceder y modificarlos.
 */

package model;

// Clase abstracta para representar un vehículo
public abstract class Vehiculo {
    private String patente;
    private String marca;
    private String modelo;
    private double precioDiario;
    private boolean arriendoLargoPlazo;
    private String estado;

    // Constructor de la clase Vehiculo
    public Vehiculo(String patente, String marca, String modelo, double precioDiario, boolean arriendoLargoPlazo, String estado) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.precioDiario = precioDiario;
        this.arriendoLargoPlazo = arriendoLargoPlazo;
        this.estado = estado;
    }

    // Métodos getter y setter para los atributos de la clase
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

    public Boolean getArriendoLargoPlazo() {
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

    // Método abstracto para obtener detalles específicos del vehículo
    public abstract String getDetalles();

    // Sobrescritura de métodos equals y hashCode para comparar vehículos por patente
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
