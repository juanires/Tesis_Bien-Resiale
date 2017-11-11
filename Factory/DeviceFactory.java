package Factory;
import DataBase.DataBase;
import Device.Device;
import Monitor.Monitor;
import com.pi4j.io.gpio.GpioController;
import java.util.List;

/**
 *
 * @author Compuj
 */
public abstract class DeviceFactory {
        
    abstract protected Device createDevice(String type);
    
    //Implements para GPIO
    public Device implementsDevice(DataBase dataBase,Monitor monitor, List<Integer> transitions, String name, GpioController gpio,int pinNumber,int time, String type){
        Device device = createDevice(type);
        
        device.setDataBase(dataBase);
        device.setMonitor(monitor);
        device.setTransitions(transitions);
        device.setName(name);
        device.setGpioController(gpio);
        device.setPinNumber(pinNumber);
        device.setPort(99);
        device.setTime(time);
        device.setActive(false);
        device.start();
        
        return device;
    } 
    
    //Implements para Serial
    public Device implementsDevice(DataBase dataBase, Monitor monitor, List<Integer> transitions, String name,String type){
        Device device = createDevice(type);
        
        device.setDataBase(dataBase);
        device.setMonitor(monitor);
        device.setTransitions(transitions);
        device.setName(name);
        device.setGpioController(null);
        device.setPinNumber(99);
        device.setPort(99);
        device.setTime(0);
        device.setActive(false);
        device.start();
        
        return device;
    } 
    
    //Implements para Camara
    public Device implementsDevice(DataBase dataBase, Monitor monitor, List<Integer> transitions, String name, int port, String type){
        Device device = createDevice(type);
        
        device.setDataBase(dataBase);
        device.setMonitor(monitor);
        device.setTransitions(transitions);
        device.setName(name);
        device.setGpioController(null);
        device.setPinNumber(99);
        device.setPort(port);
         device.setTime(0);
        device.setActive(false);
        device.start();
        
        return device;
    } 
    
  
    
}
