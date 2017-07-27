package Hardware;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import HardwareInterfaz.Sonido;

/**
 * Clase que interactua con una campana o bocina. 
 * 
 * @author Bien Christpher - Resiale Juan
 */ 
public class Campana extends Thread implements Sonido {
    
    
    private GpioController gpio;
    private GpioPinDigitalOutput pin;
    private int numPin;
    
    
    /**
     * Crea un nuevo objeto Campana. 
     * 
     * @param pin Número del GPIO donde se conecta la campana.
     * Posibles: 0 a 29 (límites incluidos).
     * @see "http://pi4j.com/pins/model-3b-rev1.html"
     */
    public Campana(int pin){
    
        gpio= null;
        this.pin = null;
        numPin = pin;
    }
    
    /**
     * El hilo comienza la ejecucion de acciones determinadas.
     * En el interior de este método se detallan las acciones que debe 
     * ejecutar el hilo cuando se invoque a la funcion "start()"
     * del objeto Boton.
     */
    @Override
    public void run(){
        
        configurar();
               
        while(true) {
            //ACCIONES QUE REALIZA EL OBJETO
        //------------------------------------
        
        //-------------------------------------
        }
    }
    
    /**
     * Emitir sonido. 
     */
    @Override
    public void encender(){
        pin.high();
    }
    
    /**
     * Dejar de emitir sonido. 
     */
    @Override
    public void apagar(){
        pin.low();
    }
    
    
    /**
     * Activa las funciones del objeto Campana. 
     */
    @Override
    public void activar(){
        configurar();
    }
    
    /**
     * Desactiva las funciones del objeto Campana. 
     */
    @Override
    public void desactivar(){
        gpio.shutdown();    
    }
    
    
    /**
     * Configuracion de la campana.
     * Se asigna el GPIO identificado por el parametro "pin" de la Raspberry 
     * como salida digital, a la cual va a estar conectada la campana. 
     */
    @Override
    public void configurar(){
       gpio = GpioFactory.getInstance();
       pin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(numPin),PinState.LOW);
    }
}
