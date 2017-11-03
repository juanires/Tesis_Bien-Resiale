package ConcreteDevice;


import Device.Device;
import Monitor.Monitor;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 *
 * @author Compuj
 */
public class GpioListener extends Device {
    
    private GpioPinDigitalInput myButton;
    private GpioPinListenerDigital listener;
    private Pin pin;
    
    public GpioListener(){
       
        myButton = null;
        listener = null;
    }
    
    @Override
    protected void configure(){
        pin = RaspiPin.getPinByAddress(pinNumber);
        myButton = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
        myButton.setShutdownOptions(true);
        myButton.setDebounce(500);
    }

    @Override
    public void start() {
        configure();
        activeListener();
        active(true);
    }

    @Override
    public void active(boolean option) {
        if (option){
            gpio.addListener(listener, myButton);
            setActive(option);
        }
        else {
               gpio.removeListener(listener, myButton);
               setActive(option);
        }
    }
    
    private void activeListener(){
        
        listener= new GpioPinListenerDigital() {
            
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // Acciones a realizar cuando se recibe un nivel alto
                if(event.getState().toString().equals("HIGH")){
                  //AQUI SE REALIZAN LOS DISPAROS DEL MONITOR
                    System.out.println( name + " HIGH");
                  
                }
            }
        };
    }
    
    public int getPinNumber(){
        return pinNumber;
    }
}
