
package SoftwareInterfaz;
import SoftwareInterfaz.SIServicio;

/**
 * Interfaz para controlar uno o más servicios. 
 * Describe todas las operaciones sobre el grupo de servicios.
 * 
 * @author Bien Christopher - Resiale Juan
 */
public interface SIControladorDeServicios {
    
    /**
    * Agregar servicio al controlador. 
    * 
    * @param servicio El servicio que se quiere agregar al controlador.
    */
    void agregarServicio(SIServicio servicio);
       
    /**
    * Cargar e iniciar el servicio. 
    * 
    * @param servicio Servicio a iniciar.
    * @return 1 si el servicio se ha iniciado correctamente, de lo contrario
    * retorna 0.
    */
    int iniciarServicio(SIServicio servicio);
    
    /**
    * Iniciar todos los servicios del controlador. 
    * 
    * @return 1 si todos los servicios del controlador se han iniciado
    * correctamente, de lo contrario retorna 0.
    */
    int iniciarTodosLosServicios();
    
    /**
    * Estado de un servicio del controlador. 
    * 
    * @param servicio Servicio del que se quiere conocer el estado.
    * @return 1 si el servicio se encuentra activo, de lo contrario retorna 0.
    */
    int estadoServicio(SIServicio servicio);
    
    /**
    * Estado de todos los servicios del controlador. 
    * 
    * @return 1 si todos los servicios se encuentra activos, de lo contrario
    * retorna 0.
    */
    int estadoTodosLosServicios();
    
    /**
    * Reiniciar los servicios caidos. 
    *
    */
    void cargarServiciosCaidos();
    
    /**
    * Imprime por pantalla una lista de los servicios del controlador. 
    * 
    */
    void listarServicios();
}
