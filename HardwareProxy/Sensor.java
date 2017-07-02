/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HardwareProxy;

/**
 *
 * @author Compuj
 */
public interface Sensor {
    
    public void activar();
    public void desactivar();
    public void configurar(String parametros);
    public float getDato();
    
}
