package Ejercicio1Repaso;

public class Cliente {
    private String nombre;
    private int id;
    private static int contCliente = 1;

    public String getNombre() {
        return nombre;
    }

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.id = contCliente++;
    }

}
