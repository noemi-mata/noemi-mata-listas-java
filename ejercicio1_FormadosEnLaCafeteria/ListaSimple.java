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
    private Nodo cabeza; // primer nodo (el que se atiende primero)
    private Nodo cola;   // último nodo (donde se forma el siguiente)
    private int tamanio;

    /** Crea una lista vacía. */
    public ListaSimple() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    /**
     * Agrega una persona al final de la lista.
     * @param nombre nombre de la persona que llega
     */
    public void agregarFinal(String nombre) {
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
    public String quitarInicio() {
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
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Devuelve la cantidad de personas formadas.
     * @return tamaño de la lista
     */
    public int tamanio() {
        return tamanio;
    }
}