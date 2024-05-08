package Ejercicio1Repaso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tienda {
    private String nombre;
    private List<Mascota> catalogoMascotas;
    private List<Cliente> listaClientes;
    private List<Compra> listaCompra;

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public Tienda(String nombre) {
        this.nombre = nombre;
        this.catalogoMascotas = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
        this.listaCompra = new ArrayList<>();
    }

    public void annadirCliente(String nombre){
        Cliente cliente = new Cliente(nombre);
        listaClientes.add(cliente);
    }

    public void annadirMascota(String nombre, Especie especie, int edad, double precio, boolean disponible){
        Mascota mascota = new Mascota(nombre, especie, edad, precio, disponible);
        catalogoMascotas.add(mascota);
    }

    public void comprarMascota(Cliente cliente , Mascota mascota){
        Compra compra = new Compra(cliente, mascota);
        listaCompra.add(compra);
    }

}
