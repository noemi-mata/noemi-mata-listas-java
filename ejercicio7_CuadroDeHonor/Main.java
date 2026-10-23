/**
 * Problema: Cuadro de Honor
 * Autor: Noemi Mata
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 *              Si no se ingresa entrada, ejecuta casos de prueba por defecto.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Lee N alumnos con su promedio e imprime, en orden alfabético,
 * los nombres de quienes tienen 9.5 o más.
 */
public class Main {

    /** Promedio mínimo para entrar al cuadro de honor. */
    static final double PROMEDIO_MINIMO = 9.5;

    /**
     * Filtra los alumnos con promedio suficiente y los guarda ordenados en la lista.
     * @param lineas líneas de entrada: nombre, promedio, nombre, promedio...
     * @param alumnos cantidad de alumnos (pares nombre/promedio) en lineas
     * @return nombres del cuadro de honor, uno por línea (vacío si nadie entra)
     */
    static String procesar(String[] lineas, int alumnos) {
        ListaSimple cuadro = new ListaSimple();
        for (int i = 0; i < alumnos; i++) {
            String nombre = lineas[2 * i];
            double promedio = Double.parseDouble(lineas[2 * i + 1]);
            if (promedio >= PROMEDIO_MINIMO) {
                cuadro.insertarOrdenado(nombre); // solo entran los de 9.5 o más
            }
        }

        StringBuilder salida = new StringBuilder();
        Nodo actual = cuadro.getCabeza();
        while (actual != null) {
            salida.append(actual.nombre).append('\n');
            actual = actual.siguiente; // se avanza al siguiente alumno
        }
        return salida.toString();
    }

    /**
     * Devuelve la siguiente línea con contenido, saltando las líneas en blanco.
     * @param lector lector de la entrada estándar
     * @return la línea sin espacios en los extremos, o null si ya no hay entrada
     * @throws IOException si falla la lectura
     */
    static String siguienteLinea(BufferedReader lector) throws IOException {
        String linea = lector.readLine();
        while (linea != null && linea.trim().isEmpty()) {
            linea = lector.readLine();
        }
        return (linea == null) ? null : linea.trim();
    }

    /**
     * Muestra un caso por defecto: su entrada y su salida.
     * @param titulo nombre del caso
     * @param lineas nombre y promedio de cada alumno, en líneas alternadas
     */
    static void mostrarCaso(String titulo, String[] lineas) {
        int alumnos = lineas.length / 2;
        System.out.println("=== " + titulo + " ===");
        System.out.println("Entrada:");
        System.out.println(alumnos);
        for (String linea : lineas) {
            System.out.println(linea);
        }
        System.out.println("Salida:");
        String resultado = procesar(lineas, alumnos);
        System.out.print(resultado.isEmpty() ? "(vacía: nadie alcanzó el cuadro de honor)\n" : resultado);
        System.out.println();
    }

    /**
     * Ejecuta todos los casos por defecto (se usa cuando no hay entrada).
     */
    static void ejecutarCasosPorDefecto() {
        System.out.println("No se ingresó entrada: se ejecutan los casos por defecto.\n");

        // Caso 1: normal (el del enunciado)
        mostrarCaso("Caso 1: normal (enunciado)", new String[]{
            "Pepito Perez", "9.6", "Julian Cardenas", "10.0", "Felix Garduza", "9.9",
            "Martin Gonzalez", "9.2", "Jose Soto", "8.5"});

        // Caso 2: límite, nadie llega a 9.5
        mostrarCaso("Caso 2: limite (nadie entra)", new String[]{
            "Ana Lopez", "9.4", "Luis Mora", "9.49"});

        // Caso 3: límite, exactamente 9.5 sí entra
        mostrarCaso("Caso 3: limite (9.5 exacto)", new String[]{
            "Zoe Ruiz", "9.5", "Ana Gil", "9.50"});

        // Caso 4: límite, un solo alumno que sí entra
        mostrarCaso("Caso 4: limite (un solo alumno)", new String[]{
            "Carlos Vega", "10.0"});

        // Caso 5: todos entran y llegan en orden alfabético inverso
        mostrarCaso("Caso 5: orden inverso", new String[]{
            "Zacarias Luna", "10.0", "Maria Diaz", "9.8", "Beto Cruz", "9.6"});

        // Caso 6: grande, N = 1000; la mitad entra (se muestra el resumen)
        int n = 1000;
        String[] lineas = new String[2 * n];
        for (int i = 0; i < n; i++) {
            lineas[2 * i] = String.format("Alumno %04d", 1000 - i);
            lineas[2 * i + 1] = (i % 2 == 0) ? "10.0" : "9.0";
        }
        long inicio = System.nanoTime();
        String[] salida = procesar(lineas, n).split("\n");
        long milisegundos = (System.nanoTime() - inicio) / 1_000_000;
        System.out.println("=== Caso 6: grande (N = " + n + ") ===");
        System.out.println("Alumnos en el cuadro: " + salida.length + " (esperado: 500)");
        System.out.println("Primero: " + salida[0] + " (esperado: Alumno 0002)");
        System.out.println("Último:  " + salida[salida.length - 1] + " (esperado: Alumno 1000)");
        System.out.println("Tiempo: " + milisegundos + " ms");
    }

    /**
     * Punto de entrada: lee N y, por cada alumno, su nombre y su promedio.
     * Si no hay entrada, ejecuta los casos por defecto.
     * @param args no se usa
     * @throws IOException si falla la lectura de la entrada estándar
     */
    public static void main(String[] args) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Escribe N y luego, por alumno, una línea con el nombre y otra con el promedio (Enter solo = casos por defecto): ");

        String primera = lector.readLine();
        // Sin entrada (null) o Enter sin escribir nada: casos por defecto
        if (primera == null || primera.trim().isEmpty()) {
            System.out.println();
            ejecutarCasosPorDefecto();
            return;
        }

        int n = Integer.parseInt(primera.trim());
        String[] lineas = new String[2 * n];
        int leidas = 0;
        while (leidas < 2 * n) {
            String linea = siguienteLinea(lector);
            if (linea == null) {
                break; // la entrada terminó antes de lo esperado
            }
            lineas[leidas++] = linea;
        }
        System.out.print(procesar(lineas, leidas / 2));
    }
}