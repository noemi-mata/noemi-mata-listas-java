/**
 * Problema: Comprando agua
 * Autor: Noemi Mata Ruiz
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 *              Si no se ingresa entrada, ejecuta casos de prueba por defecto.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Lee N precios e imprime el menor, usando una lista simple propia.
 */
public class Main {

    private static StringTokenizer tokens; // números de la línea actual

    /**
     * Procesa un arreglo de precios usando la lista simple.
     * @param precios precios de las marcas
     * @return el menor precio
     */
    static int procesar(int[] precios) {
        ListaSimple lista = new ListaSimple();
        for (int precio : precios) {
            lista.agregarFinal(precio);
        }
        return lista.obtenerMinimo();
    }

    /**
     * Devuelve el siguiente número de la entrada, leyendo más líneas si hace falta.
     * @param lector lector de la entrada estándar
     * @return el siguiente token, o null si ya no hay más entrada
     * @throws IOException si falla la lectura
     */
    static String siguienteToken(BufferedReader lector) throws IOException {
        while (tokens == null || !tokens.hasMoreTokens()) {
            String linea = lector.readLine();
            if (linea == null) {
                return null;
            }
            tokens = new StringTokenizer(linea);
        }
        return tokens.nextToken();
    }

    /**
     * Muestra un caso por defecto: su entrada y su salida.
     * @param titulo nombre del caso
     * @param precios precios del caso
     */
    static void mostrarCaso(String titulo, int[] precios) {
        System.out.println("=== " + titulo + " ===");
        System.out.println("Entrada:");
        System.out.println(precios.length);
        StringBuilder linea = new StringBuilder();
        for (int i = 0; i < precios.length; i++) {
            linea.append(precios[i]);
            if (i < precios.length - 1) {
                linea.append(' ');
            }
        }
        System.out.println(linea);
        System.out.println("Salida:");
        System.out.println(procesar(precios));
        System.out.println();
    }

    /**
     * Ejecuta todos los casos por defecto (se usa cuando no hay entrada).
     */
    static void ejecutarCasosPorDefecto() {
        System.out.println("No se ingresó entrada: se ejecutan los casos por defecto.\n");

        // Caso 1: normal (primer ejemplo del enunciado)
        mostrarCaso("Caso 1: normal", new int[]{9, 10, 8, 7, 11});

        // Caso 2: normal (segundo ejemplo del enunciado)
        mostrarCaso("Caso 2: normal", new int[]{20, 12, 16});

        // Caso 3: límite, una sola marca
        mostrarCaso("Caso 3: limite (una sola marca)", new int[]{500});

        // Caso 4: límite, el menor está al inicio
        mostrarCaso("Caso 4: limite (menor al inicio)", new int[]{1, 50, 99999, 300});

        // Caso 5: límite, el menor está al final
        mostrarCaso("Caso 5: limite (menor al final)", new int[]{900, 800, 700, 2});

        // Caso 6: grande, N = 10000 con precios distintos de 99999 hasta 90000
        int n = 10000;
        int[] grande = new int[n];
        for (int i = 0; i < n; i++) {
            grande[i] = 99999 - i;
        }
        long inicio = System.nanoTime();
        int minimo = procesar(grande);
        long milisegundos = (System.nanoTime() - inicio) / 1_000_000;
        System.out.println("=== Caso 6: grande (N = " + n + ") ===");
        System.out.println("Menor precio: " + minimo + " (esperado: 90000)");
        System.out.println("Tiempo: " + milisegundos + " ms");
    }

    /**
     * Punto de entrada: lee N y los N precios de la entrada estándar.
     * Si no hay entrada, ejecuta los casos por defecto.
     * @param args no se usa
     * @throws IOException si falla la lectura de la entrada estándar
     */
    public static void main(String[] args) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Escribe N y luego los precios (Enter solo = casos por defecto): ");

        String primera = lector.readLine();
        // Sin entrada (null) o Enter sin escribir nada: casos por defecto
        if (primera == null || primera.trim().isEmpty()) {
            System.out.println();
            ejecutarCasosPorDefecto();
            return;
        }

        tokens = new StringTokenizer(primera);
        int n = Integer.parseInt(siguienteToken(lector));
        int[] precios = new int[n];
        for (int i = 0; i < n; i++) {
            precios[i] = Integer.parseInt(siguienteToken(lector));
        }
        System.out.println(procesar(precios));
    }
}