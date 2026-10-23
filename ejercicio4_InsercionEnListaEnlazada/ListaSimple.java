/**
 * Problema: Inserción en Lista Enlazada
 * Autor: Noemi Mata
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */

/**
 * Lista enlazada simple de enteros.
 * Guarda referencia al último nodo para agregar al final en O(1).
 */
public class ListaSimple {
    private Nodo cabeza; // primer nodo
    private Nodo cola;   // último nodo
    private int tamanio;

    /** Crea una lista vacía. */
    public ListaSimple() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    /**
     * Agrega un valor al final de la lista.
     * @param valor número a agregar
     */
    public void agregarFinal(int valor) {
        Nodo nuevo = new Nodo(valor);
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
     * Función Parcial3 del enunciado: inserta un nodo con valor -1 antes de
     * cada nodo cuyo valor sea impar.
     * @return cantidad de nodos insertados
     */
    public int parcial3() {
        int insertados = 0;
        Nodo anterior = null;   // nodo que está detrás de actual
        Nodo actual = cabeza;   // nodo que se está revisando

        while (actual != null) {
            if (actual.valor % 2 != 0) {
                // El valor es impar: se crea el nodo -1 y se coloca antes de actual
                Nodo nuevo = new Nodo(-1);
                nuevo.siguiente = actual;      // el nuevo apunta al nodo impar
                if (anterior == null) {
                    cabeza = nuevo;            // el impar era la cabeza: el nuevo es la nueva cabeza
                } else {
                    anterior.siguiente = nuevo; // el anterior ahora apunta al nuevo
                }
                insertados++;
                tamanio++;
            }
            // Se avanza: el nodo revisado pasa a ser el anterior
            // (así el siguiente -1 se enlaza después de él y no antes del -1 recién creado)
            anterior = actual;
            actual = actual.siguiente;
        }
        return insertados;
    }

    /**
     * Devuelve el contenido de la lista con el formato "a -> b -> NULL".
     * @return texto con la lista, o "NULL" si está vacía
     */
    public String aTexto() {
        StringBuilder texto = new StringBuilder();
        Nodo actual = cabeza;
        while (actual != null) {
            texto.append(actual.valor).append(" -> ");
            actual = actual.siguiente;
        }
        texto.append("NULL");
        return texto.toString();
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