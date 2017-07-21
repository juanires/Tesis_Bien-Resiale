package Hardware;
import HardwareInterfaz.Cerradura;

/**
 * Clase que interactua con una electrocerradura. 
 * 
 * @author Bien Christpher - Resiale Juan
 */ 
public class Electrocerradura implements Cerradura {

    /**
     * Crea un nuevo objeto Electrocerradura. 
     */
    public Electrocerradura(){}
    
    /**
     * Activa las funciones de la electrocerradura. 
     */
    @Override
    public void activar() {     
    }

    /**
     * Desactiva las funciones de la electrocerradura. 
     */
    @Override
    public void desactivar() {
    }
    
    /**
     * Abre la electrocerradura durante "seg" segundos.
     * 
     * @param seg Cantidad de tiempo en segundos que se mantiene abierta la
     * electrocerradura.
     */
    @Override
    public void abrir(int seg) {
    }
    
    /**
     * Cierra la electrocerradura.
     */
    @Override
    public void cerrar() {
    }
    
    
}
