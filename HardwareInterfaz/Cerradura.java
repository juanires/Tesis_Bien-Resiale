package HardwareInterfaz;

/**
 * Interfaz para controlar una cerradura ya sea eléctrica o magnética. 
 * Describe todas las operaciones sobre la cerradura.
 * 
 * @author Bien Christpher - Resiale Juan
 */

public interface Cerradura {
    
    /**
     * Activa las funciones de la cerradura. 
     */
    public void activar();
        
    /**
     * Desactiva las funciones de la cerradura. 
     */
    public void desactivar();
    
    /**
     * Abre la cerradura durante "seg" segundos.
     * 
     * @param seg Cantidad de tiempo en segundos que se mantiene abierta la
     * cerradura.
     */
    public void abrir(int seg);
    
    /**
     * Cierra la cerradura.
     */
    public void cerrar();
    
}
