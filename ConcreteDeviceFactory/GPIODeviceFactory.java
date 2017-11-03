package ConcreteDeviceFactory;
import ConcreteDevice.GpioListener;
import ConcreteDevice.GpioOutput;
import Device.Device;
import Factory.DeviceFactory;


/**
 *
 * @author Compuj
 */
public class GPIODeviceFactory extends DeviceFactory {

    @Override
    protected Device createDevice(String type) {
   
        if(type.equalsIgnoreCase("GpioOutput")){
            return new GpioOutput();
        }
        if(type.equalsIgnoreCase("GpioListener")){
            return new GpioListener();
        }
        return null;
    }
    
}
