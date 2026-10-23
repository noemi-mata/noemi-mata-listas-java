/**
 * Problema: Supermercado
 * Autor: Noemi Mata
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */

/**
 * Nodo de lista simple que guarda un producto con su precio.
 */
public class Nodo {
    String nombre;   // nombre del producto
    double precio;   // precio del producto
    Nodo siguiente;  // referencia al siguiente producto

    /**
     * Crea un nodo sin enlazar.
     * @param nombre nombre del producto
     * @param precio precio del producto
     */
    public Nodo(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.siguiente = null;
    }
}