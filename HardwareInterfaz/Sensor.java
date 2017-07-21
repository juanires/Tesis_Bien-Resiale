package HardwareInterfaz;

/**
 * Interfaz para controlar un sensor, cualquiera sea su tipo. Describe todas las operaciones sobre
 * el sensor.
 * 
 * @author Bien Christpher - Resiale Juan
 */
public interface Sensor {
       
    /**
     * Activa las funciones del sensor. 
     */
    public void activar();
    
    /**
     * Desactiva las funciones del sensor. 
     */
    public void desactivar();
    
    /**
     * Configura el sensor. 
     */
    public void configurar();
    
    
    /**
     * Comienza la ejecución de acciones del hilo. Invoca al método "run()"
     * del objeto que implementa esta interfaz.
     */
    public void start();
    
    /**
     * Obtiene la información del sensor.
     * 
     * @return info. Retorna un String con la información del sensor
     */
    public String getInfo();
        
}
