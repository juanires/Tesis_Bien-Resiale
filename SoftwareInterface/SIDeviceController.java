package SoftwareInterface;
import Device.Device;

/**
 * Interfaz que define los métodos para innteractuar con un grupo de dispositivos (Device). 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public interface SIDeviceController {
    
    /**
    * Se agrega un nuevo dispositivo al conntrolador.
    * 
    * @param device dispositivo a añadir al controlador.
    */
    void addDevice(Device device);
    
    /**
    * Se obtiene el dispositivo cuyo nombre es "deviceName".
    * 
    * @param deviceName nombre del dispositivo.
    * @return el dispositivo cuyo nombre es "deviceName", o null en caso de que no haya un dispositivo
    * con ese nombre.
    */
    Device getDevice (String deviceName);
    
    /**
    * Se activa o desactiva un dispositivo específico.
    * @param deviceName nombre del dispositvo que se quiere activar o desactivar.
    * @param setActive True para activar, False para desactivar.
    * 
    * @return 1 si la función se ejecutó correctamente, 0 de lo contrario.
    */
    int setActiveDevice(String deviceName, boolean setActive);
    
    /**
    * Retorna True si el dispositivo está activo, de lo contrario retorna False.
    * @param deviceName nombre del dispositivo del cual se quiere conocer el estado.
    * @return True si el dispositivo está activo, False de lo contrario.
    */
    boolean isActive (String deviceName);
}
