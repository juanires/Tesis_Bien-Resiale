package ConcreteDevice;
import Device.Device;
import Monitor.Monitor;
import com.pi4j.io.gpio.GpioController;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Compuj
 */
public class SerialComunications  extends Device {

    private Serial serial;
    private SerialDataEventListener listener;
    private String code;
    
    public SerialComunications(){
    
        serial = SerialFactory.createInstance();
        listener=null;
        code = null;
    }
    
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

    @Override
    public void start() {
        configure();
        activeListener();
        active(true);
    }

    @Override
    public void active(boolean option) {
        if (option){
            cleanBuffer();
            serial.addListener(listener);
            setActive(option);
        }
        else {
            serial.removeListener(listener);
            setActive(option);
        }
    }
    
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
                        monitor.disparar(getNextTransitions());
                        setCode(event.getAsciiString().substring(0, 10));
                       //Aqui se ejecutan las transiciones
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
    
    private void cleanBuffer(){
         try {
            serial.discardInput(); //Se borra el buffer de recepcion
        } 
        catch (IOException ex) {}
    }
    
    private void setCode(String code){
        this.code = code;
        System.out.println(code);
    }
    
    public String getCode(){
        return code;
    }
}
