package EjercicioFichero6;

import EjercicioFichero5.MiEntradaSalida;

import java.io.File;

public class EjercicioF6 {

    public static void main(String[] args) {

        String nombre = MiEntradaSalida.leerCadena("Elija un directorio que quiere listar");
        File directorio = new File(nombre);

        if (directorio.exists() && directorio.isDirectory()) {
            listarArchivosEnDirectorio(directorio);
        } else {
            System.out.println("El directorio no existe o no es válido.");
        }

    }

    public static void listarArchivosEnDirectorio(File directorio) {
        File[] archivos = directorio.listFiles();

        if (archivos == null || archivos.length == 0) {
            System.out.println("El directorio está vacío.");
            return;
        }

        for (File archivo : archivos) {
            if (archivo.isDirectory()) {
                System.out.println(archivo.getName() + " (Directorio)");
            } else {
                System.out.println(archivo.getName() + " - " + archivo.length() / 1024 + " KB");
            }
        }
    }
}

