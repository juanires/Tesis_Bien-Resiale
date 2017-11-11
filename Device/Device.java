package Device;

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
    protected int nexTransitions;
   
    
    //Metodos
    abstract protected void configure();
    abstract public void start();
    abstract public void active(boolean option);
    
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
    
    public void setActive(boolean active){
        this.active = active;
    }
    
    public boolean isActive(){
        return active;
    }
    
    protected int getNextTransitions(){
        
        int result;
        if(nexTransitions < transitions.size()){
           result = transitions.get(nexTransitions);
           nexTransitions ++;
        }
        else{
            nexTransitions = 0;
            result = transitions.get(nexTransitions);
        } 
        return result;
    }
}
