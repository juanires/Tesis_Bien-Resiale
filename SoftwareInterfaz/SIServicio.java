package SoftwareInterfaz;

/**
 * Interfaz para interactuar con un servicio. 
 * Describe todas las operaciones sobre el servicio.
 * 
 * @author Bien Christopher - Resiale Juan
 */
public interface SIServicio {
    
    /**
    * Iniciar el servicio. 
    * 
    * @return 1 si el servicio se ha iniciado correctamente, de lo contrario
    * retorna 0.
    */
    int iniciar();
    
    /**
    * Detiene el servicio. 
    * 
    * @return 1 si el servicio se detuvo correctamente, de lo contrario
    * retorna 0.
    */
    int detener();
    
    /**
    * Reiniciar el servicio. 
    * 
    * @return 1 si el servicio se ha reiniciado correctamente, de lo contrario
    * retorna 0.
    */
    int reiniciar();
    
    /**
    * Consultar el estado del servicio. 
    * 
    * @return 1 si el servicio se encuentra activo, de lo contrario
    * retorna 0.
    */
    int estado();
    
    /**
    * Obtener el nombre del servicio. 
    * 
    * @return Nombre del servicio.
    */
    String getNombre();
}
