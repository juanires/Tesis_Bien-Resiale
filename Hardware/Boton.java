package Hardware;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.lang.String;
import HardwareInterfaz.HIPulsador;
import HardwareInterfaz.HISensor;
/**
 * Clase que interactua con el hardware pulsador. 
 * 
 * @author Bien Christopher - Resiale Juan
 */


public class Boton extends Thread implements HIPulsador {
    
    private GpioController gpio;
    private GpioPinDigitalInput myButton;
    private Pin pin;
    
    /**
     * Crea un nuevo objeto Boton. 
     * 
     * @param pin Número del GPIO donde se conecta el boton.
     * Posibles: 0 a 29 (límites incluidos).
     * @see "http://pi4j.com/pins/model-3b-rev1.html"
     */
    public Boton(int pin){
    
        gpio = null; 
        myButton = null;
        this.pin = RaspiPin.getPinByAddress(pin);
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
        
        myButton.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
               
                // Acciones a realizar cuando se recibe un nivel alto
                if(event.getState().toString()=="HIGH"){
                    
                    System.out.println(" SE PRESIONO EL PULSADOR ");
                    
                    try { //A continuacion se duerme el hilo para ignorar los rebotes
                        Thread.sleep(400);
                    } 
                    catch (InterruptedException ex) {
                    }
                }
                //setInfo("LOW");
                //System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
            }
        });
        while(true) {
            try {
                Thread.sleep(500);
            } 
            catch (InterruptedException ex) {
                System.err.println("Error sleep Thread");
            }
        }
        
    }
           
    /**
     * Activa las funciones del objeto Boton.
     */
    @Override
    public void activar(){
        configurar(); 
    }
    
    /**
     * Desactiva las funciones del objeto Boton. 
     */
    @Override
    public void desactivar(){
         gpio.shutdown(); //detiene todas las actividades / subprocesos de GPIO cerrando el controlador GPIO
    }
    
     /**
     * Configuracion del pulsador.
     * Se asigna el GPIO identificado por el parametro "pin" de la Raspberry
     * como receptor de la señal del boton. 
     * Este pin captura las interrupciones por cambio de nivel.
     */
    @Override
    public void configurar(){
        gpio = GpioFactory.getInstance(); //Se crea un controlador gpio 
        myButton = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
        myButton.setShutdownOptions(true);
    }
    
}
