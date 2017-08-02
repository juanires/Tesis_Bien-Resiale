package ProcesadorPetri;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Compuj
 */
public class Transicion {
    
    private Semaphore micond; //Semaforo propio de la transicion
    int bloqueados;           //Contador de la cantidad de hilos bloqueados en la transicion
    
    public Transicion(){
        micond= new Semaphore (0, true);
        bloqueados=0;
    }
    
}
