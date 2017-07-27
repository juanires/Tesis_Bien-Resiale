package HardwareInterfaz;

/**
 * Interfaz para controlar disposivos de sonido. Describe todas las operaciones sobre
 * los dispositivos de sonido.
 * 
 * @author Bien Christpher - Resiale Juan
 */

public interface Sonido {
   
    
    /**
     * Comienza la ejecución de acciones del hilo. Invoca al método "run()"
     * del objeto que implementa esta interfaz.
     */
    public void start();
    
    /**
     * Emitir sonido desde el dispositivo de sonido. 
     */
    public void encender();

    /**
     * El dispositivo de sonido deja de emitir sonido. 
     */
    public void apagar();
    
    /**
     * Activa las funciones del Sonido. 
     */
    public void activar();
    
    /**
     * Desactiva las funciones del Sonido. 
     */
    public void desactivar();
    
    /**
     * Se configura el dispositivo de sonido. 
     */
    public void configurar();
}
