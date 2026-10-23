/**
 * Problema: Formados en la cafetería
 * Autor: Noemi Mata Ruiz
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */

/**
 * Nodo de lista simple que representa una letra del alfabeto
 * y cuántas veces apareció en la cadena.
 */
public class Nodo {
    char letra;      // letra que representa este nodo ('a' a 'z')
    int cantidad;    // veces que apareció en la cadena
    Nodo siguiente;  // referencia a la siguiente letra

    /**
     * Crea un nodo con contador en cero.
     * @param letra letra que representa el nodo
     */
    public Nodo(char letra) {
        this.letra = letra;
        this.cantidad = 0;
        this.siguiente = null;
    }
}