package ExamenFicheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ejercicio1examen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la carpeta origen");
        Path origen = Paths.get(sc.nextLine());
        System.out.println("Introduce la carpeta destino");
        Path destino = Paths.get(sc.nextLine());



        if (!Files.isDirectory(origen)){
            System.out.println("La ruta origen no es un directorio");
            return;
        } else {
            if (!Files.isReadable(origen)){
                System.out.println("El directorio de origen no tiene permimso de lectura");
                return;
            }
        }
        if (!Files.isDirectory(destino)){
            System.out.println("La ruta destino no es un directorio");
            return;
        }else{
            if (!Files.isWritable(destino)){
                System.out.println("El directorio destino no tiene permisos de escritura");
                return;
            }
        }

        try(Stream<Path> st = Files.list(origen)){

            st.filter(path -> {
                try {
                    if(Files.size(path) > 1024 && Files.isRegularFile(path) && path.getFileName().toString().endsWith(".txt")){
                        try(BufferedReader br = Files.newBufferedReader(path)){
                            if (br.readLine().toLowerCase().startsWith("copiar")){
                                return true;
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error al leer el fichero");
                }
                return false;
            }).forEach(path -> {
                try {
                    Files.copy(path , destino.resolve(path.getFileName()));
                } catch (IOException e) {
                    System.out.println("Error al copiar el fichero " + path.getFileName());
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
