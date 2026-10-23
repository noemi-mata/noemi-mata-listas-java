/**
 * Problema: Comprando agua
 * Autor: Noemi Mata
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */

/**
 * Lista enlazada simple de precios.
 * Guarda referencia al último nodo para agregar al final en O(1).
 */
public class ListaSimple {
    private Nodo cabeza; // primer precio
    private Nodo cola;   // último precio
    private int tamanio;

    /** Crea una lista vacía. */
    public ListaSimple() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    /**
     * Agrega un precio al final de la lista.
     * @param precio precio de una marca de agua
     */
    public void agregarFinal(int precio) {
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
    public int obtenerMinimo() {
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
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Devuelve la cantidad de precios guardados.
     * @return tamaño de la lista
     */
    public int tamanio() {
        return tamanio;
    }
}