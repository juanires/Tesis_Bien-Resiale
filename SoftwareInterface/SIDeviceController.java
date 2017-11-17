package SoftwareInterface;

import Device.Device;

/**
 *
 * @author Compuj
 */
public interface SIDeviceController {
    
    void addDevice(Device device);
          
    Device getDevice (String deviceName);
    
    int setActiveDevice(String deviceName, boolean setActive);
    
    boolean isActive (String deviceName);
    
    
     
}
