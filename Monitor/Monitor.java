package Monitor;
import java.util.concurrent.Semaphore;

/**
 * Clase que representa a un monitor.  
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */

public class Monitor {
	
    private Semaphore Entrada;   //Semaforo que maneja la exclusion mutua del monitor
    private ProcesadorPetri Red;      //Red  de Petri con la que trabajara el monitor
    private VariableDeCondicion [] VCondicion; //Arreglo de variables de condicion, cada elemento del arreglo es una variable de condicion de la red
    private Politica politica;

    /**
     * Crea un nuevo monitor.
     * @param R procesador petri que maneja la lógica del monitor.
     */
    public Monitor(ProcesadorPetri R){

        Entrada = new Semaphore(1, true);
        Red = R;
        VCondicion = new VariableDeCondicion[Red.getTransiciones()]; 
        politica = new Politica();

        for(int i=0;i<Red.getTransiciones();i++){ //Una VCondicion por cada transicion de la red
            VCondicion[i] = new VariableDeCondicion(Entrada);
            politica.añadirElemento(i); //Agregamos dentro del array los numeros de posiciones
        }
    }

    /**
     * Disparar una transición.
     * @param disparo número de la transición a disparar.
     */
    public void disparar(int disparo){

        try {
            Entrada.acquire(); //Toma la exclusion mutua del monitor
           
            while(!Red.SolicitudDeDisparo(disparo)){
                VCondicion[disparo].DELAY(); //La exclusion mutua del monitor se libera en la VCondicion
                try{
                    Thread.currentThread().sleep(1);
                }
                catch (Exception e){}
            }
            //Si se pudo realizar el disparo...
            Red.disparar(disparo);

            for(int i=0; i<Red.getTransiciones(); i++){ //Se revisa si hay algun hilo bloqueado en una variable de condicion
                int d=politica.getPrioridad(i);	
                if(!VCondicion[politica.getPrioridad(i)].isEmpty()&& Red.SolicitudDeDisparo(politica.getPrioridad(i))){
                    int p = politica.getPrioridad(i);
                    politica.modificarPrioridad(i); // Se modifica la politica de la cola
                    VCondicion[p].RESUME(); //Si hay se lo despierta y no se libera la entrada del monitor, ya que el hilo despertado esta  en el monitor
                    break;
                }

                if(i+1 == Red.getTransiciones()){ //Si al final del ciclo no encontro hilos en las varibles de condicion, libera la EM
                    Entrada.release();
                        break;
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }		
    } 

    /**
     * Retorna la cantidad de hilos bloqueados en la VC especificada por el parametro d.
     * @param d número de transición.
     * @return numero de hilos bloqueados en la transición.
     */
    public int getBloqueados(int d){ 
        return VCondicion[d].getBloqueados();
    }
}

