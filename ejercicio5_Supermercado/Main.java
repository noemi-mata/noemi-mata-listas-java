/**
 * Problema: Supermercado
 * Autor: Noemi Mata
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 *              Si no se ingresa entrada, ejecuta casos de prueba por defecto.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.StringTokenizer;

/**
 * Lee N productos con su precio e imprime el ticket ordenado de mayor a menor
 * precio, seguido del total de la compra.
 */
public class Main {

    private static StringTokenizer tokens; // palabras de la línea actual

    /**
     * Da formato a un número igual que cout de C++: máximo 6 cifras
     * significativas y sin ceros sobrantes (23.60 -> 23.6, 23.00 -> 23).
     * @param valor número a mostrar
     * @return texto del número
     */
    static String formatear(double valor) {
        if (valor == 0) {
            return "0";
        }
        BigDecimal redondeado = new BigDecimal(valor).round(new MathContext(6, RoundingMode.HALF_EVEN));
        int exponente = redondeado.precision() - redondeado.scale() - 1;
        if (exponente < -4 || exponente >= 6) {
            // Notación científica, como hace cout con números muy grandes o muy pequeños
            String digitos = redondeado.unscaledValue().abs().toString().replaceAll("0+$", "");
            String mantisa = digitos.substring(0, 1)
                    + (digitos.length() > 1 ? "." + digitos.substring(1) : "");
            int absoluto = Math.abs(exponente);
            return mantisa + "e" + (exponente < 0 ? "-" : "+") + (absoluto < 10 ? "0" : "") + absoluto;
        }
        return redondeado.stripTrailingZeros().toPlainString();
    }

    /**
     * Inserta los productos en la lista ordenada y arma el ticket.
     * @param nombres nombres de los productos
     * @param precios precios de los productos
     * @return ticket completo: una línea por producto, línea en blanco y total
     */
    static String procesar(String[] nombres, double[] precios) {
        ListaSimple lista = new ListaSimple();
        double total = 0;
        for (int i = 0; i < nombres.length; i++) {
            lista.insertarOrdenado(nombres[i], precios[i]);
            total += precios[i];
        }

        StringBuilder salida = new StringBuilder();
        Nodo actual = lista.getCabeza();
        int posicion = 1;
        while (actual != null) {
            salida.append(posicion).append(" | ")
                  .append(actual.nombre).append(" | ")
                  .append(formatear(actual.precio)).append('\n');
            actual = actual.siguiente; // se avanza al siguiente producto
            posicion++;
        }
        salida.append('\n').append(formatear(total)).append('\n');
        return salida.toString();
    }

    /**
     * Devuelve la siguiente palabra de la entrada, leyendo más líneas si hace falta.
     * @param lector lector de la entrada estándar
     * @return la siguiente palabra, o null si ya no hay más entrada
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
     * @param lineas productos en formato "nombre precio"
     */
    static void mostrarCaso(String titulo, String[] lineas) {
        String[] nombres = new String[lineas.length];
        double[] precios = new double[lineas.length];
        System.out.println("=== " + titulo + " ===");
        System.out.println("Entrada:");
        System.out.println(lineas.length);
        for (int i = 0; i < lineas.length; i++) {
            System.out.println(lineas[i]);
            String[] partes = lineas[i].split(" ");
            nombres[i] = partes[0];
            precios[i] = Double.parseDouble(partes[1]);
        }
        System.out.println("Salida:");
        System.out.print(procesar(nombres, precios));
        System.out.println();
    }

    /**
     * Ejecuta todos los casos por defecto (se usa cuando no hay entrada).
     */
    static void ejecutarCasosPorDefecto() {
        System.out.println("No se ingresó entrada: se ejecutan los casos por defecto.\n");

        // Caso 1: normal (el del enunciado)
        mostrarCaso("Caso 1: normal (enunciado)", new String[]{
            "axion 23.60", "fud 23.00", "LaLa 23.01", "Petalo 129.5",
            "2Kg_manzanas 58.6", "BimboG 30.5", "MayonesaM 50.5", "Colgate 23"});

        // Caso 2: límite, un solo producto
        mostrarCaso("Caso 2: limite (un solo producto)", new String[]{"leche 25.5"});

        // Caso 3: límite, precios iguales (se respeta el orden de llegada)
        mostrarCaso("Caso 3: precios iguales", new String[]{"pan 10", "atun 10", "sal 10"});

        // Caso 4: entrada en orden ascendente (cada nuevo producto va a la cabeza)
        mostrarCaso("Caso 4: entrada ascendente", new String[]{"a 1.5", "b 2.25", "c 3"});

        // Caso 5: decimales que deben sumar exactamente 100
        mostrarCaso("Caso 5: suma con decimales", new String[]{"cafe 99.99", "te 0.01"});

        // Caso 6: grande, 100 productos con precio i * 2.5 (se muestra el resumen)
        int n = 100;
        String[] nombres = new String[n];
        double[] precios = new double[n];
        for (int i = 0; i < n; i++) {
            nombres[i] = "producto" + (i + 1);
            precios[i] = (i + 1) * 2.5;
        }
        String[] lineas = procesar(nombres, precios).split("\n");
        System.out.println("=== Caso 6: grande (N = " + n + ") ===");
        System.out.println("Primera línea: " + lineas[0] + " (esperado: 1 | producto100 | 250)");
        System.out.println("Última línea:  " + lineas[n - 1] + " (esperado: 100 | producto1 | 2.5)");
        System.out.println("Total: " + lineas[n + 1] + " (esperado: 12625)");
    }

    /**
     * Punto de entrada: lee N y los N productos de la entrada estándar.
     * Si no hay entrada, ejecuta los casos por defecto.
     * @param args no se usa
     * @throws IOException si falla la lectura de la entrada estándar
     */
    public static void main(String[] args) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Escribe N y luego un producto por línea: nombre precio (Enter solo = casos por defecto): ");

        String primera = lector.readLine();
        // Sin entrada (null) o Enter sin escribir nada: casos por defecto
        if (primera == null || primera.trim().isEmpty()) {
            System.out.println();
            ejecutarCasosPorDefecto();
            return;
        }

        tokens = new StringTokenizer(primera);
        int n = Integer.parseInt(siguienteToken(lector));
        String[] nombres = new String[n];
        double[] precios = new double[n];
        for (int i = 0; i < n; i++) {
            nombres[i] = siguienteToken(lector);
            precios[i] = Double.parseDouble(siguienteToken(lector));
        }
        System.out.print(procesar(nombres, precios));
    }
}