package Hardware;
import java.io.IOException;
import HardwareInterfaz.HICamara;
import java.util.ArrayList;

/**
 * Clase que interactua con una cámara. 
 * 
 * @author Bien Christpher - Resiale Juan
 */ 
public class CamaraWeb extends Thread implements HICamara{
    
    private int port; //Puerto de acceso a la camara web
       
    /**
     * Crea un nuevo objeto CamaraWeb.
     * @param port Puerto de donde se obtiene la imágen. Se corresponde con
     * el puerto establecido para la configuracion de Motion en su archivo
     * de configuración.
     */
    public CamaraWeb(int port){
    
        this.port= port;
    }     
    
    /**
     * El hilo comienza la ejecucion de acciones determinadas.
     * En el interior de este método se detallan las acciones que debe 
     * ejecutar el hilo cuando se invoque a la funcion "start()"
     * del objeto CamaraWeb.
     */
    @Override
    public void run(){
        
     while (true){
        
         //AQUI SE REALIZAN LOS DISPAROS DEL MONITOR
         capturaFoto();
         
     }
        
    /*    try {
                Thread.sleep(20000);
            } 
            catch (InterruptedException ex) {
                System.err.println("Error sleep Thread");
            }
        capturaFoto();*/
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
            String cmd = "curl http://localhost:"+port+"/0/action/snapshot"; 
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
