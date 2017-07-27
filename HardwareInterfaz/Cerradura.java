package HardwareInterfaz;

/**
 * Interfaz para controlar una cerradura ya sea eléctrica o magnética. 
 * Describe todas las operaciones sobre la cerradura.
 * 
 * @author Bien Christpher - Resiale Juan
 */

public interface Cerradura {
    
    /**
     * Comienza la ejecución de acciones del hilo. Invoca al método "run()"
     * del objeto que implementa esta interfaz.
     */
    public void start();
    
    /**
     * Abre la cerradura durante los segundos especificados por el 
     * parametro "seg".
     * 
     * @param seg Tiempo (en segundos) que se mantiene abierta la cerradura.
     */
    public void abrir(int seg);
    
    /**
     * Activa las funciones de la cerradura. 
     */
    public void activar();
        
    /**
     * Desactiva las funciones de la cerradura. 
     */
    public void desactivar();
                  
    /**
     * Se configura la cerradura. 
     */
    public void configurar();
    
}
