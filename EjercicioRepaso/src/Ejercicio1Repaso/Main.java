package Ejercicio1Repaso;

import java.util.List;

public class Main {
    private static Tienda tienda = new Tienda("AnimalCrossingShop");
    public static void main(String[] args) {
        addCliente();
        addMascota();
    }

    public static void addCliente(){
        tienda.annadirCliente(MiEntradaSalida.leerCadena("Introduce el nombre del cliente"));
    }

    public static void addMascota(){
        String nombre = MiEntradaSalida.leerCadena("Introduce el nombre de la mascota");
        int edad = MiEntradaSalida.leerEnteroPositivo("Introduce la edad de la mascota");
        double precio = MiEntradaSalida.leerDoublePositivo("Introduce el precio de la mascota");
        Especie especie = MiEntradaSalida.leerEnum("Que tipo de mascota es", Especie.values());
        boolean disponible = MiEntradaSalida.leerSN("¿Está disponible para la venta?") == 'S';
        tienda.annadirMascota(nombre, especie, edad, precio, disponible);
    }

    public static void addCompra(){
        List<Cliente> clientes = tienda.getListaClientes();
        Cliente c = MiEntradaSalida.leerOpciones("Selecciona el cliente", clientes.stream().map());
    }
}
