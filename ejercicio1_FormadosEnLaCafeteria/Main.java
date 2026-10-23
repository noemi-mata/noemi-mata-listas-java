/**
 * Problema: Formados en la cafetería
 * Autor: Noemi Mata Ruiz
 * Grupo: GTID244
 * Fecha: 22/10/2026
 * Descripción: Resuelve el problema usando una lista simple propia.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Procesa N eventos de la cafetería con dos colas:
 * trabajadores (prioridad) y alumnos.
 */
public class Main {

    /**
     * Punto de entrada: lee los eventos y imprime a las personas atendidas.
     * @param args no se usa
     * @throws IOException si falla la lectura de la entrada estándar
     */
    public static void main(String[] args) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder salida = new StringBuilder(); // acumula la salida para imprimir una sola vez

        ListaSimple colaAlumnos = new ListaSimple();
        ListaSimple colaTrabajadores = new ListaSimple();

        int n = Integer.parseInt(lector.readLine().trim());

        for (int i = 0; i < n; i++) {
            String[] partes = lector.readLine().trim().split(" ");

            if (partes[0].equals("LLEGA")) {
                // Formato: LLEGA <ALUMNO|TRABAJADOR> <nombre>
                if (partes[1].equals("ALUMNO")) {
                    colaAlumnos.agregarFinal(partes[2]);
                } else {
                    colaTrabajadores.agregarFinal(partes[2]);
                }
            } else {
                // ATIENDE: primero trabajadores; alumnos solo si no hay trabajadores
                String atendido;
                if (!colaTrabajadores.estaVacia()) {
                    atendido = colaTrabajadores.quitarInicio();
                } else {
                    atendido = colaAlumnos.quitarInicio();
                }
                salida.append(atendido).append('\n');
            }
        }
        System.out.print(salida);
    }
}