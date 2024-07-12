package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {

    public static List<String[]> leerCSV(String archivo) throws IOException {
        List<String[]> datos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean isFirstLine = true;
            while ((linea = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Omitir la primera línea
                    continue;
                }
                String[] valores = linea.split(",");
                datos.add(valores);
            }
        }
        return datos;
    }

    public static void escribirCSV(String archivo, List<String[]> datos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (String[] fila : datos) {
                bw.write(String.join(",", fila));
                bw.newLine();
            }
        }
    }
}
