package Hardware;

import HardwareInterfaz.Sensor;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.lang.String;

/**
 * Clase que interactua con el sensor de movimiento PIR. 
 * 
 * @author Bien Christopher - Resiale Juan
 */
public class Movimiento extends Thread implements Sensor {
    
    private String informacion;
    private GpioController gpio;
    private GpioPinDigitalInput myButton;
    private Pin pin;
    
    /**
     * Crea un nuevo objeto Movimiento. 
     * 
     * @param pin Número del GPIO donde se conecta el sensor de movimiento.
     * Posibles: 0 a 29 (límites incluidos).
     * @see "http://pi4j.com/pins/model-3b-rev1.html"
     */
    public Movimiento(int pin){
        informacion = new String();
        gpio = null; 
        myButton = null;
        this.pin = RaspiPin.getPinByAddress(pin);
    }
    
    /**
     * El hilo comienza la ejecucion de acciones determinadas.
     * En el interior de este método se detallan las acciones que debe 
     * ejecutar el hilo cuando se invoque a la funcion "start()"
     * del objeto Movimiento.
     */
    @Override
    public void run(){
    
        configurar();
        myButton.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
               
                // Acciones a realizar cuando se recibe un nivel alto
                if(event.getState().toString()=="HIGH"){
                   // setInfo("HIGH");
                   System.out.println("SE DETECTO MOVIMIENTO");
                }
               // setInfo("LOW");
               // System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
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
     * Activa las funciones del sensor de movimiento. 
     */
    @Override
    public void activar(){
        configurar();        
    }
    
    /**
     * Desactiva las funciones del sensor de movimiento. 
     */
    @Override
    public void desactivar(){
        gpio.shutdown(); //detiene todas las actividades / subprocesos de GPIO cerrando el controlador GPIO 
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
        gpio = GpioFactory.getInstance(); //Se crea un controlador gpio 
        myButton = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
        myButton.setShutdownOptions(true);
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
