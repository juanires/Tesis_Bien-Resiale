package Monitor;
import java.util.concurrent.Semaphore;

/**
 * Clase que representa a una variable de condición del monitor.
 * En las variables de condición van a residir los procesos que no hayan podido disparar una transición 
 * a causa de que no estaba disponible el recurso que necesitan. Habrá tantas variables de condición como 
 * transiciones tenga la RdP con la que se está trabajando, y la identificación de cada variable de condición, 
 * se corresponderá con la identificación de la transición de la red. 
 * Por ejemplo, la variable de condición 4 corresponderá a la transición T4 de la red.
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class VariableDeCondicion {
    
    private Semaphore mutex;  //Exclusion mutua del monitor (se pasa como parametro)	
    private Semaphore micond; //Semaforo propio de la variable de condicion
    int bloqueados;           //Contador de la cantidad de hilos bloqueados en la variable
	
    /**
     * Crea una nueva variable de condición.
     * @param mutex semáforo que maneja la entrada al monitor.
     */
    public VariableDeCondicion (Semaphore mutex){
        this.mutex=mutex;
	micond= new Semaphore (0, true);
	bloqueados=0;
    }
	
    /**
     * El hilo que ejecuta este método se "duerme" indefectiblemente.
     */
    public void DELAY(){ //Bloqueo del hilo
	bloqueados++;
	mutex.release(); //Se libera el mutex del Monitor
	try {
            micond.acquire();
	} 
	catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("ERROR");
	}
    }
	
    /**
     * Se "despierta" un hilo dormido en la variable de condición.
     */
    public void RESUME(){//Se despierta un hilo
	
        if(bloqueados>0){ //Si hay hilos bloqueados en esta condicion...    
            bloqueados--;
            micond.release();
        }
    }
	
    /**
     * Retorna True si la variable de condición está vacia, de lo contrario retorna False.
     * @return True si la variable de condición está vacia, de lo contrario retorna False.
     */
    public boolean isEmpty(){ //Retorna "true" si hay hilos bloqueados en esta variable de condicion, de lo contrario retorna "false"
        return bloqueados==0;
    }
    
    /**
     * Se obtiene el número de hilos bloqueados en la variable de condición.
     * @return número de hilos bloqueados en la variable de condición.
     */
    public int getBloqueados(){
	return bloqueados;
    }
}
