package HardwareInterfaz;

/**
 * Interfaz para controlar dispositivos HILed. Describe todas las operaciones sobre
 el HILed.
 * 
 * @author Bien Christopher - Resiale Juan
 */
public interface HILed {
    
    /**
     * Comienza la ejecución de acciones del hilo. Invoca al método "run()"
     * del objeto que implementa esta interfaz.
     */
  //  public void start();
    
    /**
     * Encender LED. 
     */
    public void encender();

    /**
     * Apagar LED. 
     */
    public void apagar();
    
    /**
     * Activa las funciones del LED. 
     */
    public void activar();
    
    /**
     * Desactiva las funciones del LED. 
     */
    public void desactivar();
    
    /**
     * Se configura el dispositivo de LED. 
     */
    public void configurar();
    
}
