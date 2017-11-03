package ConcreteDeviceFactory;

import ConcreteDevice.GpioOutput;
import ConcreteDevice.SerialComunications;
import Device.Device;
import Factory.DeviceFactory;

/**
 *
 * @author Compuj
 */
public class SerialDeviceFactory extends DeviceFactory{

    @Override
    protected Device createDevice(String type) {
        if(type.equalsIgnoreCase("SerialComunications")){
            return new SerialComunications();
        }
        return null;
    }
    
}
