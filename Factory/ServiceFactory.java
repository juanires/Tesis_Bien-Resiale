package Factory;

import ConcreteService.ConcreteService;
import Service.Service;

/**
 *
 * @author Compuj
 */
public class ServiceFactory {
    
    public static Service getService(String nameService){
        return new ConcreteService(nameService);
    }
}
