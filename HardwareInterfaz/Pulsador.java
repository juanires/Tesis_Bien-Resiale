package HardwareInterfaz;
/**
 * Interfaz para controlar un pulsador. Describe todas las operaciones sobre
 * el pulsador.
 * 
 * @author Bien Christpher - Resiale Juan
 */

public interface Pulsador {
    
    /**
     * Activa las funciones del Pulsador.
     */
    public void activar();
    
    /**
     * Desactiva las funciones del Pulsador. 
     */
    public void desactivar();
    
     /**
     * Comienza la ejecución de acciones del hilo. Invoca al método "run()"
     * del objeto que implementa esta interfaz.
     */
    public void start();
    
    
    /**
     * Se configura el pulsador. 
     */
    public void configurar();
}
