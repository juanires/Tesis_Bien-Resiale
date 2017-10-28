package Hardware;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import HardwareInterfaz.HILed;

/**
 * Clase que interactua con disposivo de LED. 
 * 
 * @author Bien Christopher - Resiale Juan
 */ 
public class Led implements HILed{
    
    private GpioController gpio;
    private GpioPinDigitalOutput pin;
    private int numPin;
    private boolean habilitado;
    
    /**
     * Crea un nuevo objeto LedPin. 
     * 
     * @param pin Número del GPIO donde se conecta el LED.
     * Posibles: 0 a 29 (límites incluidos).
     * @see "http://pi4j.com/pins/model-3b-rev1.html"
     */
    public Led(GpioController gpio, int pin){
    
        this.gpio = gpio;
        this.pin = null;
        numPin = pin;
        habilitado = true;
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
         habilitado = true;
    }
    
    /**
     * Desactiva las funciones del objeto PinLed. 
     */
    @Override
    public void desactivar(){
         habilitado = false;    
    }
        
    /**
     * Configuracion del LED.
     * Se asigna el GPIO identificado por el parametro "pin" de la Raspberry 
     * como salida digital, a la cual va a estar conectado el LED. 
     */
    @Override
    public void configurar(){
       pin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(numPin),PinState.LOW);
       pin.setShutdownOptions(true, PinState.LOW);
    }
}
