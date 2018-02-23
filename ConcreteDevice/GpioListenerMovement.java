package ConcreteDevice;
import Readers.ReaderSnapshot;
import Readers.ReaderDate;
import Device.Device;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;


/**
 * Esta clase se encarga de verificar si se ha detectado movimiento. Esto lo realiza creando un listener que
 * detecta cuando hay un cambio de nivel lógico de "alto" a "bajo" en el pin asignado a este objeto. 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class GpioListenerMovement extends Device  implements Runnable {
    
    private Thread thread;
    private GpioPinDigitalInput myButton;
    private GpioPinListenerDigital listener;
    private Pin pin;
    
    /**
    * Crea un nuevo objeto GpioListenerMovement.
    */
    public GpioListenerMovement(){
        thread = null;
        myButton = null;
        listener = null;
    }
    
    /**
    * Se realiza la configuración del objeto.
    * Para más informacion visitar http://pi4j.com/example/listener.html para entender las configuraciones que se realizan.
    */
    @Override
    protected void configure(){
        pin = RaspiPin.getPinByAddress(pinNumber);
        myButton = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
        myButton.setShutdownOptions(true);
        myButton.setDebounce(500);
    }

    /**
    * Configura, crea e inicializa el listener, y activa el objeto. La activación del objeto se hace mediante la llamada al método
    * "setActive(true)" de la clase abstracta.
    */
    @Override
    public void start() {
        configure();
        activeListener();
        setActive(true);
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
    * Activa o desactiva la acción que realiza el objeto. Al desactivar, se remueve el listener; al activar, se agrega el
    * listener.
    * @param option "true" para activar, "false" para desactivar.
    */
    @Override
    protected void active(boolean option) {
        if (option){
            gpio.addListener(listener, myButton);
        }
        else {
               gpio.removeListener(listener, myButton);
        }
    }
    
    /**
    * No se implementa para objeto.
    * @return null.
    */   
    @Override
    public String getCode(){
        return null;
    }
    
    @Override
    public void run() {
        
        while(true){
            
            monitor.disparar(29); //Se consulta  la base de datos
            if(dataBase.movementSlotTime()){
                monitor.disparar(30); //Se libera la base de datos
                if(!isActive()){ //Si el dispositivo no esta activo
                    setActive(true); //Se activa
                }
            }
            else{//Si no se esta en el rango de deteccion
                monitor.disparar(30); //Se libera la base de datos
                if(isActive()){  //Si esta activado
                    setActive(false); //Se desactiva
                }
            }
            try { Thread.currentThread().sleep(time*1000);}
            catch (InterruptedException ex) {System.out.println("ERROR1");}
        }
    }
        
    
    /**
    * Se activa el listener. Dentro del cuerpo de éste método se crea un nuevo objeto "GpioPinListenerDigital" y se sobreescribe
    * su método "handleGpioPinDigitalStateChangeEvent" que es donde se epecifican las acciones que debe realizar el hilo cuando detecta
    * un cambio de nivel lógico en el pin. En este caso se realizan las acciones cuando se detecte un cambio de "HIGH" a "LOW".
    */
    private void activeListener(){
        
        listener= new GpioPinListenerDigital() {
            
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // Acciones a realizar cuando se recibe un nivel alto
                if(event.getState().toString().equals("LOW")){
                  //AQUI SE REALIZAN LOS DISPAROS DEL MONITOR
                    System.out.println( name + " LOW");
                    monitor.disparar(transitions.get(0));
                    monitor.disparar(transitions.get(1));
                    //Ahora se guarda en base de datos
                    dataBase.insertEventMovement();
                    //Se retornan reccursos
                    monitor.disparar(transitions.get(2));
                }
            }
        };
    }
    
    /**
    * Retorna el GPIO asignado al objeto.
    * @return pinNumber. 
    */
    public int getPinNumber(){
        return pinNumber;
    }
    
    /**
    * No se implementa para objeto.
    * @return 0.
    */
    @Override
    public int getPinState(){
        return 0;
    }

    
}
