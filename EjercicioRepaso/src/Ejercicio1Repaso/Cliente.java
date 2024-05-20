package Ejercicio1Repaso;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
