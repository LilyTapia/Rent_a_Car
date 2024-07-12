package model;

public class Cliente {

    private String nombre;
    private String rut;
    private String direccion;
    private String telefono;

    public Cliente(String nombre, String rut, String direccion, String telefono) {
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y Setters
    @Override
    public String toString() {
        return "Cliente [nombre=" + nombre + ", rut=" + rut + ", direccion=" + direccion + ", telefono=" + telefono + "]";
    }
}
