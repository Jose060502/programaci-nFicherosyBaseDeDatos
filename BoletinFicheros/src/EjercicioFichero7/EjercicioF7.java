package EjercicioFichero7;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        String nombreArchivo = leerCadena("Ingrese el nombre del archivo a buscar: ");
        File[] archivos = directorio.listFiles(((dir, name) -> name.equals(nombreArchivo)));

        for (File archivo : archivos) {
            if (archivo.isDirectory()) {
                System.out.println(archivo.getAbsolutePath() + " (directorio)");
            } else {
                System.out.println(archivo.getAbsolutePath() + " - " + archivo.length() / 1024 + " KB");
            }
        }
    }

    public static void buscarArchivoRecursivo() {
        String nombreArchivo = leerCadena("Ingrese el nombre del archivo a buscar: ");
        Path dir = directorio.toPath();

        buscarArchivosRecusivamente(nombreArchivo, dir);

    }

    public static void buscarArchivosRecusivamente(String nombre, Path directorio) {
        try (Stream<Path> ficheros = Files.list(directorio)) {
            ficheros.sorted((a, b) -> {
                        if (Files.isRegularFile(a) && Files.isRegularFile(b)) {
                            return -1;
                        } else if (Files.isRegularFile(b) && Files.isRegularFile(a)) {
                            return 1;
                        } else {
                            return 0;
                }
                    })
                    .forEach(a -> {
                        if (Files.isDirectory(a)) {
                            buscarArchivosRecusivamente(nombre, a);
                        } else {
                            if (a.endsWith(Paths.get(nombre))) {
                                try {
                                    System.out.println(a.toAbsolutePath() + " - " + Files.size(a) / 1024 + " KB");
                                } catch (IOException e) {
                                    System.out.println("Ocurrio un error al acceder al fichero");
                                }
                            }
                }
                    });
        } catch (IOException e) {
            System.out.println("Ocurrio un error al acceder al fichero");
        }
    }
}