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
public interface Camara {
    
    public void activar();
    public void desactivar();
    public void capturaFoto(String f);
    public void capturaVideo(String v);
    
}
