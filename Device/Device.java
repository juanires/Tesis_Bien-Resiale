package Device;
import DataBase.DataBase;
import Monitor.Monitor;
import com.pi4j.io.gpio.GpioController;
import java.util.List;

/**
 * Clase abstracta que define los métodos que deben implementar todos los dispositivos. También implementa
 * métodos comunes a todos.
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public abstract class Device {
    //Campos
    protected Monitor monitor;
    protected List<Integer> transitions;
    protected String name;
    protected GpioController gpio;
    protected int pinNumber;
    protected int port;
    protected int time;
    protected boolean active;
    protected DataBase dataBase;
    protected int nextTransitions;
    protected Device device;
    
    /**
    * Se configura el dispositivo. 
    */
    abstract protected void configure();
    
    /**
    * Se inician las acciones del dispositivo. 
    */
    abstract public void start();
    
    /**
    * Se activa el dispositivo.
    * @param option True para activar, False para desactivar.
    */
    abstract protected void active(boolean option);
    
    /**
    * Se obtiene el código leído por el dispositvo.
    * @return codigo.
    */
    abstract public String getCode();
    
    /**
    * Retorna el estado del pin que controla al dispositivo.
    * @return estado del pin.
    */
    abstract public int getPinState();
    
    /**
    * Retorna el nombre del dispositivo.
    * @return nombre del dispositivo.
    */
    public String getName(){
        return name;
    }
    
    /**
    * Se establece una base de datos para que interactue con el dispositivo.
    * @param dataBase base de datos que interactúa con el dispositivo.
    */
    public void setDataBase(DataBase dataBase){
        this.dataBase = dataBase;
    }
    
    /**
    * Se establece el monitor para que interactue con el dispositivo.
    * @param monitor.
    */
    public void setMonitor(Monitor monitor){
        this.monitor = monitor;
    }
    
    /**
    * Se establecen las transiciones que tiene que disparar el dispositivo.
    * @param transitions Lista de enteros que contiene los números que se corresponden con las transiciones
    * que el dispositivo debe ejecutar.
    */
    public void setTransitions(List<Integer> transitions){
        this.transitions = transitions;
    }
    
    /**
    * Se establece el nombre del dispositivo.
    * @param name nombre del dispositivo.
    */
    public void setName(String name){
        this.name = name;
    }
    
    /**
    * Se establece un controlador GPIO al dispositivo.
    * @param gpio controlador GPIO.
    */
    public void setGpioController(GpioController gpio){
        this.gpio = gpio;
    }
    
    /**
    * Se establece el número de GPIO que va a controlar el dispositivo.
    * @param pinNumber Número de GPIO del embebido asignado al dispositivo.
    */
    public void setPinNumber(int pinNumber){
        this.pinNumber = pinNumber;
    }
    
    /**
    * Se establece el número de puerto que va a utilizar el dispositivo.
    * @param port número de puerto.
    */
    public void setPort(int port){
        this.port = port;
    }
    
    /**
    * Se establece el tiempo asociado al dispositivo.
    * @param time tiempo expresado en segundos.
    */
    public void setTime(int time){
        this.time = time;
    }
    
    /**
    * Activa o desactiva el dispositivo.
    * @param setActive True para activar, False para desactivar.
    */
    public void setActive(boolean setActive){
        active(setActive);
        this.active = setActive;
    }
    
    /**
    * Se establece otro dispositivo para que interactue con este dispositivo.
    * @param device dispositivo a asociar.
    */
    public void setDevice(Device device){
        this.device = device;
    }
    
    /**
    * Retorna True o False si el dispositivo está activado o desactivado respetivamente.
    * @return True si está activado, False si está desactivado.
    */
    public boolean isActive(){
        return active;
    }
    
    /**
    * Retorna la siguiente transición a disparar. El número de transición a disparar se obtiene 
    * de la lista de transiciones "transitions" del dispositivo. Cada llamada a la función retorna el número
    * de transición de una posición de la lista, partiendo desde la posición 0. Cuando se llega a la última transición,
    * se reinicia, por lo que la próxima llamada retorna nuevamente el número que se encuentra en la posición cero.
    * Básicamente lo que hace la función toma la lista como un buffer circular y avanza de posición del buffer en cada llamada.
    * @return  número de transición a disparar.
    */
    protected int getNextTransitions(){
        
        int result;
        if(nextTransitions < transitions.size()){
           result = transitions.get(nextTransitions);
           nextTransitions ++;
        }
        else{
            nextTransitions = 0;
            result = transitions.get(nextTransitions);
            nextTransitions ++;
        } 
        return result;
    }
    
    /**
    * Establece que la próxima transición a disparar es la primera de la lista.
    */
    protected void resetNextTransitions(){
        nextTransitions = 0;
    }
}
