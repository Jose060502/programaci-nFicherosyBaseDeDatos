package Ejercicio1Repaso;

import java.time.LocalDateTime;

public class Compra {
    private int id;
    private Cliente cliente;
    private Mascota mascota;
    private LocalDateTime fechaCompra;
    private static int contCompra = 1;

    public Compra( Cliente cliente, Mascota mascota) {
        this.id = contCompra++;
        this.cliente = cliente;
        this.mascota = mascota;
        this.fechaCompra = LocalDateTime.now();
    }
}
