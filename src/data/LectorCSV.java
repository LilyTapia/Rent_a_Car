/*
 * La clase LectorCSV gestiona la lectura y escritura de archivos CSV usando clases como BufferedReader y FileWriter, 
 * indicando que permite leer, escribir o modificar archivos CSV. 
 */

package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {

    // Método estático para leer un archivo CSV y devolver los datos como una lista de arrays de cadenas
    public static List<String[]> leerCSV(String archivo) throws IOException {
        List<String[]> datos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");  // Dividir la línea por comas para obtener los valores individuales
                datos.add(valores);  // Agregar el array de valores a la lista de datos
            }
        }
        return datos;  // Devolver la lista de arrays de cadenas con los datos del CSV
    }

    // Método estático para escribir datos en un archivo CSV
    public static void escribirCSV(String archivo, List<String[]> datos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (String[] fila : datos) {
                bw.write(String.join(",", fila));  // Unir los valores de la fila con comas y escribirlos en el archivo
                bw.newLine();  // Escribir una nueva línea después de cada fila
            }
        }
    }
}
