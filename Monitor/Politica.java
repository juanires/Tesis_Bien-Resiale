package Monitor;
import java.util.ArrayList;

/**
 * Clase que define la política de un monitor. En este caso se implementa con un array de enteros, 
 * el cual contendrá el número identificador de cada variable de condición. La política de prioridad 
 * es una política Round Robin.
 * EJEMPLO: Suponga que se está trabajando con cuatro variables de condición, entonces el objeto 
 * prioridad en un comienzo será el siguiente: 1|2|3|4. Cuando un hilo dentro del monitor comience a revisar 
 * las variables de condición para ver si hay hilos dormidos dentro, empezará por la 1 seguirá por la 2 y 3, 
 * y la última que revisara será la variable de condición 4; suponga que encontró un hilo dormido y lo despertó 
 * en la variable de condición 1; pues entonces se modifica la prioridad para la próxima entrada de un hilo, y los elementos dentro 
 * dentro del objeto que maneja las prioridades del monitor será la siguiente: 2|3|4|1.
 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class Politica {
        
    private ArrayList <Integer> prioridad; //ArrayList para el manejo de prioridad

    /**
     * Crea un nuevo objeto Politica.
     */
    public Politica(){
        prioridad = new ArrayList<Integer>();
    }

    /**
     * Se adiciona el número identificador de una varibale de condición.
     * @param d número de una variable de condición.
     */
    public void aniadirElemento(int d){
        prioridad.add(d);
    }

    /**
     * Se obtiene el valor de la varible de condición que se encuentra en la posición d de la lista. 
     * @param d posición de la lista que controla la prioridad.
     * @return valor de la varible de condición que se encuentra en la posición d de la lista.
     */
    public int getPrioridad(int d){
        return prioridad.get(d);
    }

    /**
     * Se modifica la lista de prioridades.
     * @param d posición de la lista a modificar.
     */
    public void modificarPrioridad(int d){//Funcion que modifica la prioridad
        int temp = prioridad.get(d); //Se copia el contenido del array de la posicion "d"
        prioridad.remove(d); //Eliminamos el contenido dentro de la posicion d
        prioridad.add(temp); //Agregamos nuevamente este valor en la ultima posicion del Array
    }

    /**
     * Retorna el número de elementos de la lista de prioridad.
     * @return número de elementos de la lista de prioridad.
     */
    public int getNumeroDeElementos(){
        return prioridad.size();
    }
}
