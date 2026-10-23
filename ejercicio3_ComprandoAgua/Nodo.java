/**
 * Problema: Comprando agua
 * Autor: Noemi Mata
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */

/**
 * Nodo de lista simple que guarda el precio de una marca de agua.
 */
public class Nodo {
    int precio;      // precio de la botella
    Nodo siguiente;  // referencia a la siguiente marca

    /**
     * Crea un nodo sin enlazar.
     * @param precio precio de la marca
     */
    public Nodo(int precio) {
        this.precio = precio;
        this.siguiente = null;
    }
}