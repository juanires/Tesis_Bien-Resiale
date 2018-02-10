package ConcreteDevice;
import Device.Device;
import Readers.ReaderLastSnapshot;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 * Esta clase se encarga de capturar fotos desde una cámara web. Implementa la Interfaz Runnable porque en esencia es un hilo que intenta capturar
 * una foto cuando cuando se produce algún evento. Las imagenes capturadas se guardan en el path especificado en el
 * archivo de configuración del programa "Motion". Se conecta a la cámara mediante un puerto específico.
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class Camera extends Device implements Runnable {
    
    private Thread thread;
    private String lastSnapshot;
        
    /**
    * Crea un nuevo objeto Camera.
    */
    public Camera(){
        thread = null;
        lastSnapshot = null;
    }
    
    /**
    * Se realiza la configuración del objeto. En este caso en particular es un método con un cuerpo vacío.
    */
    @Override
    protected void configure() {}

    /**
    * Crea e inicializa el hilo, configura y activa el objeto. La activación del objeto se hace mediante la llamada al método
    * "setActive(true)" de la clase abstracta.
    */
    @Override
    public void start() {
        
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        setActive(true);
    }

    /**
    * Activa o desactiva la acción que realiza el objeto.
    * @param option "true" para activar, "false" para desactivar.
    */
    @Override
    protected void active(boolean option) {}
    
    /**
    * Captura una foto. Cuando se llama a este método, se corre un subproceso que ejecuta el
    * comando de linux "curl" más una url, el resultado de la ejecución de éste comando es la obtención
    * de una nueva foto en el path especificado.
    * Tanto la url del parámetro del comando curl, el puerto y el path donde se guardan las imágenes dependen del programa que
    * controla la cámara web, que en este caso en el "Motion". Para especificar path y puerto se debe modificar el archivo
    * de configuración de este programa(generalmente ubicado en "/etc/motion/" con el nombre "motionconf.conf").
    *
    */
    protected void takePicture(){
    
        try {
            String cmd = "curl http://localhost:"+port+"/0/action/snapshot";
            Process p= Runtime.getRuntime().exec(cmd);
            p.waitFor();
            while(p.isAlive()){
                try { Thread.currentThread().sleep(250);}
                catch (InterruptedException ex) {
                    System.out.println("ERROR1"); 
                }
            }
            p.destroyForcibly();
        }
        catch (IOException ioe) {
            System.out.println (ioe);
        } 
        catch (InterruptedException ex) {
            Logger.getLogger(Camera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
    * No se implementa para objeto.
    * @return null.
    */
    @Override
    public String getCode(){
        return null;
    }
    
    /**
    * No se implementa para objeto.
    * @return 0.
    */
    @Override
    public int getPinState(){
        return 0;
    }
    
    /**
    * Ejecuta las acciones del objeto. En el cuerpo de éste método se establecen las acciones que realiza el objeto.
    * En este caso, dispara la primer transición, llama al método "takePicture()" y espera a que el link
    * simbólico que se encuentra en el mismo directorio donde se almacenan las imágenes se actualice, es decir
    * apunte a la imágen que se acaba de capturar. Luego de ejecutar estas acciones dispara la segunda transición. 
    */
    @Override
    public void run() {
        
        while(true){
            if(isActive()){
                //----------------ACCIONES QUE REALIZA EL HILO----------------------- 
                monitor.disparar(transitions.get(0));
                lastSnapshot= ReaderLastSnapshot.read();
                takePicture();
                while(lastSnapshot.equals(ReaderLastSnapshot.read())){
                    takePicture();
                    try { Thread.currentThread().sleep(300);} 
                    catch (InterruptedException ex) {} 
                }
                monitor.disparar(transitions.get(1));
            }
        }
    }
}
