/**
 * Problema: Supermercado
 * Autor: Noemi Mata
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */

/**
 * Lista enlazada simple de productos, siempre ordenada de mayor a menor precio.
 */
public class ListaSimple {
    private Nodo cabeza; // producto más caro
    private int tamanio;

    /** Crea una lista vacía. */
    public ListaSimple() {
        cabeza = null;
        tamanio = 0;
    }

    /**
     * Inserta un producto en la posición que mantiene el orden de mayor a menor precio.
     * Si hay empate, el nuevo queda después de los que ya estaban (orden estable).
     * @param nombre nombre del producto
     * @param precio precio del producto
     */
    public void insertarOrdenado(String nombre, double precio) {
        Nodo nuevo = new Nodo(nombre, precio);

        if (cabeza == null || precio > cabeza.precio) {
            // Lista vacía o el nuevo es el más caro: pasa a ser la cabeza
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        } else {
            // Se avanza mientras el siguiente nodo cueste igual o más que el nuevo
            Nodo actual = cabeza;
            while (actual.siguiente != null && actual.siguiente.precio >= precio) {
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
    public Nodo getCabeza() {
        return cabeza;
    }

    /**
     * Indica si la lista no tiene elementos.
     * @return true si está vacía
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Devuelve la cantidad de productos.
     * @return tamaño de la lista
     */
    public int tamanio() {
        return tamanio;
    }
}