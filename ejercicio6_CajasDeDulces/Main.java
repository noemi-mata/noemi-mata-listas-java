/**
 * Problema: Cajas de dulces
 * Autor: Noemi Mata
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
 * Lee N cajas y un número K, e imprime la suma máxima de dulces
 * de K cajas consecutivas.
 */
public class Main {

    private static StringTokenizer tokens; // números de la línea actual

    /**
     * Carga las cajas en la lista y calcula la suma máxima de k cajas consecutivas.
     * @param k cantidad de cajas que se pueden llevar
     * @param cajas dulces de cada caja, en orden
     * @return suma máxima de dulces
     */
    static int procesar(int k, int[] cajas) {
        ListaSimple lista = new ListaSimple();
        for (int dulces : cajas) {
            lista.agregarFinal(dulces);
        }
        return lista.sumaMaximaVentana(k);
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
     * @param k cantidad de cajas que se pueden llevar
     * @param cajas dulces de cada caja
     */
    static void mostrarCaso(String titulo, int k, int[] cajas) {
        System.out.println("=== " + titulo + " ===");
        System.out.println("Entrada:");
        System.out.println(cajas.length + " " + k);
        StringBuilder linea = new StringBuilder();
        for (int i = 0; i < cajas.length; i++) {
            linea.append(cajas[i]);
            if (i < cajas.length - 1) {
                linea.append(' ');
            }
        }
        System.out.println(linea);
        System.out.println("Salida:");
        System.out.println(procesar(k, cajas));
        System.out.println();
    }

    /**
     * Ejecuta todos los casos por defecto (se usa cuando no hay entrada).
     */
    static void ejecutarCasosPorDefecto() {
        System.out.println("No se ingresó entrada: se ejecutan los casos por defecto.\n");

        // Caso 1: normal (primer ejemplo del enunciado)
        mostrarCaso("Caso 1: normal (enunciado)", 4, new int[]{3, 9, 9, 1, 9, 10, 8, 7, 2, 16});

        // Caso 2: normal (segundo ejemplo del enunciado)
        mostrarCaso("Caso 2: normal (enunciado)", 2, new int[]{9, 5, 2, 10, 3});

        // Caso 3: límite, K = N (se lleva todas las cajas)
        mostrarCaso("Caso 3: limite (K = N)", 5, new int[]{1, 2, 3, 4, 5});

        // Caso 4: límite, K = 1 (gana la caja más grande)
        mostrarCaso("Caso 4: limite (K = 1)", 1, new int[]{4, 9, 2, 9, 1, 7});

        // Caso 5: límite, una sola caja
        mostrarCaso("Caso 5: limite (N = 1)", 1, new int[]{500});

        // Caso 6: la mejor ventana está al inicio
        mostrarCaso("Caso 6: mejor ventana al inicio", 3, new int[]{8, 9, 7, 1, 1});

        // Caso 7: la mejor ventana está al final
        mostrarCaso("Caso 7: mejor ventana al final", 3, new int[]{1, 1, 1, 1, 9, 9});

        // Caso 8: grande, N = 1000 y K = 500 (se muestra solo el resumen)
        // Valores 1..500 y luego 500..1: la mejor ventana queda centrada en el pico
        int n = 1000;
        int[] grande = new int[n];
        for (int i = 0; i < n; i++) {
            grande[i] = (i < 500) ? i + 1 : 1000 - i;
        }
        long inicio = System.nanoTime();
        int resultado = procesar(500, grande);
        long milisegundos = (System.nanoTime() - inicio) / 1_000_000;
        System.out.println("=== Caso 8: grande (N = " + n + ", K = 500) ===");
        System.out.println("Suma máxima: " + resultado + " (esperado: 187750)");
        System.out.println("Tiempo: " + milisegundos + " ms");
    }

    /**
     * Punto de entrada: lee N, K y las N cajas de la entrada estándar.
     * Si no hay entrada, ejecuta los casos por defecto.
     * @param args no se usa
     * @throws IOException si falla la lectura de la entrada estándar
     */
    public static void main(String[] args) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Escribe N y K, luego las N cajas (Enter solo = casos por defecto): ");

        String primera = lector.readLine();
        // Sin entrada (null) o Enter sin escribir nada: casos por defecto
        if (primera == null || primera.trim().isEmpty()) {
            System.out.println();
            ejecutarCasosPorDefecto();
            return;
        }

        tokens = new StringTokenizer(primera);
        int n = Integer.parseInt(siguienteToken(lector));
        int k = Integer.parseInt(siguienteToken(lector));
        int[] cajas = new int[n];
        for (int i = 0; i < n; i++) {
            cajas[i] = Integer.parseInt(siguienteToken(lector));
        }
        System.out.println(procesar(k, cajas));
    }
}