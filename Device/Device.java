package Device;

import Monitor.Monitor;
import com.pi4j.io.gpio.GpioController;


/**
 *
 * @author Compuj
 */
public abstract class Device {
    //Campos
    protected Monitor monitor;
    protected int [] transitions;
    protected String name;
    protected GpioController gpio;
    protected int pinNumber;
    protected int port;
    protected boolean active;
   
    
    //Metodos
    abstract protected void configure();
    abstract public void start();
    abstract public void active(boolean option);
    
    public String getName(){
        return name;
    }
    
    public void setMonitor(Monitor monitor){
        this.monitor = monitor;
    }
    
    public void setTransitions(int [] transitions){
        this.transitions = transitions;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setGpioController(GpioController gpio){
        this.gpio = gpio;
    }
    
    public void setPinNumber(int pinNumber){
        this.pinNumber = pinNumber;
    }
    
    public void setPort(int port){
        this.port = port;
    }
    
    public void setActive(boolean active){
        this.active = active;
    }
    
    public boolean isActive(){
        return active;
    }
    
    
}
