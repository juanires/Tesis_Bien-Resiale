package ConcreteDevice;

import Device.Device;
import Monitor.Monitor;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Compuj
 */
public class GpioOutput extends Device implements Runnable {

    private GpioPinDigitalOutput pin;
    private Thread thread;
  
    public GpioOutput(){
        
        pin = null;
        thread = null;
    }
    
    @Override
    protected void configure() {
       pin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(pinNumber),PinState.LOW);
       pin.setShutdownOptions(true, PinState.LOW);
    }

    @Override
    public void start() {
        configure();
       
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        active(true);
    }

    @Override
    public void active(boolean option) {
        setActive(option);
    }

    @Override
    public void run() {
        while(true){
            try {Thread.sleep(10);} 
            catch (InterruptedException ex) {}
     //----------------ACCIONES QUE REALIZA EL HILO-----------------------
            if(active){
                
                monitor.disparar(getNextTransitions());
                pin.high();
                
                if(time == 0){
                    monitor.disparar(getNextTransitions());
                    pin.low();
                }
                else{
                    try {Thread.sleep(time*1000);} 
                    catch (InterruptedException ex) {}
                    pin.low();
                }
            }
        }
    }
    
    public int getPinNumber(){
        return pinNumber;
    }
    
    
}
