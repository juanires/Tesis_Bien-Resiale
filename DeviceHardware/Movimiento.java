/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeviceHardware;

import HardwareProxy.Sensor;

/**
 *
 * @author Compuj
 */
public class Movimiento implements Sensor {
    
    private float dato;
    
    //Constructor
    public Movimiento(){
    }
    
    @Override
    public void activar(){}
    
    
    @Override
    public void desactivar(){}
    
    
    @Override
    public void configurar(String parametros){}
    
    
    @Override
    public float getDato(){
        
        return dato;
    }
    
}
