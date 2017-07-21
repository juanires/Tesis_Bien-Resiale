package Hardware;

import HardwareInterfaz.Lector;
import java.lang.String;
import com.pi4j.io.gpio.exception.UnsupportedBoardType;
import com.pi4j.io.serial.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Compuj
 */
public class RFID extends Thread implements Lector {
    
    private String codigo;
    private Serial serial;
    
    public RFID(){
        codigo=new String("");
        serial = SerialFactory.createInstance();
    }
    
    @Override
    public void run(){
    
        configurar();
        try {
            serial.discardInput(); //Se borra el buffer de recepcion
        } 
        catch (IOException ex) {
            Logger.getLogger(RFID.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        serial.addListener(new SerialDataEventListener() {

            @Override
            public void dataReceived(SerialDataEvent event) {
                
                try {
                    
                    //Es muy importante leer los datos recibidos del puerto serie. 
                    //Si no se lee el búfer de recepción, éste continuará creciendo y consumiendo memoria.
                   int n= event.length(); //Se obtiene el numero de bytes del buffer serial
                   //El codigo que envia el lector es de 12 bytes 
                   //pero se reciben primero 8, y despues 4
                   if(n==12){ //ACCIONES A REALIZAR CUANDO SE RECIBE EL CODIGO
                        setCodigo(event.getAsciiString().substring(0, 10));
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
        });
        while (true){
            try {
                Thread.sleep(2000);
            } 
            catch (InterruptedException ex) {
                System.err.println("Error sleep Thread");
            }
        }
    }
    
    @Override
    public void activar(){
        if(serial.isClosed()){
            configurar();       
        }
    }
    
    @Override
    public void desactivar(){
        if(serial.isOpen()){
            try {
            serial.close();
            } 
            catch (IOException ex) {
                 System.err.println("Error al cerrar puerto serie");
            }
        }
    }
    
    @Override
    public String getCodigo(){
        return codigo;
    }
    
    private  void configurar(){
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
    
    private void setCodigo(String cod){
        codigo= cod;
        System.out.println(codigo);
    }
}
