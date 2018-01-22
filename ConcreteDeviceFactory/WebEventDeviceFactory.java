package ConcreteDeviceFactory;
import ConcreteDevice.WebEventOpenDoor;
import ConcreteDevice.WebEventSendCode;
import Device.Device;
import Factory.DeviceFactory;

/**
 * Esta clase es una fábrica de dispositivos de tipo webEvent. El tipo de "webEvent" creado depende del parámetro del método "createDevice". 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class WebEventDeviceFactory extends DeviceFactory {

    /**
    * Crea un nuevo objeto webEvent. 
    * 
    * @param type - especifica el nombre del tipo de evento web que se quiere crear. El nombre del parámetro
    * debe coincidir con el nombre de la clase del concreteDevice del cual se quiere crear una instancia.
    * @return ConcreteDevice.
    */
    @Override
    protected Device createDevice(String type) {
   
        if(type.equalsIgnoreCase("WebEventSendCode")){
            return new WebEventSendCode();
        }
        if(type.equalsIgnoreCase("WebEventOpenDoor")){
            return new WebEventOpenDoor();
        }
        return null;
    }
}
