/**
 * Problema: Formados en la cafetería
 * Autor: Noemi Mata Ruiz
 * Grupo: GITID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */

/**
 * Lista enlazada simple usada como cola (FIFO).
 * Mantiene referencia al primer y al último nodo para que
 * agregar al final y quitar del inicio sean operaciones O(1).
 */
public class ListaSimple {
    private Nodo cabeza; // nodo de la letra 'a'
    private Nodo cola;   // nodo de la última letra agregada

    /** Crea una lista vacía. */
    public ListaSimple() {
        cabeza = null;
        cola = null;
    }

    /**
     * Agrega un nodo al final de la lista.
     * @param letra letra que representará el nuevo nodo
     */
    public void agregarFinal(char letra) {
        Nodo nuevo = new Nodo(letra);
        if (cabeza == null) {
            cabeza = nuevo;          // lista vacía: es cabeza y cola
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;  // el último actual apunta al nuevo
            cola = nuevo;            // el nuevo pasa a ser el último
        }
    }

    /**
     * Llena la lista con los 26 nodos de la 'a' a la 'z'.
     */
    public void crearAbecedario() {
        for (char c = 'a'; c <= 'z'; c++) {
            agregarFinal(c);
        }
    }

    /**
     * Suma 1 al contador de la letra indicada.
     * @param letra letra encontrada en la cadena
     */
    public void incrementar(char letra) {
        Nodo actual = cabeza;
        // Se avanza con el puntero siguiente hasta encontrar la letra
        while (actual != null && actual.letra != letra) {
            actual = actual.siguiente;
        }
        if (actual != null) {
            actual.cantidad++;
        }
    }

    /**
     * Devuelve el primer nodo de la lista.
     * @return nodo cabeza (letra 'a')
     */
    public Nodo getCabeza() {
        return cabeza;
    }
}