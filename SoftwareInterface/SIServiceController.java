package SoftwareInterface;
import Service.Service;


/**
 * Interfaz para controlar uno o más servicios. 
 * Describe todas las operaciones sobre el grupo de servicios.
 * 
 * @author Bien Christopher - Resiale Juan
 */
public interface SIServiceController {
    
    /**
    * Agregar servicio al controlador. 
    * 
    * @param service El servicio que se quiere agregar al controlador.
    */
    void addService(Service service);
       
    /**
    * Cargar e iniciar el servicio. 
    * 
    * @param nameService nombre del servicio a iniciar.
    * @return 1 si el servicio se ha iniciado correctamente, de lo contrario
    * retorna 0.
    */
    int startService(String nameService);
   
    
    /**
    * Iniciar todos los servicios del controlador. 
    * 
    * @return 1 si todos los servicios del controlador se han iniciado
    * correctamente, de lo contrario retorna 0.
    */
    int startAllServices();
    
   /**
    * Detiene un servicio.
    * @param nameService nombre del servicio a detener.
    * @return 1 si el servicio se detuvo correctamente, de lo contrario retorna 0. 
    */
    int stopService (String nameService);
    
    /**
     * Detiene todos los servicios.
     * @return 1 si los servicios se detuvieron correctamente, de lo contrario retorna 0. 
     */
    int stopAllService ();
    /**
    * Estado de un servicio del controlador. 
    * 
    * @param service Servicio del que se quiere conocer el estado.
    * @return 1 si el servicio se encuentra activo, de lo contrario retorna 0.
    */
    int stateService(Service service);
    
    /**
    * Estado de todos los servicios del controlador. 
    * 
    * @return 1 si todos los servicios se encuentra activos, de lo contrario
    * retorna 0.
    */
    int stateAllServices();
    
    /**
    * Reiniciar los servicios caidos. 
    *
    */
    void restartDroppedService();
    
    /**
    * Imprime por pantalla una lista de los servicios del controlador. 
    * 
    */
    void listServices();
}
