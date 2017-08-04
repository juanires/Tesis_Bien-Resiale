package Servicios;

import SoftwareInterfaz.SIServicio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa un servicio. 
 * Describe todas las operaciones sobre el servicio.
 * 
 * @author Bien Christopher - Resiale Juan
 */
public class Servicio implements SIServicio {
        
    private String nombreServicio;
    
    
    /**
    * Crea un nuevo servicio. 
    * 
    * @param servicio Nombre del servicio que se quiere asociar al objeto.
    */
    public Servicio(String servicio){
        nombreServicio = servicio;
    }
    
    
    /**
    * Iniciar el servicio. Ejecuta una llamada de sistema para iniciar
    * el servicio y evalua la respuesta de la misma para determinar si se
    * ha iniciado el servicio.
    * 
    * @return 1 si el servicio se ha iniciado correctamente, de lo contrario
    * retorna 0.
    */
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
    
    /**
    * Detener el servicio. Ejecuta una llamada de sistema para detener
    * el servicio y evalua la respuesta de la misma para determinar si se
    * ha detenido el servicio.
    * 
    * @return 1 si el servicio se ha detenido correctamente, de lo contrario
    * retorna 0.
    */
    @Override
    public int detener(){
        
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
    
    /**
    * Reiniciar el servicio. Ejecuta una llamada de sistema para reiniciar
    * el servicio y evalua la respuesta de la misma para determinar si se
    * ha reiniciado el servicio.
    * 
    * @return 1 si el servicio se ha reiniciado correctamente, de lo contrario
    * retorna 0.
    */
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
    
    /**
    * Estado el servicio. Ejecuta una llamada de sistema para obtener el estado
    * del servicio y evalua la respuesta de la misma para determinar el estado.
    * 
    * @return 1 si el servicio se encuentra activo, de lo contrario retorna 0.
    */
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
    
    /**
    * Obtener el nombre del servicio. 
    * 
    * @return Nombre del servicio.
    */
    @Override
    public String getNombre(){
        
        return nombreServicio;
    }
}
