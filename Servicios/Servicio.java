
package Servicios;

import SoftwareInterfaz.SIServicio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Compuj
 */
public class Servicio implements SIServicio {
    
    private String nombreServicio;
    
    public Servicio(String servicio){
        nombreServicio = servicio;
    }
    
    @Override
    public int iniciar(){
        
        try {
            Process proceso;
            proceso = Runtime.getRuntime().exec("/etc/init.d/"+nombreServicio+" start");
            InputStream is = proceso.getInputStream(); 
            BufferedReader buffer = new BufferedReader (new InputStreamReader (is));
            String resultado = buffer.readLine();
            
            if(resultado.contains("Starting "+nombreServicio)){
                return 1;
            }
            
        } 
        catch (IOException ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    
    @Override
    public int parar(){
        
        try {
            Process proceso;
            proceso = Runtime.getRuntime().exec("/etc/init.d/"+nombreServicio+" stop");
            InputStream is = proceso.getInputStream(); 
            BufferedReader buffer = new BufferedReader (new InputStreamReader (is));
            String resultado = buffer.readLine();
            
            if(resultado.contains("Stopping "+nombreServicio)){
                return 1;
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    @Override
    public int reiniciar(){
        
        try {
            Process proceso;
            proceso = Runtime.getRuntime().exec("/etc/init.d/"+nombreServicio+" restart");
            InputStream is = proceso.getInputStream(); 
            BufferedReader buffer = new BufferedReader (new InputStreamReader (is));
            String resultado = buffer.readLine();
            
            if(resultado.contains("Restarting "+nombreServicio)){
                return 1;
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    @Override
    public int estado(){
                 
        try {
            Process proceso;
            proceso = Runtime.getRuntime().exec("/etc/init.d/"+nombreServicio+" status");
            InputStream is = proceso.getInputStream(); 
            BufferedReader buffer = new BufferedReader (new InputStreamReader (is));
            
            //Se descartan las dos primeras lineas y se lee la tercera
            buffer.readLine();
            buffer.readLine();
            String resultado = buffer.readLine();
            
            if(resultado.contains("Active: active")){
                return 1;
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return 0;
    }
    
    @Override
    public String getNombre(){
        
        return nombreServicio;
    }
    
    
}
