package Ejercicio1Repaso;

public class Mascota {
    private String nombre;
    private Especie especie;
    private int edad;
    private double precio;
    private boolean disponible;

    public Mascota(String nombre, Especie especie, int edad, double precio, boolean disponible) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.precio = precio;
        this.disponible = disponible;
    }
}
