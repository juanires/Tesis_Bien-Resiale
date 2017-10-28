package Hardware;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.lang.String;
import HardwareInterfaz.HISensor;
import Monitor.Monitor;
import java.util.ArrayList;

/**
 * Clase que interactua con el sensor de movimiento PIR. 
 * 
 * @author Bien Christopher - Resiale Juan
 */
public class Movimiento implements HISensor {
    
    private GpioController gpio;
    private GpioPinDigitalInput myButton;
    private GpioPinListenerDigital listener;
    private Pin pin;
    private String informacion;
    /**
     * Crea un nuevo objeto Movimiento. 
     * 
     * @param pin Número del GPIO donde se conecta el sensor de movimiento.
     * Posibles: 0 a 29 (límites incluidos).
     * @see "http://pi4j.com/pins/model-3b-rev1.html"
     */
    public Movimiento(GpioController gpio,int pin){
        
        informacion = new String("LOW");
        this.gpio = gpio; 
        myButton = null;
        listener=null;
        this.pin = RaspiPin.getPinByAddress(pin);
    }
    
    /**
     * El hilo comienza la ejecucion de acciones determinadas.
     * En el interior de este método se detallan las acciones que debe 
     * ejecutar el hilo cuando se invoque a la funcion "start()"
     * del objeto Movimiento.
     */
    @Override
    public void start(){
        configurar();
        activarListener();
        gpio.addListener(listener, myButton);
    }
    
    public void activarListener(){
      
        listener= new GpioPinListenerDigital() {
            
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // Acciones a realizar cuando se recibe un nivel alto
                if(event.getState().toString().equals("HIGH")){
                  //AQUIE SE REALIZAN LOS DISPAROS DEL MONITOR
                    System.out.println(" MOVIMIENTO");
                  
                }
            }
        };
    }
    
    /**
     * Activa las funciones del sensor de movimiento. 
     */
    @Override
    public void activar(){
        gpio.addListener(listener, myButton);      
    }
    
    /**
     * Desactiva las funciones del sensor de movimiento. 
     */
    @Override
    public void desactivar(){
       gpio.removeListener(listener, myButton);
    }
    
    /**
     * Retorna la información del sensor.
     * 
     * @return informacion. Retorna HIGH si se detecta movimiento, de lo contrario
     * retorna LOW
     */
    @Override
    public String getInfo(){
        return informacion;
    }
    
    /**
     * Configuracion del sensor.
     * Se asigna al GPIO 02 de la Raspberry como receptor de la señal del sensor
     * de movimiento. Este pin captura las interrupciones por cambio de nivel.
     */
    @Override
    public void configurar(){
        myButton = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
        myButton.setShutdownOptions(true);
        myButton.setDebounce(5);
    }
    
    /**
     * Setea la información del sensor.
     * 
     * @param info Nivel de voltaje (HIGH o LOW)
     */
    private void setInfo(String info){
        System.out.println(info);
        informacion = info;
    }
}
