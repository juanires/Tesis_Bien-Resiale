package Hardware;

import HardwareInterfaz.Led;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Clase que interactua con disposivo de LED. 
 * 
 * @author Bien Christopher - Resiale Juan
 */ 
public class LedPin implements Led{
    
    private GpioController gpio;
    private GpioPinDigitalOutput pin;
    private int numPin;
    
    /**
     * Crea un nuevo objeto LedPin. 
     * 
     * @param pin Número del GPIO donde se conecta el LED.
     * Posibles: 0 a 29 (límites incluidos).
     * @see "http://pi4j.com/pins/model-3b-rev1.html"
     */
    public LedPin(int pin){
    
        gpio= null;
        this.pin = null;
        numPin = pin;
    }
    
    /**
     * Encender LED. 
     */
    @Override
    public void encender(){
        pin.high();
    }
    
    /**
     * Apagar LED. 
     */
    @Override
    public void apagar(){
        pin.low();
    }
        
    /**
     * Activa las funciones del objeto PinLed. 
     */
    @Override
    public void activar(){
        configurar();
    }
    
    /**
     * Desactiva las funciones del objeto PinLed. 
     */
    @Override
    public void desactivar(){
        gpio.shutdown();    
    }
        
    /**
     * Configuracion del LED.
     * Se asigna el GPIO identificado por el parametro "pin" de la Raspberry 
     * como salida digital, a la cual va a estar conectado el LED. 
     */
    @Override
    public void configurar(){
       gpio = GpioFactory.getInstance();
       pin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(numPin),PinState.LOW);
    }
}
