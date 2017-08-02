package HardwareInterfaz;
/**
 * Interfaz para controlar un lector de codigos. Describe todas las operaciones sobre
 * el lector.
 * 
 * @author Bien Christpher - Resiale Juan
 */

public interface HILector {
    
    public void activar();
    public void desactivar();
    public String getCodigo();
    public void start();
}
