package Factory;
import ConcreteService.ConcreteService;
import Service.Service;

/**
 * Fábrica de objetos "Service". Esta clase se encarga de crear objetos "ConcreteService" 
 * mediante su método abstracto "getService", el  servicio con el que interactúa depende del parámetro que se le pase 
 * a este método.
 * 
 * @author Bien Christopher - Resiale Juan.
 * 2018 - Córdoba, Argentina. 
 */
public class ServiceFactory {
    
    /**
    * Crea un nuevo objeto que interactúa con un servicio. 
    * 
    * @param nameService especifica el nombre del servicio con el que se quiere interactuar. El nombre del parámetro
    * debe coincidir con el nombre de un servicio. Por ejemplo si se quiere crear un objeto que interactúe con 
    * con el servicio ssh, el parámtro debe ser "ssh".
    * 
    * @return ConcreteService, objeto que va a interactuar con el servicio.
    */
    public static Service getService(String nameService){
        return new ConcreteService(nameService);
    }
}
