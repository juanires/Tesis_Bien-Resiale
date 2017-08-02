
package HardwareInterfaz;

/**
 * Interfaz para controlar una cámara. Describe todas las operaciones sobre
 * la cámara.
 * 
 * @author Bien Christpher - Resiale Juan
 */
public interface HICamara {
    
    /**
     * Activa las funciones de la HICamara. 
     */
    public void activar();
    
    /**
     * Desactiva las funciones de la HICamara. 
     */
    public void desactivar();
    
    /**
     * Captura una foto.
     */
    public void capturaFoto();
    
    /**
     * Comienza la ejecución de acciones del hilo. Invoca al método "run()"
     * del objeto que implementa esta interfaz.
     */
    public void start();
    
    /**
     * Captura un video.
     * 
     * @param seg. Duración de la filmacion en segundos.
     */
    public void capturaVideo(int seg);
    
}
