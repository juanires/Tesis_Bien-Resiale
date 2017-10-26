package Monitor;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Compuj
 */
public class LectorDeMatriz {
    
    private int [][] matrizIncidencia;
    private int [] matrizEstado;
    private int plazas;
    private int transiciones;
    private String urlMatrizIncidencia;
    private String urlMatrizEstado;
    
    public LectorDeMatriz(int transiciones, int plazas, int [][] matrizIncidencia, int[] matrizEstado, String urlMatrizIncidencia, String urlMatrizEstado){
		
        this.matrizIncidencia =  matrizIncidencia;
        this.matrizEstado = matrizEstado;
        this.plazas = plazas;
        this.transiciones = transiciones;
        this.urlMatrizIncidencia = urlMatrizIncidencia;
        this.urlMatrizEstado = urlMatrizEstado;
    }
    
    public void leer(){

        try{
            File fileMatrizIncidencia = new File(urlMatrizIncidencia); 
            File fileMatrizDeEstado = new File (urlMatrizEstado);
            Scanner scIncidencia = new Scanner(fileMatrizIncidencia);//FileNotFoundException
            Scanner scEstado = new Scanner(fileMatrizDeEstado);

            while(scIncidencia.hasNext()){ //Se lee y carga la matriz de incidencia

                for(int i=0; i<plazas;i++){
                    for (int j=0; j<transiciones; j++){
                        matrizIncidencia[i][j] = scIncidencia.nextInt();
                    }
                }
            }
            scIncidencia.close();

            while(scEstado.hasNext()){ //Se lee y se carga el vector de estado
                for(int i=0; i<plazas;i++)
                    matrizEstado[i] = scEstado.nextInt();
            }
            scEstado.close();
        }
        catch(FileNotFoundException e){
            System.out.println("No se pudo abrir el archivo");
        }
    }
}
