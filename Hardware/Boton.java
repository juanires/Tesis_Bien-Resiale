package Hardware;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.lang.String;
import HardwareInterfaz.HIPulsador;
import HardwareInterfaz.HISensor;
import java.util.ArrayList;
/**
 * Clase que interactua con el hardware pulsador. 
 * 
 * @author Bien Christopher - Resiale Juan
 */


public class Boton implements HIPulsador {
    
    private GpioController gpio;
    private GpioPinDigitalInput myButton;
    private GpioPinListenerDigital listener;
    private Pin pin;
    private String estado;
    
    /**
     * Crea un nuevo objeto Boton. 
     * 
     * @param pin Número del GPIO donde se conecta el boton.
     * Posibles: 0 a 29 (límites incluidos).
     * @see "http://pi4j.com/pins/model-3b-rev1.html"
     */
    public Boton(GpioController gpio, int pin){
    
        estado = new String("LOW");
        this.gpio = gpio; 
        myButton = null;
        listener=null;
        this.pin = RaspiPin.getPinByAddress(pin);
    }
    
    public void start(){
       configurar();
       listener();
       gpio.addListener(listener, myButton);
    }
      
    /**
     * Activa las funciones del objeto Boton.
     */
    @Override
    public void activar(){
        gpio.addListener(listener, myButton);
    }
    
    /**
     * Desactiva las funciones del objeto Boton. 
     */
    @Override
    public void desactivar(){
         gpio.removeListener(listener, myButton); 
    }
    
     /**
     * Configuracion del pulsador.
     * Se asigna el GPIO identificado por el parametro "pin" de la Raspberry
     * como receptor de la señal del boton. 
     * Este pin captura las interrupciones por cambio de nivel.
     */
    @Override
    public void configurar(){
        myButton = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
        myButton.setShutdownOptions(true);
        myButton.setDebounce(500);
        
    }
    
    public void listener(){
        
       listener= new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // Acciones a realizar cuando se recibe un nivel alto
                if(event.getState().toString().equals("HIGH")){
                 
                   System.out.println(" SE PRESIONO EL PULSADOR 1 "+ myButton.getName());
                   //AQUI SE REALIZAN LOS DISPAROS DEL MONITOR
                }
            }
        };
    }   
}
