package ConcreteDeviceFactory;
import ConcreteDevice.CodeVerifier;
import Device.Device;
import Factory.DeviceFactory;

/**
 * Esta clase es una fábrica de dispositivos verificadores de código. El tipo creado depende del parámetro del método "createDevice". 
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
*/
public class CodeVerifierDeviceFactory extends DeviceFactory {

    /**
    * Crea un nuevo objeto verificador. 
    * 
    * @param type - especifica el tipo de CodeVerifierDevice que se quiere crear. El nombre del parámetro
    * debe coincidir con el nombre de la clase del concreteDevice del cual se quiere crear una instancia.
    * @return ConcreteDevice.
    */
    @Override
    protected Device createDevice(String type) {
        if(type.equalsIgnoreCase("CodeVerifier")){
            return new CodeVerifier();
        }
        return null;
    }
}
