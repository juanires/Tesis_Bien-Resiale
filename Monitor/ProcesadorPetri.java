package Monitor;
    import java.lang.String;
/**
 *
 * @author Compuj
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
		
    public boolean disparar (int Disparo){

        if(SolicitudDeDisparo(Disparo)){
            for(int i=0; i<plazas; i++){ 
                matrizEstado[i] = matrizEstadoAux[i];//Actualizamos la matriz de Estado al disparar la transicion "disparo"
            }
            return true;
        }
        else return false;
    }
	
    public int getPlaza(){
        return plazas;
    }
	
    public int getTransiciones(){
        return transiciones;
    }
	
    public boolean SolicitudDeDisparo(int TransADisparar){

        for(int i=0; i<plazas; i++){ //M0 + I*D
            matrizEstadoAux[i] = matrizEstado[i] + matrizI[i][TransADisparar];
            if (matrizEstadoAux[i] == -1)
                return false;
        }
        return true;
    }

    public int getMatriz(int a, int b){
        int temp= matrizI[a][b];
        return temp;
    }
}
