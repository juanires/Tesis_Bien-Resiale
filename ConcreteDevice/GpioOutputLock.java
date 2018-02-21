package ConcreteDevice;
import Device.Device;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Esta clase se encarga de controlar el funcionamiento de una cerradura (eléctrica o magnética). 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class GpioOutputLock extends Device implements Runnable {

    private GpioPinDigitalOutput pin;
    private Thread thread;
  
    /**
    * Crea un nuevo objeto GpioOutputLed.
    */
    public GpioOutputLock(){
        
        pin = null;
        thread = null;
    }
    
    /**
    * Se realiza la configuración del objeto.
    * Para más informacion visitar http://pi4j.com/example/control.html para entender las configuraciones que se realizan.
    */
    @Override
    protected void configure() {
       pin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(pinNumber),PinState.LOW);
       pin.setShutdownOptions(true, PinState.LOW);
    }

    /**
    * Configura el pin, crea e inicializa el hilo que ejecuta las acciones y activa el objeto. La activación del objeto se hace mediante la llamada al método
    * "setActive(true)" de la clase abstracta.
    */
    @Override
    public void start() {
        configure();
       
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        setActive(true);
    }

    /**
    * No se implementa para objeto.
    */
    @Override
    protected void active(boolean option) {}

    /**
    * No se implementa para objeto.
    * @return null.
    */ 
    @Override
    public String getCode(){
        return null;
    }
    
    /**
    * Retorna el estado del pin.
    * @return 1 si es "alto", 0 si es "bajo".
    */ 
    public int getPinState(){
        if(pin.isHigh())
            return 1;
        return 0;
    }
    
    /**
    * Realiza las acciones del objeto GpioOutputLock.
    */
    @Override
    public void run() {
        while(true){
     //----------------ACCIONES QUE REALIZA EL HILO-----------------------
            if(isActive()){
                monitor.disparar(transitions.get(0));
                open();
            }
        }
    }
    /**
    * Retorna el GPIO asignado al objeto.
    * @return pinNumber. 
    */
    public int getPinNumber(){
        return pinNumber;
    }
    
    /**
    * Abre la cerradura durante el lapso de tiempo especificado en el parámetro "time". 
    */
    public void open(){
        pin.high();
        //Si tiene tiempo asociado, despues de que éste transcurre se apaga.
        try {Thread.sleep(time*1000);} 
        catch (InterruptedException ex) {}
        pin.low();
        
    }
}
