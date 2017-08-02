
package SoftwareInterfaz;
import SoftwareInterfaz.SIServicio;
/**
 *
 * @author Compuj
 */
public interface SIControladorDeServicios {
    
    void agregarServicio(SIServicio servicio);
    int cargarServicio(SIServicio servicio);
    int cargarTodosLosServicios();
    int estadoServicio(SIServicio servicio);
    int estadoTodosLosServicios();
    void cargarServiciosCaidos();
    void listarServicios();
    
    
    
}
