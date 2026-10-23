/**
 * Problema: Formados en la cafetería
 * Autor: Noemi Mata
 * Grupo: GITID244
 * Fecha: 22/10/2026
 * Descripción: Programa de pruebas locales con menú: entrada manual
 *              o casos de prueba ya llenos. Usa listas simples propias.
 */

import java.util.Scanner;

/**
 * Menú para probar la solución sin necesidad de omegaUp.
 */
public class MainPruebas {

    /**
     * Procesa una secuencia de eventos con dos colas (trabajadores tienen prioridad).
     * @param eventos arreglo de eventos, por ejemplo "LLEGA ALUMNO luis" o "ATIENDE"
     * @return nombres de las personas atendidas, uno por línea
     */
    static String procesar(String[] eventos) {
        ListaSimple colaAlumnos = new ListaSimple();
        ListaSimple colaTrabajadores = new ListaSimple();
        StringBuilder salida = new StringBuilder();

        for (String evento : eventos) {
            String[] partes = evento.trim().split(" ");
            if (partes[0].equals("LLEGA")) {
                if (partes[1].equals("ALUMNO")) {
                    colaAlumnos.agregarFinal(partes[2]);
                } else {
                    colaTrabajadores.agregarFinal(partes[2]);
                }
            } else { // ATIENDE
                String atendido;
                if (!colaTrabajadores.estaVacia()) {
                    atendido = colaTrabajadores.quitarInicio();
                } else {
                    atendido = colaAlumnos.quitarInicio();
                }
                if (atendido == null) {
                    atendido = "(no hay nadie formado)";
                }
                salida.append(atendido).append('\n');
            }
        }
        return salida.toString();
    }

    /**
     * Revisa que un evento tenga el formato correcto.
     * @param evento texto escrito por el usuario
     * @return true si es válido
     */
    static boolean esValido(String evento) {
        String e = evento.trim();
        return e.equals("ATIENDE") || e.matches("LLEGA (ALUMNO|TRABAJADOR) [a-z]+");
    }

    /** Pide los eventos por teclado y muestra el resultado. */
    static void entradaManual(Scanner teclado) {
        int n = 0;
        while (n < 1) {
            System.out.print("¿Cuántos eventos quieres escribir (N)? ");
            try {
                n = Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Escribe un número entero mayor que 0.");
            }
        }
        System.out.println("Formato: LLEGA ALUMNO luis | LLEGA TRABAJADOR ana | ATIENDE");

        String[] eventos = new String[n];
        for (int i = 0; i < n; i++) {
            String linea;
            do {
                System.out.print("Evento " + (i + 1) + ": ");
                linea = teclado.nextLine();
                if (!esValido(linea)) {
                    System.out.println("  Formato incorrecto, intenta de nuevo (nombre en minúsculas).");
                }
            } while (!esValido(linea));
            eventos[i] = linea;
        }
        System.out.println("\n--- Personas atendidas ---");
        System.out.print(procesar(eventos));
    }

    /** Ejecuta un caso ya lleno, mostrando la entrada y la salida. */
    static void casoLleno(String titulo, String[] eventos) {
        System.out.println("\n=== " + titulo + " ===");
        System.out.println("Entrada:");
        System.out.println(eventos.length);
        for (String e : eventos) {
            System.out.println(e);
        }
        System.out.println("Salida:");
        System.out.print(procesar(eventos));
    }

    /** Caso grande: N = 200000. Solo muestra tiempo y cantidad atendida. */
    static void casoGrande() {
        int n = 200000;
        String[] eventos = new String[n];
        for (int i = 0; i < n / 2; i++) {
            eventos[i] = (i % 2 == 0) ? "LLEGA ALUMNO alumno" : "LLEGA TRABAJADOR trabaja";
        }
        for (int i = n / 2; i < n; i++) {
            eventos[i] = "ATIENDE";
        }
        long inicio = System.nanoTime();
        String salida = procesar(eventos);
        long milisegundos = (System.nanoTime() - inicio) / 1_000_000;

        int atendidos = salida.split("\n").length;
        System.out.println("\n=== Caso grande (N = " + n + ") ===");
        System.out.println("Personas atendidas: " + atendidos + " (esperado: 100000)");
        System.out.println("Tiempo: " + milisegundos + " ms");
    }

    /**
     * Menú principal.
     * @param args no se usa
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n===== FORMADOS EN LA CAFETERÍA =====");
            System.out.println("1. Escribir los eventos yo misma");
            System.out.println("2. Caso normal (el del enunciado)");
            System.out.println("3. Caso límite (un solo alumno)");
            System.out.println("4. Caso de prioridad y orden");
            System.out.println("5. Caso grande (200000 eventos)");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    entradaManual(teclado);
                    break;
                case 2:
                    casoLleno("Caso normal", new String[]{
                        "LLEGA ALUMNO luis", "LLEGA TRABAJADOR ana", "LLEGA ALUMNO jorge",
                        "ATIENDE", "ATIENDE", "LLEGA TRABAJADOR juan", "ATIENDE"});
                    break;
                case 3:
                    casoLleno("Caso límite", new String[]{
                        "LLEGA ALUMNO pedro", "ATIENDE"});
                    break;
                case 4:
                    casoLleno("Prioridad y orden", new String[]{
                        "LLEGA ALUMNO maria", "LLEGA ALUMNO carlos",
                        "LLEGA TRABAJADOR sofia", "LLEGA TRABAJADOR diego",
                        "ATIENDE", "ATIENDE", "ATIENDE", "ATIENDE"});
                    break;
                case 5:
                    casoGrande();
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        teclado.close();
    }
}
