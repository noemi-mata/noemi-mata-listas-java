/**
 * Problema: Formados en la cafetería
 * Autor: Noemi Mata Ruiz
 * Grupo: GTID244
 * Fecha: 22/10/2026
* Descripción: Resuelve el problema usando una lista simple propia.
 *              Si no se ingresa entrada, ejecuta casos de prueba por defecto.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Procesa eventos de la cafetería con dos colas:
 * trabajadores (prioridad) y alumnos.
 */
public class Main {

    /**
     * Procesa una secuencia de eventos y devuelve las personas atendidas.
     * @param eventos arreglo de eventos, por ejemplo "LLEGA ALUMNO luis" o "ATIENDE"
     * @return nombres de las personas atendidas, uno por línea
     */
    static String procesar(String[] eventos) {
        ListaSimple colaAlumnos = new ListaSimple();
        ListaSimple colaTrabajadores = new ListaSimple();
        StringBuilder salida = new StringBuilder(); // se imprime una sola vez al final

        for (String evento : eventos) {
            String[] partes = evento.trim().split(" ");
            if (partes[0].equals("LLEGA")) {
                // Formato: LLEGA <ALUMNO|TRABAJADOR> <nombre>
                if (partes[1].equals("ALUMNO")) {
                    colaAlumnos.agregarFinal(partes[2]);
                } else {
                    colaTrabajadores.agregarFinal(partes[2]);
                }
            } else {
                // ATIENDE: primero trabajadores; alumnos solo si no hay trabajadores
                String atendido;
                if (!colaTrabajadores.estaVacia()) {
                    atendido = colaTrabajadores.quitarInicio();
                } else {
                    atendido = colaAlumnos.quitarInicio();
                }
                salida.append(atendido).append('\n');
            }
        }
        return salida.toString();
    }

    /**
     * Muestra un caso por defecto: su entrada y su salida.
     * @param titulo nombre del caso
     * @param eventos eventos del caso
     */
    static void mostrarCaso(String titulo, String[] eventos) {
        System.out.println("=== " + titulo + " ===");
        System.out.println("Entrada:");
        System.out.println(eventos.length);
        for (String e : eventos) {
            System.out.println(e);
        }
        System.out.println("Salida:");
        System.out.print(procesar(eventos));
        System.out.println();
    }

    /**
     * Ejecuta todos los casos por defecto (se usa cuando no hay entrada).
     */
    static void ejecutarCasosPorDefecto() {
        System.out.println("No se ingresó entrada: se ejecutan los casos por defecto.\n");

        // Caso 1: normal (el del enunciado)
        mostrarCaso("Caso 1: normal", new String[]{
            "LLEGA ALUMNO luis", "LLEGA TRABAJADOR ana", "LLEGA ALUMNO jorge",
            "ATIENDE", "ATIENDE", "LLEGA TRABAJADOR juan", "ATIENDE"});

        // Caso 2: límite, un solo elemento (la lista queda vacía)
        mostrarCaso("Caso 2: limite (un solo alumno)", new String[]{
            "LLEGA ALUMNO pedro", "ATIENDE"});

        // Caso 3: prioridad de trabajadores y orden FIFO
        mostrarCaso("Caso 3: prioridad y orden", new String[]{
            "LLEGA ALUMNO maria", "LLEGA ALUMNO carlos",
            "LLEGA TRABAJADOR sofia", "LLEGA TRABAJADOR diego",
            "ATIENDE", "ATIENDE", "ATIENDE", "ATIENDE"});

        // Caso 4: solo trabajadores intercalados con alumnos que llegan después
        mostrarCaso("Caso 4: llegan trabajadores despues", new String[]{
            "LLEGA ALUMNO rosa", "ATIENDE",
            "LLEGA ALUMNO tomas", "LLEGA TRABAJADOR elena",
            "ATIENDE", "ATIENDE"});

        // Caso 5: grande, N = 200000 (solo se muestra el resumen)
        int n = 200000;
        String[] grande = new String[n];
        for (int i = 0; i < n / 2; i++) {
            grande[i] = (i % 2 == 0) ? "LLEGA ALUMNO alumno" : "LLEGA TRABAJADOR trabaja";
        }
        for (int i = n / 2; i < n; i++) {
            grande[i] = "ATIENDE";
        }
        long inicio = System.nanoTime();
        String resultado = procesar(grande);
        long milisegundos = (System.nanoTime() - inicio) / 1_000_000;
        System.out.println("=== Caso 5: grande (N = " + n + ") ===");
        System.out.println("Personas atendidas: " + resultado.split("\n").length
                + " (esperado: 100000)");
        System.out.println("Tiempo: " + milisegundos + " ms");
    }

    /**
     * Punto de entrada: lee los eventos de la entrada estándar.
     * Si no hay entrada, ejecuta los casos por defecto.
     * @param args no se usa
     * @throws IOException si falla la lectura de la entrada estándar
     */
    public static void main(String[] args) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        String primera = lector.readLine();
        // Sin entrada (null) o Enter sin escribir nada (cadena vacía): casos por defecto
        if (primera == null || primera.trim().isEmpty()) {
            ejecutarCasosPorDefecto();
            return;
        }

        // Entrada real: se leen los N eventos y se imprimen los atendidos
        int n = Integer.parseInt(primera.trim());
        String[] eventos = new String[n];
        for (int i = 0; i < n; i++) {
            eventos[i] = lector.readLine();
        }
        System.out.print(procesar(eventos));
    }
}
