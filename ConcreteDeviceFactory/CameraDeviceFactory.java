package ConcreteDeviceFactory;
import ConcreteDevice.Camera;
import Device.Device;
import Factory.DeviceFactory;

/**
 * Esta clase es una fábrica de dispositivos "Camera". El tipo creado depende del parámetro del método "createDevice". 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class CameraDeviceFactory extends DeviceFactory {

    /**
    * Crea un nuevo "CameraDevice". 
    * 
    * @param type - especifica el tipo de CameraDevice que se quiere crear. El nombre del parámetro
    * debe coincidir con el nombre de la clase del concreteDevice del cual se quiere crear una instancia.
    * @return ConcreteDevice.
    */
    @Override
    protected Device createDevice(String type) {
        if(type.equalsIgnoreCase("Camera")){
            return new Camera();
        }
        return null;
    }
}
