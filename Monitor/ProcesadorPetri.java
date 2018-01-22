package Monitor;
import java.lang.String;

/**
 * Clase que representa a una red de petri. 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class ProcesadorPetri {
    
    private  int transiciones;
    private  int plazas;
    private  int [] matrizEstado;
    private  int [][] matrizI;
    private  int[] matrizEstadoAux;
    private  String urlMatrizIncidencia;
    private  String urlMatrizEstado;
    private  LectorDeMatriz lector;
	
	
    /**
     * Crea un nuevo objeto ProcesadorPtri.
     * @param transiciones cantidad de transiciones de la red de petri.
     * @param plazas cantidad de plazas de la red de petri.
     * @param urlMatrizIncidencia url del archivo que almacena la matriz de incidencia.
     * @param urlMatrizEstado url del archivo que almacena la matriz de estado.
     */
    public ProcesadorPetri(int transiciones, int plazas, String urlMatrizIncidencia, String urlMatrizEstado){
		
        this.transiciones = transiciones;
        this.plazas = plazas;
        matrizEstado = new int[plazas];
        matrizI = new int [plazas][transiciones];
        matrizEstadoAux = new int[plazas]; //Matriz de Estado auxiliar
        this.urlMatrizIncidencia = urlMatrizIncidencia;
        this.urlMatrizEstado = urlMatrizEstado;
        lector = new LectorDeMatriz(transiciones,plazas,matrizI,matrizEstado,urlMatrizIncidencia,urlMatrizEstado);
        lector.leer();
    }
		
    /**
     * Dispara una transición.
     * @param Disparo número de la transición a disparar.
     * @return True si el disparo se realizó, en caso contrario retorna False.
     */
    public boolean disparar (int Disparo){

        if(SolicitudDeDisparo(Disparo)){
            for(int i=0; i<plazas; i++){ 
                matrizEstado[i] = matrizEstadoAux[i];//Actualizamos la matriz de Estado al disparar la transicion "disparo"
            }
            return true;
        }
        else return false;
    }
	
    /**
     * Retorna la cantidad de plazas que tiene de la red de petri.
     * @return cantidad de plazas de la red de petri.
     */
    public int getPlaza(){
        return plazas;
    }

    /**
     * Retorna la cantidad de transiciones que tiene de la red de petri.
     * @return cantidad de transiciones de la red de petri.
     */
    public int getTransiciones(){
        return transiciones;
    }
	
    /**
     * Verifica si es posible realizar el disparo. 
     * @param TransADisparar número de la transición a disparar.
     * @return True si es posible realizar el disparo solicitado, de lo contrario retorna False.
     */
    public boolean SolicitudDeDisparo(int TransADisparar){

        for(int i=0; i<plazas; i++){ //M0 + I*D
            matrizEstadoAux[i] = matrizEstado[i] + matrizI[i][TransADisparar];
            if (matrizEstadoAux[i] == -1)
                return false;
        }
        return true;
    }
}
