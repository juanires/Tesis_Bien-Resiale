package ConcreteDevice;
import Device.Device;
import Readers.ReaderDate;
import Readers.ReaderLastSnapshot;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
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
    protected void configure() {}

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
                serverSocket = new ServerSocket(port,1,InetAddress.getLocalHost());
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
    
    public int getPinState(){
        return 0;
    }

    @Override
    public void run() {
        while(true){
        //----------------ACCIONES QUE REALIZA EL HILO-----------------------
            if(isActive()){
         
                try {
                    socket = serverSocket.accept(); //Espera una nueva conexion
                    socket.setSoTimeout(10000); //Se espera recibir informacion durante 5 segundos
                    dataInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));//Se recibe informacion
                    String data = dataInput.readLine();
                    System.out.println(data);
                    //Se disparan recien aqui las transiciones para evitar inconvenientes en caso de TimeOut
                    monitor.disparar(transitions.get(0));
                    monitor.disparar(transitions.get(1));//Se guarda en base de datos
                    dataBase.insert("insert into events_" + name.toLowerCase() + "(date_time,user_id,image)  values ('"+ReaderDate.read()+"',"+Integer.parseInt(data)+",'"+ReaderLastSnapshot.read()+"')");
                    monitor.disparar(transitions.get(2));
                } 
                catch (SocketTimeoutException ex) { //En caso que haya expirado el TimeOut tengo que 
                    System.out.println("El tiempo a expirado");
                }
                catch (IOException ex) {
                    System.out.println("Error en Socket");
                }
                try {
                    socket.close();
                } 
                catch (IOException ex) {
                    System.out.println("Error al cerrar Socket");
                }
            }
        }

    }
    
}
