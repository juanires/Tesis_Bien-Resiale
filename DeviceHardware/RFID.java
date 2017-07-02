package DeviceHardware;

import HardwareProxy.Lector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Compuj
 */
public class RFID implements Lector {
    
    private String codigo;
    
    public RFID(){
    
    }
    
    @Override
    public void activar(){    
    }
    
    @Override
    public void desactivar(){
        
    }
    
    @Override
    public String getCodigo(){
        
        return codigo;
    }
    
}
