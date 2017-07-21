/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hardware;

import HardwareInterfaz.Sensor;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.lang.String;

/**
 *
 * @author Bien-Resiale
 */
public class Movimiento extends Thread implements Sensor {
    
    private String informacion;
    private GpioController gpio;
    GpioPinDigitalInput myButton;
    
    /**
     * Crea un nuevo objeto Movimiento. 
     */
    public Movimiento(){
        
        informacion = new String();
        gpio = null; 
        myButton = null;
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
                    setInfo("HIGH");
                }
                setInfo("LOW");
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
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
        myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
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
