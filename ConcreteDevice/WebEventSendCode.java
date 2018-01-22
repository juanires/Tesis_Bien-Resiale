package ConcreteDevice;
import Device.Device;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase se encarga de receptar las solicitudes de envío de código que se receptan desde la web y actuar
 * en consecuencia. Como la página web se encuentra almacenada en un servidor que corre en el mismo embebido, 
 * para comunicar la página web con el programa principal, este objeto crea un ServerSocket mediante el cual "escucha"
 * en el puerto especificado en el parámetro de creacion del Device. Cuando el cliente se conecta a este servidor se realizan
 * las acciones establecidas en el método run de este objeto.
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class WebEventSendCode extends Device implements Runnable{

    private Thread thread;
    private BufferedReader dataInput;
    PrintWriter dataOutput;
    private Socket socket;
    private ServerSocket serverSocket;
     
    /**
    * Crea un nuevo objeto WebEventSendCode.
    */
    public WebEventSendCode(){
        thread = null;
        dataInput = null;
        dataOutput = null;
        socket = null;
        serverSocket = null;
    }
       
    /**
    * Método no implementado.
    */
    @Override
    protected void configure() {}

    /**
    * Crea e inicializa el hilo que ejecuta las acciones y activa el objeto. La activación del objeto se hace mediante la llamada al método
    * "setActive(true)" de la clase abstracta.
    */
    @Override
    public void start() {
        
        configure();
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        setActive(true);
    }

    /**
    * Activa o desactiva la acción que realiza el objeto. Al desactivar, se cierra el serverSocket; al activar,
    * se crea un nuevo serverSocket.
    * @param option "true" para activar, "false" para desactivar.
    */
    @Override
    protected void active(boolean option) {
        if(option){
            try { 
                serverSocket = new ServerSocket(port,1,InetAddress.getLocalHost());//127.0.1.1
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

    /**
    * No se implementa para objeto.
    * @return null.
    */
    @Override
    public String getCode() {
        return null;
    }
    
    /**
    * No se implementa para objeto.
    * @return 0.
    */
    public int getPinState(){
        return 0;
    }

    /**
    * Realiza las acciones del objeto WebEventOpenDoor.
    */
    @Override
    public void run() {
         
        while(true){
        //----------------ACCIONES QUE REALIZA EL HILO-----------------------
            if(isActive()){
                try {
                    socket = serverSocket.accept(); //Espera una nueva conexion
                    monitor.disparar(transitions.get(0));
                    monitor.disparar(transitions.get(1));
                    dataOutput = new PrintWriter(socket.getOutputStream());
                    dataOutput.print(device.getCode());
                    dataOutput.flush();
                    dataOutput.close();
                    socket.close();
                    monitor.disparar(transitions.get(2));                    
                } 
                catch (IOException ex) {
                    Logger.getLogger(WebEventSendCode.class.getName()).log(Level.SEVERE, null, ex);
                    monitor.disparar(transitions.get(2));
                }
            }
        }
    }
}
