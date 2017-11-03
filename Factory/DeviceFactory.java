package Factory;
import Device.Device;
import Monitor.Monitor;
import com.pi4j.io.gpio.GpioController;

/**
 *
 * @author Compuj
 */
public abstract class DeviceFactory {
        
    abstract protected Device createDevice(String type);
    
    //Implements para GPIO
    public Device implementsDevice(Monitor monitor, int[] transitions, String name, GpioController gpio,int pinNumber, String type){
        Device device = createDevice(type);
        
        device.setMonitor(monitor);
        device.setTransitions(transitions);
        device.setName(name);
        device.setGpioController(gpio);
        device.setPinNumber(pinNumber);
        device.setPort(99);
        device.setActive(false);
        device.start();
        
        return device;
    } 
    
    //Implements para Serial
    public Device implementsDevice(Monitor monitor, int[] transitions, String name,String type){
        Device device = createDevice(type);
        
        device.setMonitor(monitor);
        device.setTransitions(transitions);
        device.setName(name);
        device.setGpioController(null);
        device.setPinNumber(99);
        device.setPort(99);
        device.setActive(false);
        device.start();
        
        return device;
    } 
    
    //Implements para Camara
    public Device implementsDevice(Monitor monitor, int[] transitions, String name, int port, String type){
        Device device = createDevice(type);
        
        device.setMonitor(monitor);
        device.setTransitions(transitions);
        device.setName(name);
        device.setGpioController(null);
        device.setPinNumber(99);
        device.setPort(port);
        device.setActive(false);
        device.start();
        
        return device;
    } 
    
  
    
}
