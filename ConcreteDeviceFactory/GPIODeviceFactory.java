package ConcreteDeviceFactory;
import ConcreteDevice.*;
import Device.Device;
import Factory.DeviceFactory;

/**
 * Esta clase es una fábrica de dispositivos GPIO. El tipo de GPIO creado depende del parámetro del método "createDevice". 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class GPIODeviceFactory extends DeviceFactory {

    /**
    * Crea un nuevo objeto GPIO. 
    * 
    * @param type especifica el nombre del tipo de GPIO que se quiere crear. El nombre del parámetro
    * debe coincidir con el nombre de la clase del concreteDevice del cual se quiere crear una instancia. Por ejemplo si se quiere crear un GPIO 
    * para controlar un led el parametro debe ser: "GpioOutputLed".
    * @return ConcreteDevice.
    */
    @Override
    protected Device createDevice(String type) {
   
        if(type.equalsIgnoreCase("GpioListenerMovement")){
            return new GpioListenerMovement();
        }
        if(type.equalsIgnoreCase("GpioListenerButton")){
            return new GpioListenerButton();
        }
        if(type.equalsIgnoreCase("GpioOutputBell")){
            return new GpioOutputBell();
        }
        if(type.equalsIgnoreCase("GpioOutputLed")){
            return new GpioOutputLed();
        }
        if(type.equalsIgnoreCase("GpioOutputLock")){
            return new GpioOutputLock();
        }
        return null;
    }
}
