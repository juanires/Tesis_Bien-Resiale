package Device;

import ConcreteDevice.SerialComunications;
import DataBase.DataBase;
import Monitor.Monitor;
import com.pi4j.io.gpio.GpioController;
import java.util.List;


/**
 *
 * @author Compuj
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
 
   
    
    //Metodos
    abstract protected void configure();
    abstract public void start();
    abstract protected void active(boolean option);
    abstract public String getCode();
    
    
    public String getName(){
        return name;
    }
    
    public void setDataBase(DataBase dataBase){
        this.dataBase = dataBase;
    }
    public void setMonitor(Monitor monitor){
        this.monitor = monitor;
    }
    
    public void setTransitions(List<Integer> transitions){
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
    
    public void setTime(int time){
        this.time = time;
    }
    
    public void setActive(boolean setActive){
        active(setActive);
        this.active = setActive;
    }
    
     public void setDevice(Device device){
        this.device = device;
    }
    
    public boolean isActive(){
        return active;
    }
    
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
    
    protected void resetNextTransitions(){
        nextTransitions = 0;
    }
    
    
}
