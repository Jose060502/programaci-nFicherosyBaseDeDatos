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

    public boolean isDisponible() {
        return disponible;
    }

    public String getNombre() {
        return nombre;
    }

    public Especie getEspecie() {
        return especie;
    }

    public int getEdad() {
        return edad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Mascota{");
        sb.append("nombre='").append(nombre).append('\'');
        sb.append(", especie=").append(especie);
        sb.append(", edad=").append(edad);
        sb.append(", precio=").append(precio);
        sb.append(", disponible=").append(disponible);
        sb.append('}');
        return sb.toString();
    }
}
