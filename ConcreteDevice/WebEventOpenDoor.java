package ConcreteDevice;
import Device.Device;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Compuj
 */
public class WebEventOpenDoor extends Device implements Runnable {

    private Thread thread;
    private BufferedReader dataInput;
    private DataOutputStream dataOutput;
    private Socket socket;
    private ServerSocket serverSocket;
    
    public WebEventOpenDoor(){
        thread = null;
        dataInput = null;
        dataOutput = null;
        socket = null;
        serverSocket = null;
    }
    
    @Override
    protected void configure() {
        
    }

    @Override
    public void start() {
       
        configure();
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        setActive(true);
    }

    @Override
    protected void active(boolean option) {
        if(option){
            try { 
                serverSocket = new ServerSocket(port);
                //FALTA BINDEAR
            } 
            catch (IOException ex) {
                Logger.getLogger(WebEventOpenDoor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                serverSocket.close();
            } 
            catch (IOException ex) {
                Logger.getLogger(WebEventOpenDoor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public void run() {
        while(true){
        //----------------ACCIONES QUE REALIZA EL HILO-----------------------
            if(isActive()){
         
                try {
                    socket = serverSocket.accept(); //Espera una nueva conexion
                    dataInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));//Se recibe informacion
                    while(!dataInput.ready()); //Mietras el dato no este listo, espera
                    String data = dataInput.readLine();
                    System.out.println(data);
                    socket.close();
                } 
                catch (IOException ex) {
                    Logger.getLogger(WebEventOpenDoor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
    
}
