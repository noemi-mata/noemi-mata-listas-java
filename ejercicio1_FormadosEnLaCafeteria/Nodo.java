/**
 * Problema: Formados en la cafetería
 * Autor: Noemi Mata Ruiz
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */

/**
 * Nodo de una lista enlazada simple que guarda el nombre de una persona.
 */

public class Nodo {
    String nombre;   // nombre de la persona formada
    Nodo siguiente;  // referencia a la persona que está detrás

    /**
     * Crea un nodo sin enlazar.
     * @param nombre nombre de la persona
     */

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }
}