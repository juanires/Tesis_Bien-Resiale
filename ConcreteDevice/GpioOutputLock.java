package ConcreteDevice;
import Device.Device;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


/**
 *
 * @author Compuj
 */
public class GpioOutputLock extends Device implements Runnable {

    private GpioPinDigitalOutput pin;
    private Thread thread;
  
    public GpioOutputLock(){
        
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
        setActive(true);
    }

    @Override
    protected void active(boolean option) {}

    
    @Override
    public String getCode(){
        return null;
    }
    
     public int getPinState(){
        if(pin.isHigh())
            return 1;
        return 0;
    }
    
    @Override
    public void run() {
        while(true){
     //----------------ACCIONES QUE REALIZA EL HILO-----------------------
            if(isActive()){
                
                monitor.disparar(transitions.get(0));
                pin.high();
                
                if(time == 0){ //Si no tiene tiempo asociado, debe ejecutar una transicion para apagarse
                    monitor.disparar(transitions.get(1));
                    pin.low();
                }
                else{//Si tiene tiempo asociado, despues de que éste transcurre se apaga.
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
