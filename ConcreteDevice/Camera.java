package ConcreteDevice;
import Device.Device;
import Readers.ReaderLastSnapshot;
import java.io.IOException;


/**
 *
 * @author Compuj
 */
public class Camera extends Device implements Runnable {
    
    private Thread thread;
    private String lastSnapshot;
    
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
        setActive(true);
    }

    @Override
    protected void active(boolean option) {}
    
    private void takePicture(){
        
        try {
            String cmd = "curl http://localhost:"+port+"/0/action/snapshot"; 
            Runtime.getRuntime().exec(cmd); 
        } 
        catch (IOException ioe) {
            System.out.println (ioe);
        }
    }
    
    public String getCode(){
        return null;
    }
    
    public int getPinState(){
        return 0;
    }
    
    @Override
    public void run() {
        
        while(true){
            if(isActive()){
                //----------------ACCIONES QUE REALIZA EL HILO----------------------- 
                monitor.disparar(transitions.get(0));
                lastSnapshot = ReaderLastSnapshot.read();
                takePicture();
                while(lastSnapshot.equals(ReaderLastSnapshot.read())){//Hasta que no se actualice el link simbolico no se dispara la proxima transicion
                    try {Thread.sleep(1);} 
                    catch (InterruptedException ex) {}
                } 
                monitor.disparar(transitions.get(1));
            }
        }
    }
}
