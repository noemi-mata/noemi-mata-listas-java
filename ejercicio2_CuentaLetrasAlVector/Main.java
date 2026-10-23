/**
 * Problema: cuenta_letras_al_vector
 * Autor: Noemi Mata
 * Grupo: GITID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 *              Si no se ingresa entrada, ejecuta casos de prueba por defecto.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Lee una cadena de minúsculas e imprime cuántas veces aparece cada letra.
 */
public class Main {

    /**
     * Cambiar a true SOLO al enviar a omegaUp. Con true, una entrada vacía
     * se trata como cadena vacía (entrada válida) y no se corren los casos por defecto.
     */
    static final boolean MODO_OMEGAUP = false;

    /**
     * Cuenta las letras de la cadena usando una lista simple de 26 nodos.
     * @param cadena texto en minúsculas (puede estar vacío)
     * @return salida del problema: línea con los 26 contadores y una línea por letra presente
     */
    static String procesar(String cadena) {
        // 1. Crear la lista con las 26 letras en cero
        ListaSimple letras = new ListaSimple();
        letras.crearAbecedario();

        // 2. Contar cada carácter de la cadena
        for (int i = 0; i < cadena.length(); i++) {
            letras.incrementar(cadena.charAt(i));
        }

        // 3. Primera línea: los 26 contadores separados por espacio
        StringBuilder salida = new StringBuilder();
        Nodo actual = letras.getCabeza();
        while (actual != null) {
            salida.append(actual.cantidad);
            if (actual.siguiente != null) {
                salida.append(' ');
            }
            actual = actual.siguiente;
        }
        salida.append('\n');

        // 4. Una línea por cada letra que sí apareció
        actual = letras.getCabeza();
        while (actual != null) {
            if (actual.cantidad > 0) {
                salida.append("La letra ").append(actual.letra)
                      .append(" aparece ").append(actual.cantidad)
                      .append(" veces\n");
            }
            actual = actual.siguiente;
        }
        return salida.toString();
    }

    /**
     * Muestra un caso por defecto: su entrada y su salida.
     * @param titulo nombre del caso
     * @param cadena cadena de entrada del caso
     */
    static void mostrarCaso(String titulo, String cadena) {
        System.out.println("=== " + titulo + " ===");
        System.out.println("Entrada:");
        System.out.println(cadena.isEmpty() ? "(cadena vacía)" : cadena);
        System.out.println("Salida:");
        System.out.print(procesar(cadena));
        System.out.println();
    }

    /**
     * Ejecuta todos los casos por defecto (se usa cuando no hay entrada).
     */
    static void ejecutarCasosPorDefecto() {
        System.out.println("No se ingresó entrada: se ejecutan los casos por defecto.\n");

        // Caso 1: normal (primer ejemplo del enunciado)
        mostrarCaso("Caso 1: normal", "programacion");

        // Caso 2: normal con repeticiones (segundo ejemplo del enunciado)
        mostrarCaso("Caso 2: con repeticiones", "aaaaacccceeegghxxzzz");

        // Caso 3: límite, cadena vacía (0 caracteres)
        mostrarCaso("Caso 3: limite (cadena vacia)", "");

        // Caso 4: límite, un solo carácter
        mostrarCaso("Caso 4: limite (un caracter)", "z");

        // Caso 5: grande, 1000 caracteres (se muestra solo el resumen)
        String abecedario = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder grande = new StringBuilder();
        while (grande.length() < 1000) {
            grande.append(abecedario);
        }
        grande.setLength(1000); // exactamente 1000 caracteres
        String resultado = procesar(grande.toString());
        String[] lineas = resultado.split("\n");
        System.out.println("=== Caso 5: grande (1000 caracteres) ===");
        System.out.println("Primera línea de la salida:");
        System.out.println(lineas[0]);
        System.out.println("Letras que aparecen: " + (lineas.length - 1) + " (esperado: 26)");
    }

    /**
     * Punto de entrada: lee una cadena de la entrada estándar.
     * Si no hay entrada, ejecuta los casos por defecto.
     * @param args no se usa
     * @throws IOException si falla la lectura de la entrada estándar
     */
    public static void main(String[] args) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(MODO_OMEGAUP ? "" : "Escribe una cadena en minúsculas (Enter solo = casos por defecto): ");
        String cadena = lector.readLine();

        // Sin entrada (null) o Enter sin escribir nada: casos por defecto
        if (!MODO_OMEGAUP && (cadena == null || cadena.trim().isEmpty())) {
            System.out.println();
            ejecutarCasosPorDefecto();
            return;
        }

        if (cadena == null) {
            cadena = ""; // en omegaUp, entrada vacía = cadena de 0 caracteres
        }
        System.out.print(procesar(cadena.trim()));
    }
}