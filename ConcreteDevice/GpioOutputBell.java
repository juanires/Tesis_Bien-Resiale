package ConcreteDevice;
import Device.Device;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
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
                
               // monitor.disparar(transitions.get(0));
                ring();
            }
        }
    }
    
    public int getPinNumber(){
        return pinNumber;
    }
    
    private void ring(){
       
        monitor.disparar(transitions.get(0));
/*        pin.high();

        if(time == 0){ //Si no tiene tiempo asociado, debe ejecutar una transicion para apagarse
            monitor.disparar(transitions.get(1));
            pin.low();
        }
        else{//Si tiene tiempo asociado, despues de que éste transcurre se apaga.
            try {Thread.sleep(time*1000);} 
            catch (InterruptedException ex) {}
            pin.low();
        }*/
        LocalTime finalize3;
        LocalTime finalize2;
       // LocalTime finalize = LocalTime.now().plusSeconds(time); //Al tiempo actual se le añaden los segundos especificados en el campo time
       // while(LocalTime.now().isBefore(finalize)){ //Mientras no hayan transcurrido los segundos
            //Se genera un sonido de 250Hz
            finalize2 = LocalTime.now().plusSeconds(1); //Duracion del tono
            while(LocalTime.now().isBefore(finalize2)){ //Mientras no finalize la duracion del tono
                finalize3 = LocalTime.now().plusNanos(500000); //Se fija el semi periodo de la señal
                pin.high();
                while(LocalTime.now().isBefore(finalize3));
                finalize3 = LocalTime.now().plusNanos(500000);
                pin.low();
                while(LocalTime.now().isBefore(finalize3));
            }
            //SEGUNDO TONO
            finalize2 = LocalTime.now().plusSeconds(1); //Duracion del tono
            while(LocalTime.now().isBefore(finalize2)){ //Mientras no finalize la duracion del tono
                finalize3 = LocalTime.now().plusNanos(200000); //Se fija el semi periodo de la señal
                pin.high();
                while(LocalTime.now().isBefore(finalize3));
                finalize3 = LocalTime.now().plusNanos(200000);
                pin.low();
                while(LocalTime.now().isBefore(finalize3));
         //   }
        }
    }
}
