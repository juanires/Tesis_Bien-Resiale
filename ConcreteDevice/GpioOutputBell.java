package ConcreteDevice;
import Device.Device;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import static java.lang.Thread.MAX_PRIORITY;
import java.time.LocalTime;


/**
 *
 * @author Compuj
 */
public class GpioOutputBell extends Device implements Runnable {

    private GpioPinDigitalOutput pin;
    private Thread thread;
  
    public GpioOutputBell(){
        
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
               // monitor.disparar(transitions.get(0));
                ring();
            }
        }
    }
    
    public int getPinNumber(){
        return pinNumber;
    }
    
    private void ring(){
       
       pin.high();
       try {Thread.sleep(100);} 
       catch (InterruptedException ex){}
       pin.low();
       try {Thread.sleep(30);} 
       catch (InterruptedException ex){}
       pin.high();
       try {Thread.sleep(100);} 
       catch (InterruptedException ex){}
       pin.low();
    }
}
