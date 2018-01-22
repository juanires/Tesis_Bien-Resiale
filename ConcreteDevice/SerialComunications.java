package ConcreteDevice;
import Device.Device;
import com.pi4j.io.gpio.exception.UnsupportedBoardType;
import com.pi4j.io.serial.Baud;
import com.pi4j.io.serial.DataBits;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialConfig;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataEventListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.StopBits;
import java.io.IOException;

/**
 * Esta clase se encarga de interactuar con el lector RFID mediante el puerto serial. 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class SerialComunications  extends Device {

    private Serial serial;
    private SerialDataEventListener listener;
    private String code;
    
    /**
    * Crea un nuevo objeto SerialComunications.
    */
    public SerialComunications(){
        serial = SerialFactory.createInstance();
        listener=null;
        code = null;
    }
    
    /**
    * Se configura el puerto serial. Para entender las connfiguraciones que se realizan visitar http://pi4j.com/apidocs/index.html
    */
    @Override
    protected void configure() {
        // create serial config object
        SerialConfig config = new SerialConfig();
        //Se establecen las configuraciones del puerto serial
        try {
            config.device("/dev/ttyAMA0");
            config.baud(Baud._1200);
            config.dataBits(DataBits._8);
            config.parity(Parity.NONE);
            config.stopBits(StopBits._1);
            config.flowControl(FlowControl.NONE);
            serial.open(config); // Se abre el puerto serial con las configuraciones establecidas
        } 
        catch (UnsupportedBoardType ex) {
            System.err.println("Modelo o tipo de hardware no soportado");
        }
        catch (IOException ex) {
            System.err.println("Error al abrir puerto serie");
        }
    }

    /**
    * Configura, crea e inicializa el listener, y activa el objeto. La activación del objeto se hace mediante la llamada al método
    * "setActive(true)" de la clase abstracta.
    */
    @Override
    public void start() {
        configure();
        activeListener();
        setActive(true);
    }

    /**
    * Activa o desactiva la acción que realiza el objeto. Al desactivar, se remueve el listener; al activar, se agrega el
    * listener.
    * @param option "true" para activar, "false" para desactivar.
    */
    @Override
    protected void active(boolean option) {
        if (option){
            cleanBuffer();
            serial.addListener(listener);
        }
        else {
            serial.removeListener(listener);
        }
    }
    
    /**
    * Se activa el listener. Dentro del cuerpo de éste método se crea un nuevo objeto "SerialDataEventListener" y se sobreescribe
    * su método "dataReceived" que es donde se epecifican las acciones que debe realizar el hilo cuando recibe informacion
    * por el puerto serial. 
    */
    private void activeListener(){
         
        listener = new SerialDataEventListener() {
            @Override
            public void dataReceived(SerialDataEvent event) {

                try {
                    //Es muy importante leer los datos recibidos del puerto serie. 
                    //Si no se lee el búfer de recepción, éste continuará creciendo y consumiendo memoria.
                   int n= event.length(); //Se obtiene el numero de bytes del buffer serial
                   //El codigo que envia el lector es de 12 bytes 
                   //pero se reciben primero 8, y despues 4
                   if(n==12){ 
                    //ACCIONES A REALIZAR CUANDO SE RECIBE EL CODIGO
                        setCode(event.getAsciiString().substring(0, 10));
                        monitor.disparar(transitions.get(0));
                        event.discardData();
                   }
                   else {
                       if(n>12) //Si por alguna razon hay mas de 12 bytes, se limpia el buffer
                         event.discardData();
                   }
                } 
                catch (IOException ex) {
                    System.err.println("Error al leer puerto serie");
                }
            }
        };
    }
    
    /**
     * Se limpia el buffer de recepción.
    */
    private void cleanBuffer(){
         try {
            serial.discardInput(); //Se borra el buffer de recepcion
        } 
        catch (IOException ex) {}
    }
    
    /**
     * Se guarda el código leído.
     * @param code Código leído.
     */
    private void setCode(String code){
        this.code = code;
        System.out.println(code);
    }
    
    /**
     * Retorna el código leído.
     * @return code - código leído.
     */
    @Override
    public String getCode(){
        return code;
    }
    
    /**
    * No se implementa para objeto.
    * @return 0.
    */
    @Override
     public int getPinState(){
        return 0;
    }
}
