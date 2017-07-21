package Hardware;
import HardwareInterfaz.Camara;
import java.io.IOException;

/**
 * Clase que interactua con una cámara. 
 * 
 * @author Bien Christpher - Resiale Juan
 */ 
public class CamaraWeb extends Thread implements Camara{
    
    /**
     * Crea un nuevo objeto CamaraWeb. 
     */
    public CamaraWeb(){}
            
    
    /**
     * El hilo comienza la ejecucion de acciones determinadas.
     * En el interior de este método se detallan las acciones que debe 
     * ejecutar el hilo cuando se invoque a la funcion "start()"
     * del objeto CamaraWeb.
     */
    @Override
    public void run(){
        
        
    }
    
    /**
     * Activa las funciones del objeto CamaraWeb. 
     */
    @Override
    public void activar(){}
    
    /**
     * Desactiva las funciones del objeto CamaraWeb. 
     */
    @Override
    public void desactivar(){}
    
    /**
     * Captura una foto. Se guarda en el directorio configurado en el
     * software "Motion".
     */
    @Override
    public void capturaFoto(){
        try {
            String cmd = "curl http://localhost:8080/0/action/snapshot"; 
            Runtime.getRuntime().exec(cmd); 
        } 
        catch (IOException ioe) {
            System.out.println (ioe);
        }
    }
    
    /**
     * Captura un video. Se guarda en el directorio configurado en el
     * software "Motion". No está implementada.
     * 
     * @param seg. Duración de la filmacion en segundos.
     */
    @Override
    public void capturaVideo(int seg){
    }
    
}
