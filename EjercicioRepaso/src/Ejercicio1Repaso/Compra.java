package Ejercicio1Repaso;

import java.time.LocalDateTime;

public class Compra {
    private int id;
    private Cliente cliente;
    private Mascota mascota;
    private LocalDateTime fechaCompra;
    private LocalDateTime fechaDevoulucion;
    private static int contCompra = 1;

    public Compra( Cliente cliente, Mascota mascota) {
        this.id = contCompra++;
        this.cliente = cliente;
        this.mascota = mascota;
        this.fechaCompra = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public static int getContCompra() {
        return contCompra;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Compra{");
        sb.append("id=").append(id);
        sb.append(", cliente=").append(cliente);
        sb.append(", mascota=").append(mascota);
        sb.append(", fechaCompra=").append(fechaCompra);
        sb.append('}');
        return sb.toString();
    }

    public void marcarDevolucion(){
        this.fechaDevoulucion = LocalDateTime.now();
    }
}
