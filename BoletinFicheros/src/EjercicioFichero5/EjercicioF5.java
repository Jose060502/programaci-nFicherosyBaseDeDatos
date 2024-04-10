package EjercicioFichero5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EjercicioF5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            menu();
            opcion = MiEntradaSalida.leerEnteroDeRango("Elija una opción", 1, 5); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    crearDirectorio();
                    break;
                case 2:
                    crearFicheroTexto();
                    break;
                case 3:
                    borrarFichero();
                    break;
                //case 4:
                //    mostrarFicherosEnCarpeta();
                //    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 5);

        scanner.close();
    }

    public static void menu() {
        System.out.println("\nMenú:");
        System.out.println("1. Crear directorio");
        System.out.println("2. Crear fichero de texto");
        System.out.println("3. Borrar fichero");
        System.out.println("4. Mostrar carpeta");
        System.out.println("5. Salir");
    }

    public static void crearDirectorio() {
        String nombre = MiEntradaSalida.leerCadena("Elija el nombre del directorio");
        File directorio = new File("./src/EjercicioFichero5/" + nombre);

        if (directorio.exists()) {
            System.out.println("El directorio ya existe");
        } else {
            if (directorio.mkdir()) {
                System.out.println("Directorio creado correctamente.");
            } else {
                System.out.println("No se pudo crear el directorio.");
            }
        }
    }

    public static void crearFicheroTexto() {
        String nombre = MiEntradaSalida.leerCadena("Elija el nombre del fichero");
        File fichertoTexto = new File("./src/EjercicioFichero5/" + nombre);
        boolean append = true;
        if (fichertoTexto.exists()) {
            if (fichertoTexto.isFile()) {
                append = MiEntradaSalida.leerSN("¿Quieres añadir el contenido al final del fichero?") == 'S';
            } else {
                System.out.println("El fichero no ha podido crearse por ya existe un directorio con el mismo nombre");
                return;
            }
        }

        String cadena = MiEntradaSalida.leerCadena("Escriba la cadena que estará en el fichero");

        try (PrintWriter pw = new PrintWriter(new FileWriter(fichertoTexto, append))) {
            pw.write(cadena);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void borrarFichero() {
        String nombre = MiEntradaSalida.leerCadena("Elija el nombre del fichero que quiere borrar");
        File ficheroABorrar = new File("./src/EjercicioFichero5/" + nombre);

    }
}
