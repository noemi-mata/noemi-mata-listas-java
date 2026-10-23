/**
 * Problema: Derdenio y sus Estructuras de Datos
 * Autor: Noemi Mata
 * Grupo: GTID244
 * Fecha: 23/10/2026
 * Descripción: Resuelve el problema usando una lista doble propia.
 */

/**
 * Nodo de lista doblemente enlazada que guarda un número entero.
 */
public class Nodo {
    long valor;      // número guardado en el nodo
    Nodo siguiente;  // referencia al nodo de la derecha (el mayor)
    Nodo anterior;   // referencia al nodo de la izquierda (el menor)

    /**
     * Crea un nodo sin enlazar.
     * @param valor número a guardar
     */
    public Nodo(long valor) {
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }
}