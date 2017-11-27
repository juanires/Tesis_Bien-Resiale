package ConcreteDevice;

import Readers.ReaderLastSnapshot;
import Readers.ReaderDate;
import Device.Device;
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
public class GpioListenerMovement extends Device {
    
    private GpioPinDigitalInput myButton;
    private GpioPinListenerDigital listener;
    private Pin pin;
    
    public GpioListenerMovement(){
       
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
        setActive(true);
    }

    @Override
    protected void active(boolean option) {
        if (option){
            gpio.addListener(listener, myButton);
        }
        else {
               gpio.removeListener(listener, myButton);
        }
    }
    
    public String getCode(){
        return null;
    }
    
    private void activeListener(){
        
        listener= new GpioPinListenerDigital() {
            
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // Acciones a realizar cuando se recibe un nivel alto
                if(event.getState().toString().equals("HIGH")){
                  //AQUI SE REALIZAN LOS DISPAROS DEL MONITOR
                    System.out.println( name + " HIGH");
                    monitor.disparar(transitions.get(0));
                    monitor.disparar(transitions.get(1));
                    //Ahora se guarda en base de datos
                    dataBase.insert("insert into events_" + name + " (date_time,image) values ('"+ReaderDate.read()+"','"+ReaderLastSnapshot.read()+"')");
                    //Se retornan reccursos
                    monitor.disparar(transitions.get(2));
                }
            }
        };
    }
    
    public int getPinNumber(){
        return pinNumber;
    }
    
    public int getPinState(){
        return 0;
    }
}
