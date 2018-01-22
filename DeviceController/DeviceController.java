package DeviceController;
import Device.Device;
import SoftwareInterface.SIDeviceController;
import java.util.HashMap;

/**
 * Clase que representa a un controlador de dispositivos. Mediante esta clase se puede interactuar con un conjunto
 * de dispositivos "Device" pudiendo acceder a las funciones de los mismos. De esta forma se encapsula el control
 * y la interacción con los dispositivos.
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class DeviceController implements SIDeviceController {

    private HashMap <String, Device> mapDevice;
    
    /**
    * Crea un nuevo controlador de dispositivos.
    * 
    * @param devicesNumber numero de dispositivos a controlar.
    */
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
