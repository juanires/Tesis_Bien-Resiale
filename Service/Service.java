package Service;

/**
 *
 * @author Compuj
 */
public abstract class Service {
    
    /**
    * Iniciar el servicio. 
    * 
    * @return 1 si el servicio se ha iniciado correctamente, de lo contrario
    * retorna 0.
    */
    abstract public int start();
    
    /**
    * Detiene el servicio. 
    * 
    * @return 1 si el servicio se detuvo correctamente, de lo contrario
    * retorna 0.
    */
    abstract public int stop();
    
    /**
    * Reiniciar el servicio. 
    * 
    * @return 1 si el servicio se ha reiniciado correctamente, de lo contrario
    * retorna 0.
    */
    abstract public int restart();
    
    /**
    * Consultar el estado del servicio. 
    * 
    * @return 1 si el servicio se encuentra activo, de lo contrario
    * retorna 0.
    */
    abstract public int state();
    
    /**
    * Obtener el nombre del servicio. 
    * 
    * @return Nombre del servicio.
    */
    abstract public String getName();
}
