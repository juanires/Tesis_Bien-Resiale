package ConcreteDevice;
import Device.Device;
import Monitor.Monitor;
import java.io.IOException;

/**
 *
 * @author Compuj
 */
public class Camera extends Device implements Runnable {
    
    private Thread thread;
    
    public Camera(){
        thread = null;
    }
    
    @Override
    protected void configure() {}

    @Override
    public void start() {
        
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        active(true);
    }

    @Override
    public void active(boolean option) {
        setActive(option);
    }
    
    private void takePicture(){
        
        try {
            String cmd = "curl http://localhost:"+port+"/0/action/snapshot"; 
            Runtime.getRuntime().exec(cmd); 
        } 
        catch (IOException ioe) {
            System.out.println (ioe);
        }
    }
    
     @Override
    public void run() {
        
        while(true){
            try {Thread.sleep(10);} 
            catch (InterruptedException ex) {}
     
            if(active){
            //----------------ACCIONES QUE REALIZA EL HILO-----------------------       
     
            }
        }
    }

    
    
}
