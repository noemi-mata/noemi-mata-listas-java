# Implementación de Listas en Java

Estudiante: Noemi Mata 

Grupo: GIID244

Materia: Estructuras de datos 

Modalidad: Individual, 5 puntos, lenguaje Java 

Fecha: 23/10/2026

## DOCUMENTO PDF - MATA_NOEMI_LISTASJAVA.PDF

[Mata_Noemi_ListasJava.pdf](https://github.com/user-attachments/files/33997371/Mata_Noemi_ListasJava.pdf)


## Descripción del proyecto

Este repositorio contiene la solución de 8 problemas de la plataforma omegaUp usando listas enlazadas implementadas desde cero. Cada solución tiene su propia clase Nodo y su propia clase de lista (ListaSimple o ListaDoble), junto con una clase Main que lee la entrada y muestra la salida.

En ninguna solución se usan las estructuras que ya trae Java, como LinkedList, ArrayList o Vector. Todo el manejo de los nodos y de los punteros (siguiente y anterior) está escrito a mano.

## Documentación del código

Cada archivo comienza con un encabezado que indica el autor, el grupo, la fecha y el problema que resuelve. Todas las clases y todos los métodos tienen comentarios Javadoc que explican qué hacen, qué reciben y qué devuelven. Además, las partes donde se mueven los punteros tienen comentarios internos que explican paso a paso qué referencia cambia y por qué. Los nombres de variables y métodos son descriptivos y están en español.

## Ejercicios resueltos

Ejercicio 1: Formados en la cafetería. Se simula una cafetería con dos filas: una de trabajadores y una de alumnos. Los trabajadores se atienden primero y los alumnos solo cuando no hay trabajadores esperando. Se usa lista simple con referencia a la cabeza y a la cola, porque las personas llegan por el final y se atienden por el inicio, y con ambas referencias las dos operaciones son inmediatas. Se necesitan dos listas, una por cada fila. Carpeta: ejercicio1_FormadosEnLaCafeteria.

Ejercicio 3: Comprando agua. Se buscan, entre los precios de varias marcas de agua, el más barato. Se usa lista simple porque basta recorrer todos los precios una sola vez comparando contra el menor encontrado hasta el momento. Carpeta: ejercicio3_ComprandoAgua.

Ejercicio 5: Supermercado. Se arma un ticket con los productos ordenados de mayor a menor precio y se muestra el total de la compra. Se usa lista simple ordenada: cada producto se inserta directamente en su lugar correcto, así la lista siempre está ordenada y no hace falta ordenar al final. Si dos productos cuestan lo mismo, el que llegó después queda antes, igual que en el ejemplo del problema. Carpeta: ejercicio5_Supermercado.

Ejercicio 7: Cuadro de Honor. Se muestra, en orden alfabético, el nombre de los alumnos con promedio de 9.5 o más. Se usa lista simple ordenada alfabéticamente: solo se insertan los alumnos que cumplen el promedio y cada nombre se coloca directamente en su posición. Carpeta: ejercicio7_CuadroDeHonor.

## Estructura del repositorio

En la raíz está este archivo README.md y una carpeta por cada ejercicio, con el nombre indicado arriba en cada descripción. Las carpetas de los ejercicios 1 al 7 contienen tres archivos: Nodo.java, ListaSimple.java y Main.java. La carpeta del ejercicio 8 contiene Nodo.java, ListaDoble.java y Main.java.

## Cómo compilar y ejecutar

Se necesita tener instalado el JDK de Java, de forma que los comandos javac y java funcionen en la terminal.

Para probar un ejercicio, se entra a su carpeta desde la terminal, se compilan todos los archivos .java con javac y se ejecuta la clase Main con java. Para probar otro ejercicio solo se cambia de carpeta.

## Casos de prueba por defecto

Si se ejecuta un programa y se presiona Enter sin escribir nada, el programa corre automáticamente sus casos de prueba por defecto. Estos incluyen casos normales, casos límite y un caso grande, y para cada uno se muestra la entrada usada y la salida obtenida.

Para probar con datos propios, basta con escribir la entrada que pide el problema correspondiente. También se puede enviar una entrada vacía desde la terminal para que corran los casos por defecto sin escribir nada.

## Nota sobre omegaUp

Para enviar una solución a omegaUp se junta todo el código en un solo archivo Main.java, con las clases auxiliares sin la palabra public y la clase Main al final como única pública. En ese envío no se muestran mensajes de ayuda ni textos extra, solo la salida que pide el problema.

En los ejercicios 2 y 4 una entrada vacía es válida en el problema, porque representa una cadena vacía y una lista vacía. Por eso su Main tiene una constante llamada MODO_OMEGAUP. En este repositorio vale falso, para que una entrada vacía ejecute los casos por defecto. Al enviar a omegaUp se cambia a verdadero, para que una entrada vacía se trate como entrada válida.

## Codigos de OmegaUp

1------
/**
 * Problema: Formados en la cafetería
 * Autor: Noemi Mata
 * Grupo: GIDSXXX
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Nodo de una lista enlazada simple que guarda el nombre de una persona.
 */
class Nodo {
    String nombre;   // nombre de la persona formada
    Nodo siguiente;  // referencia a la persona que está detrás

    /**
     * Crea un nodo sin enlazar.
     * @param nombre nombre de la persona
     */
    Nodo(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }
}

/**
 * Lista enlazada simple usada como cola (FIFO).
 * Mantiene referencia al primer y al último nodo para que
 * agregar al final y quitar del inicio sean operaciones O(1).
 */
class ListaSimple {
    private Nodo cabeza; // primer nodo (el que se atiende primero)
    private Nodo cola;   // último nodo (donde se forma el siguiente)
    private int tamanio;

    /** Crea una lista vacía. */
    ListaSimple() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    /**
     * Agrega una persona al final de la lista.
     * @param nombre nombre de la persona que llega
     */
    void agregarFinal(String nombre) {
        Nodo nuevo = new Nodo(nombre);
        if (cabeza == null) {
            // Lista vacía: el nuevo nodo es a la vez cabeza y cola
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo; // el último actual apunta al nuevo
            cola = nuevo;           // el nuevo pasa a ser el último
        }
        tamanio++;
    }

    /**
     * Quita y devuelve el nombre de la primera persona de la lista.
     * @return nombre de la persona atendida, o null si la lista está vacía
     */
    String quitarInicio() {
        if (cabeza == null) {
            return null;
        }
        String nombre = cabeza.nombre;
        cabeza = cabeza.siguiente; // la cabeza avanza al siguiente nodo
        if (cabeza == null) {
            cola = null;           // si quedó vacía, la cola también se limpia
        }
        tamanio--;
        return nombre;
    }

    /**
     * Indica si la lista no tiene elementos.
     * @return true si está vacía
     */
    boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Devuelve la cantidad de personas formadas.
     * @return tamaño de la lista
     */
    int tamanio() {
        return tamanio;
    }
}

/**
 * Procesa eventos de la cafetería con dos colas:
 * trabajadores (prioridad) y alumnos.
 */
public class Main {

    /**
     * Atiende a la siguiente persona: primero trabajadores; alumnos solo si
     * no hay trabajadores formados.
     * @param alumnos cola de alumnos
     * @param trabajadores cola de trabajadores
     * @return nombre de la persona atendida
     */
    static String atender(ListaSimple alumnos, ListaSimple trabajadores) {
        if (!trabajadores.estaVacia()) {
            return trabajadores.quitarInicio();
        }
        return alumnos.quitarInicio();
    }

    /**
     * Procesa una secuencia de eventos y devuelve las personas atendidas.
     * @param eventos arreglo de eventos, por ejemplo "LLEGA ALUMNO luis" o "ATIENDE"
     * @return nombres de las personas atendidas, uno por línea
     */
    static String procesar(String[] eventos) {
        ListaSimple colaAlumnos = new ListaSimple();
        ListaSimple colaTrabajadores = new ListaSimple();
        StringBuilder salida = new StringBuilder();

        for (String evento : eventos) {
            String[] partes = evento.trim().split("\\s+");
            if (partes[0].equals("LLEGA")) {
                // Formato: LLEGA <ALUMNO|TRABAJADOR> <nombre>
                if (partes[1].equals("ALUMNO")) {
                    colaAlumnos.agregarFinal(partes[2]);
                } else {
                    colaTrabajadores.agregarFinal(partes[2]);
                }
            } else {
                salida.append(atender(colaAlumnos, colaTrabajadores)).append('\n');
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

    /** Ejecuta todos los casos por defecto (solo si no hay entrada). */
    static void ejecutarCasosPorDefecto() {
        System.out.println("No se ingresó entrada: se ejecutan los casos por defecto.\n");

        mostrarCaso("Caso 1: normal", new String[]{
            "LLEGA ALUMNO luis", "LLEGA TRABAJADOR ana", "LLEGA ALUMNO jorge",
            "ATIENDE", "ATIENDE", "LLEGA TRABAJADOR juan", "ATIENDE"});

        mostrarCaso("Caso 2: limite (un solo alumno)", new String[]{
            "LLEGA ALUMNO pedro", "ATIENDE"});

        mostrarCaso("Caso 3: prioridad y orden", new String[]{
            "LLEGA ALUMNO maria", "LLEGA ALUMNO carlos",
            "LLEGA TRABAJADOR sofia", "LLEGA TRABAJADOR diego",
            "ATIENDE", "ATIENDE", "ATIENDE", "ATIENDE"});

        mostrarCaso("Caso 4: llegan trabajadores despues", new String[]{
            "LLEGA ALUMNO rosa", "ATIENDE",
            "LLEGA ALUMNO tomas", "LLEGA TRABAJADOR elena",
            "ATIENDE", "ATIENDE"});

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
     * @param args no se usa
     * @throws IOException si falla la lectura de la entrada estándar
     */
    public static void main(String[] args) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        String primera = lector.readLine();
        if (primera == null || primera.trim().isEmpty()) {
            ejecutarCasosPorDefecto();
            return;
        }

        int n = Integer.parseInt(primera.trim());

        // Se procesa evento por evento y se imprime al momento: así no se
        // guarda toda la entrada ni toda la salida en memoria (límite de 32 MiB).
        ListaSimple colaAlumnos = new ListaSimple();
        ListaSimple colaTrabajadores = new ListaSimple();
        java.io.PrintWriter salida = new java.io.PrintWriter(
                new java.io.BufferedWriter(new java.io.OutputStreamWriter(System.out), 1 << 14));

        int leidos = 0;
        while (leidos < n) {
            String linea = lector.readLine();
            if (linea == null) {
                break; // la entrada terminó antes de lo esperado
            }
            java.util.StringTokenizer palabras = new java.util.StringTokenizer(linea);
            if (!palabras.hasMoreTokens()) {
                continue; // línea en blanco
            }
            String tipo = palabras.nextToken();
            if (tipo.equals("LLEGA")) {
                String quien = palabras.nextToken();
                String nombre = palabras.nextToken();
                if (quien.equals("ALUMNO")) {
                    colaAlumnos.agregarFinal(nombre);
                } else {
                    colaTrabajadores.agregarFinal(nombre);
                }
            } else {
                salida.print(atender(colaAlumnos, colaTrabajadores));
                salida.print('\n');
            }
            leidos++;
        }
        salida.flush();
    }
}



3---------

/**
 * Problema: Comprando agua
 * Autor: Noemi Mata
 * Grupo: GIDSXXX
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Nodo de lista simple que guarda el precio de una marca de agua.
 */
class Nodo {
    int precio;      // precio de la botella
    Nodo siguiente;  // referencia a la siguiente marca

    /**
     * Crea un nodo sin enlazar.
     * @param precio precio de la marca
     */
    Nodo(int precio) {
        this.precio = precio;
        this.siguiente = null;
    }
}

/**
 * Lista enlazada simple de precios.
 * Guarda referencia al último nodo para agregar al final en O(1).
 */
class ListaSimple {
    private Nodo cabeza; // primer precio
    private Nodo cola;   // último precio
    private int tamanio;

    /** Crea una lista vacía. */
    ListaSimple() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    /**
     * Agrega un precio al final de la lista.
     * @param precio precio de una marca de agua
     */
    void agregarFinal(int precio) {
        Nodo nuevo = new Nodo(precio);
        if (cabeza == null) {
            cabeza = nuevo;          // lista vacía: es cabeza y cola
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;  // el último actual apunta al nuevo
            cola = nuevo;            // el nuevo pasa a ser el último
        }
        tamanio++;
    }

    /**
     * Recorre la lista y devuelve el menor precio.
     * @return el precio más bajo, o -1 si la lista está vacía
     */
    int obtenerMinimo() {
        if (cabeza == null) {
            return -1;
        }
        int minimo = cabeza.precio; // se empieza suponiendo que el primero es el menor
        Nodo actual = cabeza.siguiente;
        while (actual != null) {
            if (actual.precio < minimo) {
                minimo = actual.precio; // se encontró uno más barato
            }
            actual = actual.siguiente;  // se avanza al siguiente nodo
        }
        return minimo;
    }

    /**
     * Indica si la lista no tiene elementos.
     * @return true si está vacía
     */
    boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Devuelve la cantidad de precios guardados.
     * @return tamaño de la lista
     */
    int tamanio() {
        return tamanio;
    }
}

/**
 * Lee N precios e imprime el menor, usando una lista simple propia.
 */
public class Main {

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
     * Ejecuta todos los casos por defecto (solo si no hay entrada).
     */
    static void ejecutarCasosPorDefecto() {
        System.out.println("No se ingresó entrada: se ejecutan los casos por defecto.\n");
        mostrarCaso("Caso 1: normal", new int[]{9, 10, 8, 7, 11});
        mostrarCaso("Caso 2: normal", new int[]{20, 12, 16});
        mostrarCaso("Caso 3: limite (una sola marca)", new int[]{500});
        mostrarCaso("Caso 4: limite (menor al inicio)", new int[]{1, 50, 99999, 300});
        mostrarCaso("Caso 5: limite (menor al final)", new int[]{900, 800, 700, 2});

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
     * @param args no se usa
     * @throws IOException si falla la lectura de la entrada estándar
     */
    public static void main(String[] args) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        // Se lee TODA la entrada y se separa en palabras: así no importa si hay
        // líneas en blanco, espacios de más, tabuladores o saltos de línea raros.
        StringBuilder todo = new StringBuilder();
        String linea;
        while ((linea = lector.readLine()) != null) {
            todo.append(linea).append(' ');
        }
        StringTokenizer palabras = new StringTokenizer(todo.toString(), " \t\r\n\f,;");

        if (!palabras.hasMoreTokens()) {
            ejecutarCasosPorDefecto(); // sin entrada: casos por defecto
            return;
        }

        int n = Integer.parseInt(palabras.nextToken());
        ListaSimple lista = new ListaSimple();
        int leidos = 0;
        while (leidos < n && palabras.hasMoreTokens()) {
            lista.agregarFinal(Integer.parseInt(palabras.nextToken()));
            leidos++;
        }
        System.out.println(lista.obtenerMinimo());
    }
}




5--------

/**
 * Problema: Supermercado
 * Autor: Noemi Mata
 * Grupo: GIDSXXX
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.StringTokenizer;

/**
 * Nodo de lista simple que guarda un producto con su precio.
 */
class Nodo {
    String nombre;   // nombre del producto
    double precio;   // precio del producto
    Nodo siguiente;  // referencia al siguiente producto

    /**
     * Crea un nodo sin enlazar.
     * @param nombre nombre del producto
     * @param precio precio del producto
     */
    Nodo(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.siguiente = null;
    }
}

/**
 * Lista enlazada simple de productos, siempre ordenada de mayor a menor precio.
 */
class ListaSimple {
    private Nodo cabeza; // producto más caro
    private int tamanio;

    /** Crea una lista vacía. */
    ListaSimple() {
        cabeza = null;
        tamanio = 0;
    }

    /**
     * Inserta un producto en la posición que mantiene el orden de mayor a menor precio.
     * Si hay empate, el nuevo queda antes de los que ya estaban (como en el ejemplo del problema).
     * @param nombre nombre del producto
     * @param precio precio del producto
     */
    void insertarOrdenado(String nombre, double precio) {
        Nodo nuevo = new Nodo(nombre, precio);

        if (cabeza == null || precio >= cabeza.precio) {
            // Lista vacía o el nuevo cuesta igual o más que la cabeza: pasa a ser la cabeza
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        } else {
            // Se avanza mientras el siguiente nodo cueste más que el nuevo
            Nodo actual = cabeza;
            while (actual.siguiente != null && actual.siguiente.precio > precio) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente; // primero se enlaza hacia adelante
            actual.siguiente = nuevo;           // luego se enlaza desde el anterior
        }
        tamanio++;
    }

    /**
     * Devuelve el primer nodo de la lista.
     * @return nodo con el producto más caro, o null si está vacía
     */
    Nodo getCabeza() {
        return cabeza;
    }

    /**
     * Indica si la lista no tiene elementos.
     * @return true si está vacía
     */
    boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Devuelve la cantidad de productos.
     * @return tamaño de la lista
     */
    int tamanio() {
        return tamanio;
    }
}

/**
 * Lee N productos con su precio e imprime el ticket ordenado de mayor a menor
 * precio, seguido del total de la compra.
 */
public class Main {

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

    /** Ejecuta todos los casos por defecto (solo si no hay entrada). */
    static void ejecutarCasosPorDefecto() {
        System.out.println("No se ingresó entrada: se ejecutan los casos por defecto.\n");
        mostrarCaso("Caso 1: normal (enunciado)", new String[]{
            "axion 23.60", "fud 23.00", "LaLa 23.01", "Petalo 129.5",
            "2Kg_manzanas 58.6", "BimboG 30.5", "MayonesaM 50.5", "Colgate 23"});
        mostrarCaso("Caso 2: limite (un solo producto)", new String[]{"leche 25.5"});
        mostrarCaso("Caso 3: precios iguales", new String[]{"pan 10", "atun 10", "sal 10"});
        mostrarCaso("Caso 4: entrada ascendente", new String[]{"a 1.5", "b 2.25", "c 3"});
        mostrarCaso("Caso 5: suma con decimales", new String[]{"cafe 99.99", "te 0.01"});

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
     * @param args no se usa
     * @throws IOException si falla la lectura de la entrada estándar
     */
    public static void main(String[] args) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        // Se lee TODA la entrada y se separa en palabras: así no importan las
        // líneas en blanco, los espacios de más ni los saltos de línea raros.
        StringBuilder todo = new StringBuilder();
        String linea;
        while ((linea = lector.readLine()) != null) {
            todo.append(linea).append(' ');
        }
        StringTokenizer palabras = new StringTokenizer(todo.toString(), " \t\r\n\f");

        if (!palabras.hasMoreTokens()) {
            ejecutarCasosPorDefecto(); // sin entrada: casos por defecto
            return;
        }

        int n = Integer.parseInt(palabras.nextToken());
        String[] nombres = new String[n];
        double[] precios = new double[n];
        int leidos = 0;
        while (leidos < n && palabras.hasMoreTokens()) {
            nombres[leidos] = palabras.nextToken();
            precios[leidos] = Double.parseDouble(palabras.nextToken());
            leidos++;
        }
        if (leidos < n) {
            nombres = java.util.Arrays.copyOf(nombres, leidos);
            precios = java.util.Arrays.copyOf(precios, leidos);
        }
        System.out.print(procesar(nombres, precios));
    }
}



7--------

/**
 * Problema: Cuadro de Honor
 * Autor: Noemi Mata
 * Grupo: GIDSXXX
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Nodo de lista simple que guarda el nombre de un alumno del cuadro de honor.
 */
class Nodo {
    String nombre;   // nombre completo del alumno
    Nodo siguiente;  // referencia al siguiente alumno

    /**
     * Crea un nodo sin enlazar.
     * @param nombre nombre completo del alumno
     */
    Nodo(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }
}

/**
 * Lista enlazada simple de nombres, siempre ordenada alfabéticamente.
 */
class ListaSimple {
    private Nodo cabeza; // primer nombre en orden alfabético
    private int tamanio;

    /** Crea una lista vacía. */
    ListaSimple() {
        cabeza = null;
        tamanio = 0;
    }

    /**
     * Inserta un nombre en la posición que mantiene el orden alfabético.
     * Si hay nombres iguales, el nuevo queda después de los que ya estaban.
     * @param nombre nombre completo del alumno
     */
    void insertarOrdenado(String nombre) {
        Nodo nuevo = new Nodo(nombre);

        if (cabeza == null || nombre.compareTo(cabeza.nombre) < 0) {
            // Lista vacía o el nuevo va antes que la cabeza: pasa a ser la cabeza
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        } else {
            // Se avanza mientras el siguiente nombre sea menor o igual que el nuevo
            Nodo actual = cabeza;
            while (actual.siguiente != null
                    && actual.siguiente.nombre.compareTo(nombre) <= 0) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente; // primero se enlaza hacia adelante
            actual.siguiente = nuevo;           // luego se enlaza desde el anterior
        }
        tamanio++;
    }

    /**
     * Devuelve el primer nodo de la lista.
     * @return primer nombre en orden alfabético, o null si está vacía
     */
    Nodo getCabeza() {
        return cabeza;
    }

    /**
     * Indica si la lista no tiene elementos.
     * @return true si está vacía
     */
    boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Devuelve la cantidad de alumnos en la lista.
     * @return tamaño de la lista
     */
    int tamanio() {
        return tamanio;
    }
}

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

    /** Ejecuta todos los casos por defecto (solo si no hay entrada). */
    static void ejecutarCasosPorDefecto() {
        System.out.println("No se ingresó entrada: se ejecutan los casos por defecto.\n");
        mostrarCaso("Caso 1: normal (enunciado)", new String[]{
            "Pepito Perez", "9.6", "Julian Cardenas", "10.0", "Felix Garduza", "9.9",
            "Martin Gonzalez", "9.2", "Jose Soto", "8.5"});
        mostrarCaso("Caso 2: limite (nadie entra)", new String[]{
            "Ana Lopez", "9.4", "Luis Mora", "9.49"});
        mostrarCaso("Caso 3: limite (9.5 exacto)", new String[]{
            "Zoe Ruiz", "9.5", "Ana Gil", "9.50"});
        mostrarCaso("Caso 4: limite (un solo alumno)", new String[]{
            "Carlos Vega", "10.0"});
        mostrarCaso("Caso 5: orden inverso", new String[]{
            "Zacarias Luna", "10.0", "Maria Diaz", "9.8", "Beto Cruz", "9.6"});

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
     * @param args no se usa
     * @throws IOException si falla la lectura de la entrada estándar
     */
    public static void main(String[] args) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        String primera = siguienteLinea(lector); // salta líneas en blanco al inicio
        if (primera == null) {
            ejecutarCasosPorDefecto();
            return;
        }

        int n = Integer.parseInt(primera);
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

