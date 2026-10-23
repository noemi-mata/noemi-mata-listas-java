/**
 * Problema: Inserción en Lista Enlazada
 * Autor: Noemi Mata
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */

/**
 * Nodo de lista simple que guarda un número entero.
 */
public class Nodo {
    int valor;       // número guardado en el nodo
    Nodo siguiente;  // referencia al siguiente nodo

    /**
     * Crea un nodo sin enlazar.
     * @param valor número a guardar
     */
    public Nodo(int valor) {
        this.valor = valor;
        this.siguiente = null;
    }
}