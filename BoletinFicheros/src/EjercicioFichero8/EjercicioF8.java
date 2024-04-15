package EjercicioFichero8;

import EjercicioFichero5.MiEntradaSalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EjercicioF8 {
    public static void main(String[] args) {
        // Verificar si se proporcionó un nombre de archivo como argumento
        String nombreArchivo = MiEntradaSalida.leerCadena("Elija el nombre del archivo que quiera");

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int numeroLinea = 0;

            while ((linea = br.readLine()) != null) {
                numeroLinea++;

                // Verificar el formato de la línea
                if (!verificarFormatoLinea(linea)) {
                    System.out.println("Formato incorrecto en la línea " + numeroLinea + ": " + linea);
                    return;
                }
            }

            System.out.println("El archivo cumple con el formato especificado.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static boolean verificarFormatoLinea(String linea) {
        // Dividir la línea en partes separadas por espacios
        String[] partes = linea.split(" ");

        // Verificar si hay tres partes separadas por espacios
        if (partes.length != 3) {
            return false;
        }

        // Verificar la longitud de los nombres y apellidos
        for (String parte : partes) {
            if (parte.length() < 2 || !parte.matches("[a-zA-Z]+")) {
                return false;
            }
        }

        // Verificar si la última parte es un número válido en el rango de 1 a 99
        try {
            int edad = Integer.parseInt(partes[2]);
            if (edad < 1 || edad > 99) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
