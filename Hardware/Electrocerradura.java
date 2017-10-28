package Hardware;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import HardwareInterfaz.HICerradura;

/**
 * Clase que interactua con una electrocerradura. 
 * 
 * @author Bien Christopher - Resiale Juan
 */ 
public class Electrocerradura extends Thread implements HICerradura {

    private GpioController gpio;
    private GpioPinDigitalOutput pin;
    private int numPin;
    private int tiempo;
    private boolean habilitado;
    
    /**
     * Crea un nuevo objeto Electrocerradura.
     * 
     * @param pin Número del GPIO donde se conecta la campana.
     * Posibles: 0 a 29 (límites incluidos).
     * @param tiempo Cantidad de tiempo en segundos que se mantiene abierta la
     * electrocerradura.
     * @see "http://pi4j.com/pins/model-3b-rev1.html"
     */
    public Electrocerradura(GpioController gpio, int pin, int tiempo){
    
        this.gpio= gpio;
        this.pin = null;
        numPin = pin;
        this.tiempo = tiempo;
        habilitado = true;
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
        abrir(tiempo);
        try {
                Thread.sleep(5000);
            } 
            catch (InterruptedException ex) {
                System.err.println("Error sleep Thread");
            }
        //-------------------------------------
        }
    }
    
    /**
     * Abre la electrocerradura durante los segundos especificados por el 
     * parametro "seg".
     * 
     * @param seg Tiempo (en segundos) que se mantiene abierta la electrocerradura.
     */
    @Override
    public void abrir(int seg) {
        pin.high();
        try {
            Thread.sleep(seg*1000);
        } 
        catch (InterruptedException ex) {
            System.err.println("Error sleep Thread");
        }
        pin.low();
    }
    
    /**
     * Activa las funciones de la electrocerradura. 
     */
    @Override
    public void activar(){
        habilitado = true;
    }

    /**
     * Desactiva las funciones de la electrocerradura. 
     */
    @Override
    public void desactivar(){
        habilitado = false;   
    }
                 
    /**
     * Configuracion de la campana.
     * Se asigna el GPIO identificado por el parametro "pin" de la Raspberry 
     * como salida digital, a la cual va a estar conectada la electrocerradura. 
     */
    @Override
    public void configurar(){
        pin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(numPin),PinState.LOW);
        pin.setShutdownOptions(true, PinState.LOW);
    }
}
