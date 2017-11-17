package DeviceController;

import Device.Device;
import SoftwareInterface.SIDeviceController;
import java.util.HashMap;

/**
 *
 * @author Compuj
 */
public class DeviceController implements SIDeviceController {

    private HashMap <String, Device> mapDevice;
    
    public DeviceController (int devicesNumber){
        
        mapDevice = new HashMap(devicesNumber);
    }
    
    @Override
    public void addDevice(Device device) {
        mapDevice.put(device.getName(),device);
    }

    @Override
    public Device getDevice(String deviceName) {
       
        return mapDevice.get(deviceName);
    }

    @Override
    public int setActiveDevice(String deviceName, boolean setActive) {
        
        if(mapDevice.containsKey(deviceName)){
            mapDevice.get(deviceName).setActive(setActive);
            return 1;
        }
        else return 0;    
    }
    
    @Override
    public boolean isActive(String deviceName) {
        if(mapDevice.containsKey(deviceName)){
           return mapDevice.get(deviceName).isActive();
        }
        else return false;     
    }
}
