/**
 * Problema: Derdenio y sus Estructuras de Datos
 * Autor: Noemi Mata
 * Grupo: GTID244
 * Fecha: 23/10/2026
 * Descripción: Resuelve el problema usando una lista doble propia.
 */

/**
 * Lista doblemente enlazada de enteros.
 * Guarda referencia al primer y al último nodo, así se puede recorrer
 * en los dos sentidos y agregar al final en O(1).
 */
public class ListaDoble {
    private Nodo cabeza; // primer nodo
    private Nodo cola;   // último nodo
    private int tamanio;

    /** Crea una lista vacía. */
    public ListaDoble() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    /**
     * Agrega un valor al final de la lista.
     * @param valor número a agregar
     */
    public void agregarFinal(long valor) {
        Nodo nuevo = new Nodo(valor);
        if (cabeza == null) {
            cabeza = nuevo;          // lista vacía: es cabeza y cola
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;  // el último actual apunta al nuevo
            nuevo.anterior = cola;   // el nuevo apunta hacia atrás al último actual
            cola = nuevo;            // el nuevo pasa a ser el último
        }
        tamanio++;
    }

    /**
     * Combina dos listas ordenadas de menor a mayor en una sola ordenada de
     * mayor a menor. Se recorren ambas desde el final hacia atrás (con el
     * puntero anterior) tomando siempre el mayor de los dos valores.
     * @param a primera lista, ordenada de menor a mayor
     * @param b segunda lista, ordenada de menor a mayor
     * @return nueva lista con todos los valores, ordenada de mayor a menor
     */
    public static ListaDoble fusionarDescendente(ListaDoble a, ListaDoble b) {
        ListaDoble resultado = new ListaDoble();
        Nodo p1 = a.getCola(); // empieza en el mayor de la primera lista
        Nodo p2 = b.getCola(); // empieza en el mayor de la segunda lista

        // Mientras las dos listas tengan elementos, se toma el mayor de los dos
        while (p1 != null && p2 != null) {
            if (p1.valor >= p2.valor) {
                resultado.agregarFinal(p1.valor);
                p1 = p1.anterior;  // se retrocede en la primera lista
            } else {
                resultado.agregarFinal(p2.valor);
                p2 = p2.anterior;  // se retrocede en la segunda lista
            }
        }
        // Si una lista se acabó, lo que queda de la otra ya está en orden
        while (p1 != null) {
            resultado.agregarFinal(p1.valor);
            p1 = p1.anterior;
        }
        while (p2 != null) {
            resultado.agregarFinal(p2.valor);
            p2 = p2.anterior;
        }
        return resultado;
    }

    /**
     * Devuelve los valores de la lista separados por un espacio.
     * @return texto con los valores de cabeza a cola
     */
    public String aTexto() {
        StringBuilder texto = new StringBuilder();
        Nodo actual = cabeza;
        while (actual != null) {
            texto.append(actual.valor);
            if (actual.siguiente != null) {
                texto.append(' ');
            }
            actual = actual.siguiente; // se avanza al siguiente nodo
        }
        return texto.toString();
    }

    /**
     * Devuelve el primer nodo de la lista.
     * @return nodo cabeza, o null si está vacía
     */
    public Nodo getCabeza() {
        return cabeza;
    }

    /**
     * Devuelve el último nodo de la lista.
     * @return nodo cola, o null si está vacía
     */
    public Nodo getCola() {
        return cola;
    }

    /**
     * Indica si la lista no tiene elementos.
     * @return true si está vacía
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Devuelve la cantidad de nodos de la lista.
     * @return tamaño de la lista
     */
    public int tamanio() {
        return tamanio;
    }
}