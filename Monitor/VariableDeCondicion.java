package Monitor;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Compuj
 */
public class VariableDeCondicion {
    
    private Semaphore mutex;  //Exclusion mutua del monitor (se pasa como parametro)	
    private Semaphore micond; //Semaforo propio de la variable de condicion
    int bloqueados;           //Contador de la cantidad de hilos bloqueados en la variable
	
    public VariableDeCondicion (Semaphore mutex){
        this.mutex=mutex;
	micond= new Semaphore (0, true);
	bloqueados=0;
    }
	
    public void DELAY(){ //Bloqueo del hilo
	bloqueados++;
	mutex.release(); //Se libera el mutex del Monitor
	try {
            micond.acquire();
	} 
	catch (InterruptedException e) {
            e.printStackTrace();
	}
    }
	
    public void RESUME(){//Se despierta un hilo
	
        if(bloqueados>0){ //Si hay hilos bloqueados en esta condicion...    
            bloqueados--;
            micond.release();
        }
    }
	
    public boolean isEmpty(){ //Retorna "true" si hay hilos bloqueados en esta variable de condicion, de lo contrario retorna "false"
        return bloqueados==0;
    }
    
    public int getBloqueados(){
	return bloqueados;
    }
	
}
