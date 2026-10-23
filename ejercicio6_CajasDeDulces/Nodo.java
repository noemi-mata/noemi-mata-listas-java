/**
 * Problema: Cajas de dulces
 * Autor: Noemi Mata
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */

/**
 * Nodo de lista simple que guarda la cantidad de dulces de una caja.
 */
public class Nodo {
    int dulces;      // cantidad de dulces de la caja
    Nodo siguiente;  // referencia a la caja que está al lado

    /**
     * Crea un nodo sin enlazar.
     * @param dulces cantidad de dulces de la caja
     */
    public Nodo(int dulces) {
        this.dulces = dulces;
        this.siguiente = null;
    }
}