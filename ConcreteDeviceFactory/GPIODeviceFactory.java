package ConcreteDeviceFactory;
import ConcreteDevice.*;
import Device.Device;
import Factory.DeviceFactory;


/**
 *
 * @author Compuj
 */
public class GPIODeviceFactory extends DeviceFactory {

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
