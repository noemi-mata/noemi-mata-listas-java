/**
 * Problema: Cuadro de Honor
 * Autor: Noemi Mata
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */

/**
 * Nodo de lista simple que guarda el nombre de un alumno del cuadro de honor.
 */
public class Nodo {
    String nombre;   // nombre completo del alumno
    Nodo siguiente;  // referencia al siguiente alumno

    /**
     * Crea un nodo sin enlazar.
     * @param nombre nombre completo del alumno
     */
    public Nodo(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }
}