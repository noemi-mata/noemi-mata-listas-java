/**
 * Problema: Cuadro de Honor
 * Autor: Noemi Mata
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */

/**
 * Lista enlazada simple de nombres, siempre ordenada alfabéticamente.
 */
public class ListaSimple {
    private Nodo cabeza; // primer nombre en orden alfabético
    private int tamanio;

    /** Crea una lista vacía. */
    public ListaSimple() {
        cabeza = null;
        tamanio = 0;
    }

    /**
     * Inserta un nombre en la posición que mantiene el orden alfabético.
     * Si hay nombres iguales, el nuevo queda después de los que ya estaban.
     * @param nombre nombre completo del alumno
     */
    public void insertarOrdenado(String nombre) {
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
     * Devuelve la cantidad de alumnos en la lista.
     * @return tamaño de la lista
     */
    public int tamanio() {
        return tamanio;
    }
}