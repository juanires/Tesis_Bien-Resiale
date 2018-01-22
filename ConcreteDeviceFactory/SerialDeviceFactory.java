package ConcreteDeviceFactory;
import ConcreteDevice.SerialComunications;
import Device.Device;
import Factory.DeviceFactory;

/**
 * Esta clase es una fábrica de dispositivos de comunicación serial. El tipo de "Serial" creado depende del parámetro del método "createDevice". 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class SerialDeviceFactory extends DeviceFactory{

    /**
    * Crea un nuevo objeto Serial. 
    * 
    * @param type - especifica el nombre del tipo de comunicación serial que se quiere crear. El nombre del parámetro
    * debe coincidir con el nombre de la clase del concreteDevice del cual se quiere crear una instancia.
    * @return ConcreteDevice.
    */
    @Override
    protected Device createDevice(String type) {
        if(type.equalsIgnoreCase("SerialComunications")){
            return new SerialComunications();
        }
        return null;
    }
}
