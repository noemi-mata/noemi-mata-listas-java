/**
 * Problema: Inserción en Lista Enlazada
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
 * Lee una lista de enteros positivos, inserta -1 antes de cada impar
 * e imprime la cantidad insertada y la lista resultante.
 */
public class Main {

    /**
     * Cambiar a true SOLO al enviar a omegaUp. Con true, una entrada vacía
     * se trata como lista vacía (entrada válida) y no se corren los casos por defecto.
     */
    static final boolean MODO_OMEGAUP = false;

    /**
     * Construye una lista a partir de un arreglo.
     * @param valores valores a cargar
     * @return lista con los valores en el mismo orden
     */
    static ListaSimple construir(int[] valores) {
        ListaSimple lista = new ListaSimple();
        for (int valor : valores) {
            lista.agregarFinal(valor);
        }
        return lista;
    }

    /**
     * Aplica Parcial3 a la lista y arma la salida del problema.
     * @param lista lista cargada con la entrada
     * @return línea con la cantidad insertada y línea con la lista actualizada
     */
    static String resolver(ListaSimple lista) {
        int insertados = lista.parcial3();
        return insertados + "\n" + lista.aTexto() + "\n";
    }

    /**
     * Muestra un caso por defecto: su entrada y su salida.
     * @param titulo nombre del caso
     * @param valores valores de la lista de entrada
     */
    static void mostrarCaso(String titulo, int[] valores) {
        System.out.println("=== " + titulo + " ===");
        System.out.println("Entrada:");
        if (valores.length == 0) {
            System.out.println("(lista vacía)");
        }
        for (int valor : valores) {
            System.out.println(valor);
        }
        System.out.println("Salida:");
        System.out.print(resolver(construir(valores)));
        System.out.println();
    }

    /**
     * Ejecuta todos los casos por defecto (se usa cuando no hay entrada).
     */
    static void ejecutarCasosPorDefecto() {
        System.out.println("No se ingresó entrada: se ejecutan los casos por defecto.\n");

        // Caso 1: normal (el del enunciado)
        mostrarCaso("Caso 1: normal (enunciado)", new int[]{1, 2, 3});

        // Caso 2: normal, mezcla de pares e impares con la cabeza par
        mostrarCaso("Caso 2: mezcla", new int[]{4, 5, 8, 3, 3, 10});

        // Caso 3: límite, lista vacía
        mostrarCaso("Caso 3: limite (lista vacia)", new int[]{});

        // Caso 4: límite, un solo nodo impar
        mostrarCaso("Caso 4: limite (un solo impar)", new int[]{7});

        // Caso 5: límite, todos pares (no se inserta nada)
        mostrarCaso("Caso 5: todos pares", new int[]{2, 4, 6});

        // Caso 6: todos impares (se inserta uno antes de cada nodo)
        mostrarCaso("Caso 6: todos impares", new int[]{1, 3, 5});

        // Caso 7: grande, valores del 1 al 10000 (se muestra solo el resumen)
        int n = 10000;
        int[] grande = new int[n];
        for (int i = 0; i < n; i++) {
            grande[i] = i + 1;
        }
        ListaSimple lista = construir(grande);
        long inicio = System.nanoTime();
        int insertados = lista.parcial3();
        long milisegundos = (System.nanoTime() - inicio) / 1_000_000;
        System.out.println("=== Caso 7: grande (N = " + n + ") ===");
        System.out.println("Nodos insertados: " + insertados + " (esperado: 5000)");
        System.out.println("Tamaño final de la lista: " + lista.tamanio() + " (esperado: 15000)");
        System.out.println("Tiempo: " + milisegundos + " ms");
    }

    /**
     * Punto de entrada: lee un valor por línea hasta el fin de la entrada.
     * Si no hay entrada, ejecuta los casos por defecto.
     * @param args no se usa
     * @throws IOException si falla la lectura de la entrada estándar
     */
    public static void main(String[] args) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        if (!MODO_OMEGAUP) {
            System.out.println("Escribe un valor por línea; una línea vacía termina la lista.");
            System.out.print("(Enter solo al inicio = casos por defecto): ");
        }

        String linea = lector.readLine();

        // Sin entrada (null) o Enter sin escribir nada: casos por defecto
        if (!MODO_OMEGAUP && (linea == null || linea.trim().isEmpty())) {
            System.out.println();
            ejecutarCasosPorDefecto();
            return;
        }

        ListaSimple lista = new ListaSimple();
        while (linea != null) {
            if (linea.trim().isEmpty()) {
                if (!MODO_OMEGAUP) {
                    break; // en pruebas locales, una línea vacía termina la lista
                }
            } else {
                StringTokenizer partes = new StringTokenizer(linea);
                while (partes.hasMoreTokens()) {
                    lista.agregarFinal(Integer.parseInt(partes.nextToken()));
                }
            }
            linea = lector.readLine();
        }
        System.out.print(resolver(lista));
    }
}