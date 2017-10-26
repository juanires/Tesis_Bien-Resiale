package Monitor;
import java.util.ArrayList;

/**
 *
 * @author Compuj
 */
public class Politica {
        
    private ArrayList <Integer> prioridad; //ArrayList para el manejo de prioridad

    public Politica(){

        prioridad = new ArrayList<Integer>();
    }

    
    public void añadirElemento(int d){
        prioridad.add(d);
    }

    public int getPrioridad(int d){
        return prioridad.get(d);
    }

    public void modificarPrioridad(int d){//Funcion que modifica la prioridad
        int temp = prioridad.get(d); //Se copia el contenido del array de la posicion "d"
        prioridad.remove(d); //Eliminamos el contenido dentro de la posicion d
        prioridad.add(temp); //Agregamos nuevamente este valor en la ultima posicion del Array
    }

    public int getNumeroDeElementos(){
        return prioridad.size();
    }
}
