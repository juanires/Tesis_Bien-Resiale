package ConcreteDevice;
import Device.Device;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Esta clase se encarga de hacer sonar una campana. 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class GpioOutputBell extends Device implements Runnable {

    private GpioPinDigitalOutput pin;
    private Thread thread;
  
    /**
    * Crea un nuevo objeto GpioOutputBell.
    */
    public GpioOutputBell(){
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
    @Override
    public int getPinState(){
        if(pin.isHigh())
            return 1;
        return 0;
    }
    
    /**
    * Realiza las acciones del objeto GpioOutputBell.
    */
    @Override
    public void run() {
        while(true){
     //----------------ACCIONES QUE REALIZA EL HILO-----------------------
            if(isActive()){
                monitor.disparar(transitions.get(0));
                ring();
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
    * Método encargado de generar el sonido de la campana.
    *  
    */
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
