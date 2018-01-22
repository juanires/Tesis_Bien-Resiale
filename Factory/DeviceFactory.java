package Factory;
import DataBase.DataBase;
import Device.Device;
import Monitor.Monitor;
import com.pi4j.io.gpio.GpioController;
import java.util.List;

/**
 * Clase abstracta que define el método creacional de las fábricas concretas. Además de definir el método
 * creacional, establece los valores iniciales de los campos de los objetos creados. Posee un método sobreescrito 
 * con distintos tipos de parámetros, por lo que cada implementación del método se corresponde con la inicialización
 * de un tipo de objeto (ConcreteDevice) diferente.
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public abstract class DeviceFactory {
    
    /**
     * Método creacional que deben implementar las fábricas concretas.
     * 
     * @param type especifica el tipo de ConcreteDevice que se quiere crear.
     * @return Device creado por las fábricas concretas.
    */    
    abstract protected Device createDevice(String type);
    
    //Implements para GPIO
    /**
     * Método que inicializa los valores de los campos de los "Devices" GPIO.
     * @param dataBase base de datos con la que interactúa el objeto.
     * @param monitor monitor con el que interactúa el objeto.
     * @param transitions lista de transiciónes que debe "dispaarar" el objeto.
     * @param name nombre del device.
     * @param gpio controlador de GPIO.
     * @param pinNumber número de GPIO asignado al device. 
     * @param time tiempo asignado al objeto para realizar una acción determinada.
     * @param type tipo de device.
     * @return el device con todos sus campos seteados.
     */
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
        device.setDevice(null);
        device.start();
        
        try {Thread.sleep(20);} 
        catch (InterruptedException ex) {}
        
        return device;
    } 
    
    //Implements para Serial
    /**
     * Método que inicializa los valores de los campos de los "Devices" SerialComunications.
     * @param monitor monitor con el que interactúa el objeto.
     * @param transitions lista de transiciónes que debe "dispaarar" el objeto.
     * @param name nombre del device.
     * @param type tipo de device.
     * @return el device con todos sus campos seteados.
     */
    public Device implementsDevice( Monitor monitor, List<Integer> transitions, String name,String type){
        Device device = createDevice(type);
        
        device.setDataBase(null);
        device.setMonitor(monitor);
        device.setTransitions(transitions);
        device.setName(name);
        device.setGpioController(null);
        device.setPinNumber(99);
        device.setPort(99);
        device.setTime(0);
        device.setDevice(null);
        device.start();
        
        try {Thread.sleep(20);} 
        catch (InterruptedException ex) {}
        
        return device;
    } 
    
    //Implements para Camara
    /**
     * Método que inicializa los valores de los campos de los "Devices" Camera.
     * @param dataBase base de datos con la que interactúa el objeto.
     * @param monitor monitor con el que interactúa el objeto.
     * @param transitions lista de transiciónes que debe "disparar" el objeto.
     * @param name nombre del device.
     * @param port puerto asignado al device.
     * @param type tipo de device.
     * @return el device con todos sus campos seteados. 
     */
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
        device.setDevice(null);
        device.start();
        
        try {Thread.sleep(20);} 
        catch (InterruptedException ex) {}
        
        return device;
    } 
    
    //Implements para CodeVerifier
    /**
     * Método que inicializa los valores de los campos de los "Devices" CodeVerifier.
     @param dataBase base de datos con la que interactúa el objeto.
     * @param monitor monitor con el que interactúa el objeto.
     * @param transitions lista de transiciónes que debe "disparar" el objeto.
     * @param name nombre del device.
     * @param codeReader device lector de códigos.
     * @param type tipo de device.
     * @return el device con todos sus campos seteados.
     */
    public Device implementsDevice(DataBase dataBase, Monitor monitor, List<Integer> transitions, String name, Device codeReader, String type){
        Device device = createDevice(type);
    
        device.setDataBase(dataBase);
        device.setMonitor(monitor);
        device.setTransitions(transitions);
        device.setName(name);
        device.setGpioController(null);
        device.setPinNumber(99);
        device.setPort(0);
        device.setTime(0);
        device.setDevice(codeReader);
        device.start();
        
        try {Thread.sleep(20);} 
        catch (InterruptedException ex) {}
        
        return device;
    }
    
    //Implements para WebEvent
    /**
     * Método que inicializa los valores de los campos de los "Devices" WebEvent.
     * @param dataBase base de datos con la que interactúa el objeto.
     * @param monitor monitor con el que interactúa el objeto.
     * @param transitions lista de transiciónes que debe "disparar" el objeto.
     * @param name nombre del device.
     * @param port puerto asignado al device.
     * @param codeReader device lector de códigos.
     * @param type tipo de device.
     * @return el device con todos sus campos seteados.
     */
    
    public Device implementsDevice(DataBase dataBase, Monitor monitor, List<Integer> transitions, String name,int port, Device codeReader, String type){
        Device device = createDevice(type);
        
        device.setDataBase(dataBase);
        device.setMonitor(monitor);
        device.setTransitions(transitions);
        device.setName(name);
        device.setGpioController(null);
        device.setPinNumber(99);
        device.setPort(port);
        device.setTime(0);
        device.setDevice(codeReader);
        device.start();
        
        try {Thread.sleep(20);} 
        catch (InterruptedException ex) {}
        
        return device;
    }
}
