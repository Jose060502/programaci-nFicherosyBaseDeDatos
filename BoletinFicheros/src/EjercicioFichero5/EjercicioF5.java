package EjercicioFichero5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

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
                case 4:
                    mostrarFicheros();
                    break;
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

        if(!ficheroABorrar.exists()){
            System.out.println("El nombre del fichero no existe");
        } else {
            if(ficheroABorrar.delete()) {
                System.out.println("Se ha borrado el fichero " + nombre + "correctamente");
            }else {
                System.out.println("El fichero no ha podido ser borrado");
            }
        }
    }

    public static void mostrarFicheros(){
        String nombre = MiEntradaSalida.leerCadena("Elija el nombre del directorio");
        File directorio = new File("./src/Ejercicio5/" + nombre);

        if (directorio.exists()){
            if (directorio.isDirectory()){
                File[] fichero = directorio.listFiles();
                if (fichero.length > 0){
                    for (File fich : fichero){
                        System.out.println("Nombre fichero: " + fich.getName());
                    }
                }else{
                    System.out.println("El directorio esta vacio");
                }
            }
        }else {
            System.out.println("El directorio no existe");
        }
    }

    public static void mostrarFicheros2(){
        String nombre = MiEntradaSalida.leerCadena("Elija el nombre del directorio");
        File directorio = new File("./src/Ejercicio5/" + nombre);

        if (directorio.exists()){
            if (directorio.isDirectory()){
                File[] ficheros = directorio.listFiles(File::isFile);
                if (ficheros.length > 0){
                    for (File fich : ficheros){
                        System.out.println("Nombre fichero: " + fich.getName());
                    }
                }else{
                    System.out.println("El directorio esta vacio");
                }
            }
        }else {
            System.out.println("El directorio no existe");
        }
    }

    public static void mostrarFicheros3() throws IOException {

        String nombre = MiEntradaSalida.leerCadena("Elija el nombre del directorio");
        Path path = Paths.get("./src/Ejercicio5/" + nombre);

        if (Files.exists(path) && Files.isDirectory(path)){
            try(Stream<Path> f = Files.list(path)) {
                f.filter(Files::isRegularFile).forEach(System.out::println);
            }
        }else {
            System.out.println("El directorio no existe");
        }
    }
}
