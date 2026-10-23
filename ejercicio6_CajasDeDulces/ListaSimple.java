/**
 * Problema: Cajas de dulces
 * Autor: Noemi Mata
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */

/**
 * Lista enlazada simple con las cajas del estante, en el orden en que están.
 * Guarda referencia al último nodo para agregar al final en O(1).
 */
public class ListaSimple {
    private Nodo cabeza; // primera caja
    private Nodo cola;   // última caja
    private int tamanio;

    /** Crea una lista vacía. */
    public ListaSimple() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    /**
     * Agrega una caja al final de la lista.
     * @param dulces cantidad de dulces de la caja
     */
    public void agregarFinal(int dulces) {
        Nodo nuevo = new Nodo(dulces);
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
     * Calcula la suma máxima de k cajas consecutivas con una ventana deslizante.
     * @param k cantidad de cajas que se pueden llevar (1 <= k <= tamaño de la lista)
     * @return suma máxima de dulces, o -1 si k no es válido
     */
    public int sumaMaximaVentana(int k) {
        if (cabeza == null || k < 1 || k > tamanio) {
            return -1;
        }

        Nodo inicio = cabeza; // primera caja de la ventana
        Nodo fin = cabeza;    // última caja de la ventana

        // 1. Suma de la primera ventana: las primeras k cajas
        int suma = cabeza.dulces;
        for (int i = 1; i < k; i++) {
            fin = fin.siguiente;
            suma += fin.dulces;
        }
        int maximo = suma;

        // 2. Se desliza la ventana una caja a la vez hasta llegar al final de la lista
        while (fin.siguiente != null) {
            fin = fin.siguiente;       // entra la caja nueva por la derecha
            suma += fin.dulces;
            suma -= inicio.dulces;     // sale la caja de la izquierda
            inicio = inicio.siguiente;
            if (suma > maximo) {
                maximo = suma;         // esta ventana es la mejor hasta ahora
            }
        }
        return maximo;
    }

    /**
     * Indica si la lista no tiene elementos.
     * @return true si está vacía
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Devuelve la cantidad de cajas.
     * @return tamaño de la lista
     */
    public int tamanio() {
        return tamanio;
    }
}