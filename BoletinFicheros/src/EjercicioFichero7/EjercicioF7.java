package EjercicioFichero7;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import static EjercicioFichero5.MiEntradaSalida.leerCadena;
import static EjercicioFichero5.MiEntradaSalida.leerEnteroDeRango;

public class EjercicioF7 {

    static File directorio = new File("/Users/alumno/Desktop");
    public static void main(String[] args) {

        int opcion;

        do {
            menu();
            opcion = leerEnteroDeRango("Elija una opción", 1, 6);

            switch (opcion) {
                case 1:
                    listarDirectorio();
                    break;
                case 2:
                    listarDirectorioPorNombre();
                    break;
                case 3:
                    listarPorExtension();
                    break;
                case 4:
                    buscarArchivo();
                    break;
                case 5:
                    buscarArchivoRecursivo();
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 6);


    }

    public static void menu() {
        System.out.println("\nMenú:");
        System.out.println("1. Listar directorio");
        System.out.println("2. Listar directorio y buscar ficheros por nombre");
        System.out.println("3. Listar archivos con cierta extensión");
        System.out.println("4. Buscar un archivo en un directorio");
        System.out.println("5. Buscar recursivamente un archivo en un directorio");
        System.out.println("6. Salir");
    }

    public static void listarDirectorio() {
        String nombreDirectorio = leerCadena("Ingrese el nombre del directorio:");
        File directorio = new File(nombreDirectorio);

        if (directorio.exists() && directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isDirectory()) {
                        System.out.println(archivo.getName() + " (Directorio)");
                    } else {
                        System.out.println(archivo.getName() + " - " + archivo.length() / 1024 + " KB");
                    }
                }
            } else {
                System.out.println("El directorio está vacío.");
            }
        } else {
            System.out.println("El directorio no existe o no es válido.");
        }
    }

    public static void listarDirectorioPorNombre() {
        String palabraFiltrar = leerCadena("Ingrese el nombre del directorio que vas a filtrar");

        File[] files = directorio.listFiles((dir, name) -> name.startsWith(palabraFiltrar));

        for (File file : files){
            if (file.isDirectory()){
                System.out.println(file.getName() + "(directorio)");
            }else{
                System.out.println(file.getName() + " - " + file.length() / 1024 + " KB");
            }
        }

    }

    public static void listarPorExtension() {
        String palabraFiltrar = leerCadena("Ingrese el nombre del directorio que vas a filtrar");
        Path  nombreDirectorio = Paths.get("/Users/alumno/Desktop");

        try(Stream<Path> ficheros = Files.list(nombreDirectorio)){
            ficheros.filter(Files::isRegularFile)
                    .filter(a -> a.endsWith("." + palabraFiltrar))
                    .forEach(a -> {
                try {
                    System.out.println(a.getFileName().toString() + " - " + Files.size(a) / 1024 + " KB");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            System.out.println("Ocurrió un error al acceder al fichero");
        }
    }

    public static void buscarArchivo() {
        String nombreDirectorio = leerCadena("Ingrese el nombre del directorio:");
        String nombreArchivo = leerCadena("Ingrese el nombre del archivo:");
        File directorio = new File(nombreDirectorio);

        if (directorio.exists() && directorio.isDirectory()) {
            buscarArchivoEnDirectorio(directorio, nombreArchivo);
        } else {
            System.out.println("El directorio no existe o no es válido.");
        }
    }

    private static void buscarArchivoEnDirectorio(File directorio, String nombreArchivo) {
        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().equalsIgnoreCase(nombreArchivo)) {
                    System.out.println("Archivo encontrado: " + archivo.getAbsolutePath() + " - " + archivo.length() / 1024 + " KB");
                    return;
                }
            }
            // Si no se encuentra en este directorio, buscar en subdirectorios
            for (File subDirectorio : archivos) {
                if (subDirectorio.isDirectory()) {
                    buscarArchivoEnDirectorio(subDirectorio, nombreArchivo);
                }
            }
        }
    }

    public static void buscarArchivoRecursivo() {
        String nombreDirectorio = leerCadena("Ingrese el nombre del directorio:");
        String nombreArchivo = leerCadena("Ingrese el nombre del archivo:");
        File directorio = new File(nombreDirectorio);

        if (directorio.exists() && directorio.isDirectory()) {
            buscarArchivoRecursivamenteEnDirectorio(directorio, nombreArchivo);
        } else {
            System.out.println("El directorio no existe o no es válido.");
        }
    }

    private static void buscarArchivoRecursivamenteEnDirectorio(File directorio, String nombreArchivo) {
        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().equalsIgnoreCase(nombreArchivo)) {
                    System.out.println("Archivo encontrado: " + archivo.getAbsolutePath() + " - " + archivo.length() / 1024 + " KB");
                }
            }
            // Buscar en subdirectorios recursivamente
            for (File subDirectorio : archivos) {
                if (subDirectorio.isDirectory()) {
                    buscarArchivoRecursivamenteEnDirectorio(subDirectorio, nombreArchivo);
                }
            }
        }
    }
}