package ConcreteDevice;
import Device.Device;
import java.io.BufferedReader;
import java.io.DataInputStream;
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
public class WebEventSendCode extends Device implements Runnable{

    private Thread thread;
    private BufferedReader dataInput;
    private DataOutputStream dataOutput;
    private Socket socket;
    private ServerSocket serverSocket;
     
    public WebEventSendCode(){
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
                   //dataInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));//Se recibe informacion
                   //String g = dataInput.readLine();
                   //while (g!= "fin/n"){
                    
                     //  g = dataInput.readLine();
                     //  System.out.println(g);
                   //}
                  // System.out.println(dataInput.readLine());
                    //if(dataInput.readLine().equals("sendCode")){
                       monitor.disparar(transitions.get(0));
                       monitor.disparar(transitions.get(1));
                       dataOutput = new DataOutputStream(socket.getOutputStream());
                      // String g = device.getCode();
                       dataOutput.writeUTF(device.getCode());
                       monitor.disparar(transitions.get(2));
                    //}
                    socket.close();
                } 
                catch (IOException ex) {
                    Logger.getLogger(WebEventSendCode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
    }
    
}
