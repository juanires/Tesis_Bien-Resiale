package ConcreteDeviceFactory;
import ConcreteDevice.Camera;
import Device.Device;
import Factory.DeviceFactory;

/**
 *
 * @author Compuj
 */
public class CameraDeviceFactory extends DeviceFactory {

    @Override
    protected Device createDevice(String type) {
        if(type.equalsIgnoreCase("Camera")){
            return new Camera();
        }
        return null;
    }
    
}
